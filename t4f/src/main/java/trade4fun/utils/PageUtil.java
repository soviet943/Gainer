package trade4fun.utils;
//分页工具
public class PageUtil {
	private int num;//记录的总数
	private int pages;//页的总数
	private int current;//当前页数
	private int size;//每页显示记录数
	private int startPos;//从数据库中查询时开始的条数
	
	public PageUtil(int num, int current, int size) {
		this.num = num;
		this.size = size;
		this.pages = num % size == 0 ? num / size : num / size + 1;
		if(current <= 0) {//小于第一页的看做第一页
			this.current = 1;
		} else {//大于最后一页的看做最后一页
			this.current = current > pages ? pages : current;
		}
		this.startPos = this.size * (this.current - 1) < 0 ? 0 : this.size * (this.current - 1);
	}
	
	//默认每页大小为15
	public PageUtil(int num, int current) {
		this.num = num;
		this.size = 15;
		this.pages = num % size == 0 ? num / size : num / size + 1;
		if(current <= 0) {//小于第一页的看做第一页
			this.current = 1;
		} else {//大于最后一页的看做最后一页
			this.current = current > pages ? pages : current;
		}
		this.startPos = this.size * (this.current - 1) < 0 ? 0 : this.size * (this.current - 1);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getStartPos() {
		return startPos;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}
	
}
