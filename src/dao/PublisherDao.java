package dao;

import entity.Publisher;

public interface PublisherDao {
	public Publisher getPublisher(String pid);
	public String getPublisherByPname(String pname);
}
