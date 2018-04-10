package trade4fun.pojo.bbs;

import java.util.Date;
import java.util.List;

public class Comment {
	
	//文章状态
	public static final int STAT_PUBLIC = 0;	//待审核
	public static final int STAT_PRIVATE = 1;	//公开
	public static final int STAT_DEL = 2;		//已删除
	
	public static final int GRADE_COMMENT = 1;	//层主对文章评论
	public static final int GRADE_REPLY = 2;	//回复层主评论

	private Integer stat;		//state
	private Integer aid;		//上级	article id
	private Integer cid;		//本身	comment id
	private Integer uid; 		//user id
	private String username;	//用户名
	private Integer voted;		//点赞数
	private String content;		//评论内容
	private Date ctime;			//评论时间
	private Integer haschild;	//是否下面有子评论
	private List<Reply> reply_list;
	
	public Integer getStat() {
		return stat;
	}
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getVoted() {
		return voted;
	}
	public void setVoted(Integer voted) {
		this.voted = voted;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public List<Reply> getReply_list() {
		return reply_list;
	}
	public void setReply_list(List<Reply> reply_list) {
		this.reply_list = reply_list;
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
