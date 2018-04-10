package trade4fun.pojo;

import java.util.Date;

public class User {
	/**
     * 未验证，需要邮箱验证
     */
    public static final Integer STAT_NOT_VALIDATE = 0;
    /**
     * 正常
     */
    public static final Integer STAT_OK = 1;
    /**
     * 受限
     */
    public static final Integer STAT_RESTRICT = 2;
    /**
     * 已删除
     */
    public static final Integer STAT_DEL = 3;
	
	private Integer id;
	
	private String username;//用户名
	
	private String password;//密码
	
	private String email;//电子邮箱
	
	private Double money;//账户余额
	
	private String image;//用户头像
	
	private Integer stat;//用户状态	 0正常 1未验证 2受限 3已删除
	
	private Date ctime;//创建时间
	
    private Date utime;//更改时间
    
    private Integer cartNum;//购物车的物品数量
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getStat() {
		return stat;
	}
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	public Integer getCartNum() {
		return cartNum;
	}
	public void setCartNum(Integer cartNum) {
		this.cartNum = cartNum;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", stat="
				+ stat + ", money=" + money + ", image=" + image + ", ctime=" + ctime + ", utime=" + utime + "]";
	}
}
