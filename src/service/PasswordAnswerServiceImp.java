package service;

import dao.PasswordAnswerDao;
import dao.PasswordAnswerDaoImp;
import entity.PasswordAnswer;

public class PasswordAnswerServiceImp implements PasswordAnwserService{

	@Override
	public void insertData(PasswordAnswer ps) {
		// TODO Auto-generated method stub
		PasswordAnswerDao dao = new PasswordAnswerDaoImp();
		dao.insertData(ps);
	}

}
