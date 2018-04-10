package trade4fun.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import trade4fun.dao.ArticleDao;
import trade4fun.dao.CommentDao;
import trade4fun.dao.ReplyDao;
import trade4fun.dao.UserDao;
import trade4fun.dao.VoteDao;
import trade4fun.pojo.bbs.Article;
import trade4fun.pojo.bbs.Comment;
import trade4fun.pojo.bbs.Reply;
import trade4fun.service.DiscussService;
import trade4fun.utils.PageUtil;
import trade4fun.utils.RedisUtil;
import trade4fun.utils.Result;
import trade4fun.utils.UserHolder;

/*
 * 点赞va:vote_article  vc:vote_comment  vr:vote_reply
 * 点击量cl:click
 * 评论数co:comment
 * 键值存储于redis 3号仓库中
 */
@Controller
@RequestMapping("discuss")
public class DiscussController {
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private ReplyDao replyDao;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private UserDao userDao;
	@Autowired
	private VoteDao voteDao;
	@Autowired
	private UserHolder userHolder;
	@Autowired
	private DiscussService discussService;
	
	@RequestMapping(value="test")
	@ResponseBody
	public String test() {
		for(int i=0; i<100; i++) {
			System.out.println(redisUtil.get("voted_4"));
			System.out.println(redisUtil.get("fuck"));
		}
		System.out.println("over");
		return "ok";
	}
	
	
	@RequestMapping
	public String wiki() {
		return "discuss/wiki";
	}
	
	@RequestMapping(value = "submission")
	public String submission() {
		return "discuss/submission";
	}
	
	@RequestMapping(value = "myarticle")
	public String myarticle() {
		return "discuss/myarticle";
	}
	
	//投稿
	@PostMapping(value="submit")
	@ResponseBody
	public Result submit(
			HttpServletRequest request,
			@RequestParam(value = "game_type") Integer game_type,
			@RequestParam(value = "article_type") Integer article_type,
			@RequestParam(value = "title") String title,
			@RequestParam(value = "brief") String brief,
			@RequestParam(value = "content") String content,
			@RequestParam(value = "cover") String cover) {
		
		String token = null;
		if(request.getCookies() != null) {
			for(Cookie cookie : request.getCookies()) {
				if(cookie.getName().equals("token")) {
					token = cookie.getValue();
				}
			}
		} else {
			return Result.fail("未登录");
		}
		if(token == null) {
			return Result.fail("未登录");
		}
		Article article = new Article();
		Integer uid = userHolder.getUser().getId();
		String username = userHolder.getUser().getUsername();
		article.setUid(uid);
		article.setUsername(username);
		article.setGame_type(game_type);
		article.setArticle_type(article_type);
		article.setTitle(title);
		article.setBrief(brief);
		article.setContent(content);
		article.setCover(cover);
		article.setStat(1);
		article.setUp(3);
		articleDao.insert(article);
		return Result.success();
	}
	
	//条件筛选后获取文章列表
	@RequestMapping(value="select_filter")
	@ResponseBody
	public Result select_filter(
			@RequestParam(required= false ,value = "up") Integer up,
			@RequestParam(required= false ,value = "stat") Integer stat,
			@RequestParam(required= false ,value = "game_type") Integer game_type,
			@RequestParam(required= false ,value = "article_type") Integer article_type,
			@RequestParam(required= false ,value = "uid") Integer uid,
			@RequestParam(required= false ,value = "title") String title, 
			@RequestParam(required= false ,value = "byclicked") String byclicked, 
			@RequestParam(required= false ,value = "byvoted") String byvoted) {
		
		List<Article> articles = articleDao.select_filter(up, stat, game_type, article_type, uid, title, byclicked, byvoted);
		List<Article> trans = new ArrayList<Article>();
		for(int i=0; i<articles.size(); i++) {
			Article article = articles.get(i);
			String clicked_num = redisUtil.get("cl"+article.getAid(),3) ;
			article.setClicked(Integer.parseInt(clicked_num==null ? "0" : clicked_num));
			trans.add(article);
		}
		return Result.success(trans);
	}
	
