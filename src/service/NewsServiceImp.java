package service;

import java.util.List;

import dao.NewsDao;
import dao.NewsDaoimp;
import entity.News;

public class NewsServiceImp implements NewsService{
	NewsDao dao=null;
	public  NewsServiceImp() {
		// TODO Auto-generated constructor stub
		 dao = new NewsDaoimp();
	}
	@Override
	public List<News> newslist() {
		// TODO Auto-generated method stub
		
		return dao.list();
	}
	@Override
	public News getNews(String id) {
		// TODO Auto-generated method stub
		return dao.getById(id);
	}

}
