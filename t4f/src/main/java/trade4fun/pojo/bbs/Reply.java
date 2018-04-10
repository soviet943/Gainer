package trade4fun.pojo.bbs;

import java.util.Date;

public class Reply {
	
	private Integer stat;		//state
	private Integer rid;		//本身reply_id
	private Integer cid;		//上级comment_id
	private Integer aid;		//上上级article_id
	private Integer uid;		//user_id
	private String username;	//用户名
	private Integer voted;		//点赞数
	private String content;		//评论内容
	private Date ctime;
	
	public Integer getStat() {
		return stat;
	}
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getVoted() {
		return voted;
	}
	public void setVoted(Integer voted) {
		this.voted = voted;
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
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
