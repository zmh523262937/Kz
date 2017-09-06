package dao;

import java.util.List;

import entity.Keyword;

public interface SearchDao {
	public List<Keyword> getList(String keyword);
}
