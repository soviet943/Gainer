package trade4fun.pojo;

import java.util.Date;

/**
 * 订单明细
 */
public class Orderitem {
	
	private Integer id;
	
	private Integer item_id;
	
	private String item_name;
	
	private String item_img;
	
	private String game_type;
	
	private double price;
	
	private Integer code_id;
	
	private Date ctime;
	
	private Date utime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getGame_type() {
		return game_type;
	}

	public void setGame_type(String game_type) {
		this.game_type = game_type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getCode_id() {
		return code_id;
	}

	public void setCode_id(Integer code_id) {
		this.code_id = code_id;
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

	public String getItem_img() {
		return item_img;
	}

	public void setItem_img(String item_img) {
		this.item_img = item_img;
	}

}