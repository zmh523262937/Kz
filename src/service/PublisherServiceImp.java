package service;

import dao.PublisherDao;
import dao.PublisherDaoImp;
import entity.Publisher;

public class PublisherServiceImp implements PublisherService{
	PublisherDao dao =new PublisherDaoImp();
	@Override
	public Publisher getPublisher(String pid) {
		// TODO Auto-generated method stub
		return dao.getPublisher(pid);
	}
	@Override
	public String getPublisherByPname(String pname) {
		// TODO Auto-generated method stub
		return dao.getPublisherByPname(pname);
	}

}
