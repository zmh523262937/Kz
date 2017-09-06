package entity;

import java.sql.Date;

public class Option {
	private String adminName ;
	private String userName;
	private String optionName;
	private Date date ;
	
	public Option(String adminName, String userName, String optionName, Date date) {
		super();
		this.adminName = adminName;
		this.userName = userName;
		this.optionName = optionName;
		this.date = date;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
