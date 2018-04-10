package trade4fun.pojo;

import java.util.Date;

public class Cart {
	
	private String id;
	
	private String user_id;
	
	private String game_itemid;
	
	private Date ctime;
	
	private Date utime;
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getGame_itemid() {
		return game_itemid;
	}

	public void setGame_itemid(String game_itemid) {
		this.game_itemid = game_itemid;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
}
