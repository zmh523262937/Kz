package service;

import java.util.List;

import entity.Option;
import entity.PageInfo;

public interface LogService {
	public PageInfo<Option> getPageInfo(int pageIndex,int pageSize,String adminName);
	public List<String> Search(String key);
}
