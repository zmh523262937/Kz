package service;

import dao.OptionDao;
import dao.OptionDaoImp;
import entity.Option;

public class OptionServiceImp implements OptionService{
	OptionDao od = new OptionDaoImp();
	@Override
	public void insertOption(Option option) {
		// TODO Auto-generated method stub
		od.insertOption(option);
	}
	
}
