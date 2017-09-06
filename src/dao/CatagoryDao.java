package dao;

import java.util.List;

import entity.Catagory;


public interface CatagoryDao {
	public List<Catagory> list();
	public Catagory get(String cid);
	public String getCid(String cname);
}
