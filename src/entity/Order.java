package entity;

import java.sql.Date;

public class Order {
	private String orderid;
	private String userid;
	private double totalprice;
	private Date orderDate;
	private String address;
	private String username;
	private String usertel;
	public Order(String orderid, String userid, double totalprice,Date orderDate,String address, String username,
			String usertel) {
		super();
		this.orderid = orderid;
		this.userid = userid;
		this.totalprice = totalprice;
		this.orderDate=orderDate;
		this.address = address;
		this.username = username;
		this.usertel = usertel;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsertel() {
		return usertel;
	}
	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}
	
}
