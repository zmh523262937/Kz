

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import dao.GoodsDao;
import dao.GoodsDaoImp;
import entity.Goods;
import entity.PageInfo;
import entity.User;
import service.UserService;
import service.UserServiceImp;
import sun.print.resources.serviceui;
import util.DBUtil;

public class TestGood {

	@Test	
	public void testgood(){
	String ine = "aaa";

	}
	@Test
	public void testUser(){
		UserService us = new UserServiceImp();
		List<User> list =us.userList();
		System.out.println(list.toString());
	}
}
