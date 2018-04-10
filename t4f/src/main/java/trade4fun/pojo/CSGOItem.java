package trade4fun.pojo;

import java.util.Date;

public class CSGOItem {
	private int id;
	private String type;
	private String item_type;
	private String item_name;
	private double price;
	private String img;
	private String exterior;
	private String quality;
	private String rarity;
	private Date createTime;
	private Date updateTime;
	public String getExterior() {
		return exterior;
	}
	public void setExterior(String exterior) {
		this.exterior = exterior;
	}
	private int sales;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getItem_type() {
		return item_type;
	}
	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getRarity() {
		return rarity;
	}
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
