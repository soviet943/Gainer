package trade4fun.pojo.bbs;

import java.util.Date;

public class Article {
	
	//文章状态
	public static final int STAT_PUBLIC = 0;	//待审核
	public static final int STAT_PRIVATE = 1;	//公开
	public static final int STAT_DEL = 2;		//已删除
	//文章对应的游戏类型
	public static final int other = 0;			//其它
	public static final int CSGO = 1;			//csgo
	public static final int DOTA2 = 2;			//dota2
	public static final int PUBG = 3;			//绝地求生
	public static final int H1Z1 = 4;			//h1z1
	//文章内容类型
	public static final int news = 1;			//资讯
	public static final int wiki = 2;			//饰品科普
	
	private Integer up;				//置顶级别
	private Integer stat;			//文章状态
	private Integer aid;			//本身	article id
	private Integer uid;			//user id
	private String username;		//作者名称
	private Integer clicked;		//点击量->
	private Integer voted;			//点赞数->
	private Integer comments;		//评论数->
	private String title;			//标题-->>
	private Integer game_type;		//文章对应的游戏类型-->>
	private Integer article_type;	//文章内容类型-->>
	private String brief;			//简介-->>
	private String content;			//内容-->>
	private String cover;			//封面地址-->>
	private Date ctime;				//创建时间
	private Date utime;				//更新时间
	private Integer haschild;	//是否下面有子评论
	
	public Integer getStat() {
		return stat;
	}
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getClicked() {
		return clicked;
	}
	public void setClicked(Integer clicked) {
		this.clicked = clicked;
	}
	public Integer getComments() {
		return comments;
	}
	public void setComments(Integer comments) {
		this.comments = comments;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getGame_type() {
		return game_type;
	}
	public void setGame_type(Integer game_type) {
		this.game_type = game_type;
	}
	public Integer getArticle_type() {
		return article_type;
	}
	public void setArticle_type(Integer article_type) {
		this.article_type = article_type;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public Date getUtime() {
		return utime;
	}
	public void setUtime(Date utime) {
		this.utime = utime;
	}
	public static int getWiki() {
		return wiki;
	}
	public Integer getUp() {
		return up;
	}
	public void setUp(Integer up) {
		this.up = up;
	}
	public Integer getVoted() {
		return voted;
	}
	public void setVoted(Integer voted) {
		this.voted = voted;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getHaschild() {
		return haschild;
	}
	public void setHaschild(Integer haschild) {
		this.haschild = haschild;
	}
	
	
	
}
