package trade4fun.pojo;

/**
 * 游戏激活码
 */
public class Code {

    /**
     * 未使用
     */
    public static final Integer STAT_NOT_USED = 0;
    /**
     * 已使用
     */
    public static final Integer STAT_USED = 1;

    private Integer id;

    private Integer item_id;
    
    private String game_type;

    private Integer user_id;

    private String code;

    private Integer stat;

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

	public String getGame_type() {
		return game_type;
	}

	public void setGame_type(String game_type) {
		this.game_type = game_type;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getStat() {
		return stat;
	}

	public void setStat(Integer stat) {
		this.stat = stat;
	}


}