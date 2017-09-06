package entity;

import java.util.Date;

public class News {
	private String tid;
	private String title;
	private String tContent;
	private Date tPubDate;
	public News(String tid, String title, String tContent, Date tPubDate) {
		super();
		this.tid = tid;
		this.title = title;
		this.tContent = tContent;
		this.tPubDate = tPubDate;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String gettContent() {
		return tContent;
	}
	public void settContent(String tContent) {
		this.tContent = tContent;
	}
	public Date gettPubDate() {
		return tPubDate;
	}
	public void settPubDate(Date tPubDate) {
		this.tPubDate = tPubDate;
	}
	@Override
	public String toString() {
		return "News [tid=" + tid + ", title=" + title + ", tContent=" + tContent + ", tPubDate=" + tPubDate + "]";
	}
	
}
