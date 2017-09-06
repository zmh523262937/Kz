package dao;

import java.util.List;

import entity.News;

public interface NewsDao {
	public List<News> list();
	public News getById(String id);
}
