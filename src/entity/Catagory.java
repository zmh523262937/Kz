package entity;

public class Catagory {
	private String cid;
	private String cname;
	public Catagory(String cid, String cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}
	public Catagory(){
		
	}
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "Catagory [cid=" + cid + ", cname=" + cname + "]";
	}
	
}
