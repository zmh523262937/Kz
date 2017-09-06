package service;

import java.util.List;

import entity.Keyword;

public interface SearchService {
	public List<Keyword> getList(String keyword);
}
