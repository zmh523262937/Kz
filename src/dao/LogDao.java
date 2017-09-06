package dao;

import java.util.List;

import entity.Option;
import entity.PageInfo;

public interface LogDao {
	public PageInfo<Option> getList(int pageIndex, int pageSize,String adminName);
	public List<String> search(String key);
}
