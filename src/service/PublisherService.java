package service;

import entity.Publisher;

public interface PublisherService {
	public Publisher getPublisher(String pid);
	public String getPublisherByPname(String pname);
}
