package trade4fun.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import trade4fun.dao.ArticleDao;
import trade4fun.dao.CommentDao;
import trade4fun.dao.ReplyDao;
import trade4fun.dao.VoteDao;
import trade4fun.pojo.bbs.Comment;
import trade4fun.pojo.bbs.Reply;
import trade4fun.service.DiscussService;
import trade4fun.utils.RedisUtil;
import trade4fun.utils.Result;
import trade4fun.utils.UserHolder;

@Service
@Transactional
public class DiscussServiceImpl implements DiscussService {
	
	@Autowired
	private VoteDao voteDao;
	@Autowired
	private UserHolder userHolder;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private ReplyDao replyDao;
	@Autowired
	private RedisUtil redisUtil;

	public String vote(String type, Integer id) {
		Integer user_id = userHolder.getUser().getId();
		switch (type) {
		case "article":
			if(voteDao.article_voted(user_id, id)>0) {
				voteDao.delete_article_vote(user_id, id);
				redisUtil.decr("va"+id);
				return "disliked";
			}
			voteDao.insert_article_vote(user_id, id);
			redisUtil.incr("va"+id);
			return "liked";
		case "comment":
			if(voteDao.comment_voted(user_id, id)>0) {
				voteDao.delete_comment_vote(user_id, id);
				redisUtil.decr("vc"+id);
				return "disliked";
			}
			voteDao.insert_comment_vote(user_id, id);
			redisUtil.incr("vc"+id);
			return "liked";
		case "reply":
			if(voteDao.reply_voted(user_id, id)>0) {
				voteDao.delete_reply_vote(user_id, id);
				redisUtil.decr("vr"+id);
				return "disliked";
			}
			voteDao.insert_reply_vote(user_id, id);
			redisUtil.incr("vr"+id);
			return "liked";
		default:
			return "wtf";
		}
	}

	public Result add_comment(Integer aid, String content) {
		
		Integer uid = userHolder.getUser().getId();
		String username = userHolder.getUser().getUsername();
		Comment comment = new Comment();
		comment.setAid(aid);
		comment.setUid(uid);
		comment.setUsername(username);
		comment.setContent(content);
		commentDao.insert(comment);
		int cid = comment.getCid();
		articleDao.comment_num(aid);
		if(cid>0) {
			return Result.success(cid);
		} else {
			return Result.fail("插入失败");
		}
	}
	
	public Result add_reply(Integer aid, Integer cid, String content) {
		
		Integer uid = userHolder.getUser().getId();
		String username = userHolder.getUser().getUsername();
		Reply reply = new Reply();
		reply.setAid(aid);
		reply.setCid(cid);
		reply.setUid(uid);
		reply.setUsername(username);
		reply.setContent(content);
		replyDao.insert(reply);
		int rid = reply.getRid();
		commentDao.reply_num(cid);
		if(cid>0) {
			return Result.success(rid);
		} else {
			return Result.fail("插入失败");
		}
	}
	
}
