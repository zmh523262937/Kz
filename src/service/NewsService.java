package service;

import java.util.List;

import entity.News;

public interface NewsService {
	public List<News> newslist();
	public News getNews(String id);
}
