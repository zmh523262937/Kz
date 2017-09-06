package service;

import java.util.List;

import org.junit.experimental.categories.Categories;

import dao.CatagoryDao;
import dao.CatagoryDaoImp;
import entity.Catagory;

public class CatagoryServiceImp implements CatagoryService{
	CatagoryDao dao=null;
	public   CatagoryServiceImp() {
		dao = new CatagoryDaoImp();
	}
	@Override
	public List<Catagory> newslist() {
		// TODO Auto-generated method stub
		return dao.list();
	}
	@Override
	public Catagory get(String cid) {
		// TODO Auto-generated method stub
		return dao.get(cid);
	}
	@Override
	public String getCid(String cname) {
		// TODO Auto-generated method stub
		return dao.getCid(cname);
	}

}
