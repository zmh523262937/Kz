package service;

import java.util.List;

import dao.SearchDao;
import dao.SearchDaoImp;
import entity.Keyword;

public class SearchServiceImp implements SearchService {
	SearchDao dao =new SearchDaoImp();
	@Override
	public List<Keyword> getList(String keyword) {
		// TODO Auto-generated method stub
		return dao.getList(keyword);
	}

}
