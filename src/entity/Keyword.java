package entity;

public class Keyword {
	private String keyword;
	private String val;
	
	public Keyword(String keyword, String val) {
		super();
		this.keyword = keyword;
		this.val = val;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	
}
