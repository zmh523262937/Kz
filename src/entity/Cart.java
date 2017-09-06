package entity;

public class Cart {
	private String gid;
	private String gtitle;
	private double saleprice;
	private double inprice;
	private String amount;
	
	public Cart(String gid, String gtitle, double saleprice, double inprice, String amount) {
		super();
		this.gid = gid;
		this.gtitle = gtitle;
		this.saleprice = saleprice;
		this.inprice = inprice;
		this.amount = amount;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getGtitle() {
		return gtitle;
	}
	public void setGtitle(String gtitle) {
		this.gtitle = gtitle;
	}
	public double getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(double saleprice) {
		this.saleprice = saleprice;
	}
	public double getInprice() {
		return inprice;
	}
	public void setInprice(double inprice) {
		this.inprice = inprice;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
