package service;

import java.util.List;

import org.junit.experimental.categories.Categories;

import entity.Catagory;


public interface CatagoryService {
	public List<Catagory> newslist();
	public Catagory get(String cid);
	public String getCid(String cname);
}