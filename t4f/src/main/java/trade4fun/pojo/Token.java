package trade4fun.pojo;

import java.util.Date;

/**
 * token，用来标识一个用户
 */
public class Token {

    /**
     * 正常状态
     */
    public static final Integer STAT_OK = 0;
    /**
     * token已过期
     */
    public static final Integer STAT_EXPIRED = 1;

    private Integer user_id;

    private String token;

    private Date expired_time;

    private String ip;
    
    private String device;

    private Date create_time;

    private Integer stat;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpired_time() {
		return expired_time;
	}

	public void setExpired_time(Date expired_time) {
		this.expired_time = expired_time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}


	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getStat() {
		return stat;
	}

	public void setStat(Integer stat) {
		this.stat = stat;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

}