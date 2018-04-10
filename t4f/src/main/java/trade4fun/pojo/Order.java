package trade4fun.pojo;

import java.util.Date;

/**
 * 订单
 */
public class Order {
	
	/**
     * 未支付
     */
    public static final Integer STAT_NOT_PAY = 0;
    /**
     * 已支付
     */
    public static final Integer STAT_PAY = 1;
    /**
     * 取消
     */
    public static final Integer STAT_CANCEL = 2;

    private Integer id;//账单流水号

    private Integer user_id;//账单对应的用户的id

    private Double price;//账单结算价格

    private Date ctime;//账单创建时间

    private Date utime;//账单更新时间

    private Integer stat;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public Integer getStat() {
		return stat;
	}

	public void setStat(Integer stat) {
		this.stat = stat;
	}

}