	//根据文章id获取文章信息
	@RequestMapping(value="article")
	public ModelAndView selectByAid(@RequestParam(value="aid") Integer aid) {
		ModelAndView model = new ModelAndView("discuss/article");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Article article = articleDao.selectByAid(aid);
		if(article == null) return null;
		String clicked_num = redisUtil.get("cl"+article.getAid(),3) ;
		String voted_num = redisUtil.get("va"+article.getAid(),3) ;
		article.setClicked(Integer.parseInt(clicked_num==null ? "0" : clicked_num));
		article.setVoted(Integer.parseInt(voted_num==null ? "0" : voted_num));
		resultMap.put("article", article);
		redisUtil.incr("cl"+article.getAid());
		Integer like = voteDao.article_voted(article.getUid(), article.getAid());
		if(like.equals(0)) {
			model.addObject("like", false);
		} else if(like.equals(1)) {
			model.addObject("like", true);
		}
		model.addObject("result", Result.success(resultMap));
		return model;
	}
	
	//查看此文章下的所有评论
	@RequestMapping(value="getAllReply")
	@ResponseBody
	public Result getAllReply(
			@RequestParam(value="aid") Integer aid,
			@RequestParam(value="current") Integer current,
			@RequestParam(value="size") Integer size) {
		
		PageUtil pageUtil = new PageUtil(size, current);
		List<Comment> comment_list = commentDao.selectByAid(aid, pageUtil.getStartPos(), pageUtil.getSize());
		List<Comment> comment_result = new ArrayList<Comment>();
		for(int i=0; i<comment_list.size(); i++) {
			Comment comment = comment_list.get(i);
			String voted_num = redisUtil.get("vc"+comment.getCid(),3) ;
			comment.setVoted(Integer.parseInt(voted_num==null ? "0" : voted_num));
			if(comment.getHaschild() == 0) {
				comment_result.add(comment);
				continue;//评论下面没有回复则跳过
			}
			List<Reply> reply_list_temp = replyDao.selectByCid(comment.getCid(),0,5);
			List<Reply> reply_list = new ArrayList<Reply>();
			for (Reply reply : reply_list_temp) {
				String voted_num_reply = redisUtil.get("vr"+comment.getCid(),3) ;
				reply.setVoted(Integer.parseInt(voted_num_reply==null ? "0" : voted_num_reply));
				reply_list.add(reply);
			}
			comment.setReply_list(reply_list);
			comment_result.add(comment);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("comments", comment_result);
		map.put("page", pageUtil);
		return Result.success(map);
	}
	//展开更多回复
	@RequestMapping(value="more_reply")
	@ResponseBody
	public List<Reply> more_reply(
			@RequestParam(value="cid") Integer cid,
			@RequestParam(value="lastPos") Integer lastPos,
			@RequestParam(value="length") Integer length) {
		
		Integer size = null;
		Integer startPos = lastPos + 1;
		if(length-lastPos > 10) {
			size = 10;
		} else {
			size = length-lastPos;
		}
		return replyDao.selectByCid(cid, startPos, size);
	}
	
	//查看此人的所有文章
	@RequestMapping(value="selectByUid")
	@ResponseBody
	public List<Article> selectByUid(Integer uid) {
		return articleDao.selectByUid(uid);
	}
	
	/*
	 * comment & reply 增加操作
	 */
	@PostMapping(value="add_comment")
	@ResponseBody
	public Result add_comment(
			@RequestParam(value="aid") Integer aid, 
			@RequestParam(value="content") String content) {
		
		return discussService.add_comment(aid, content);
	}
	@PostMapping(value="add_reply")
	@ResponseBody
	public Result add_reply(
			@RequestParam(value="aid") Integer aid,
			@RequestParam(value="cid") Integer cid,
			@RequestParam(value="content") String content) {
		
		return discussService.add_reply(aid, cid, content);
	}
	
	@PostMapping(value="vote")
	@ResponseBody
	public String vote(@RequestParam(value="type") String type, @RequestParam(value="id") Integer id) {
		
		return discussService.vote(type, id);
	}
	
	
	
	
}
