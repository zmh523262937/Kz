package service;

import java.util.List;

import dao.LogDao;
import dao.LogDaoImp;
import entity.Option;
import entity.PageInfo;

public class LogServiceImp implements LogService{
	LogDao dao = new LogDaoImp();
	@Override
	public PageInfo<Option> getPageInfo(int pageIndex, int pageSize,String adminName) {
		// TODO Auto-generated method stub
		return dao.getList(pageIndex, pageSize,adminName);
	}
	@Override
	public List<String> Search(String key) {
		// TODO Auto-generated method stub
		return dao.search(key);
	}

}
