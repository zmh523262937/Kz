package entity;

public class TempUser {
	private String uname;
	private String uaddress;
	private String utel;
	
	public TempUser(String uname, String uaddress, String utel) {
		super();
		this.uname = uname;
		this.uaddress = uaddress;
		this.utel = utel;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUaddress() {
		return uaddress;
	}
	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}
	public String getUtel() {
		return utel;
	}
	public void setUtel(String utel) {
		this.utel = utel;
	}
	
	
}
