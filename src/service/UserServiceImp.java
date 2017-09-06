package service;

import java.util.List;

import dao.UserDao;
import dao.UserDaoImp;
import entity.PasswordAnswer;
import entity.User;

public class UserServiceImp implements UserService{
	UserDao dao =new UserDaoImp();
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		dao.addUser(user);
	}
	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		return dao.login(username, password);
	}
	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return dao.getUser(username);
	}
	@Override
	public void toLife(String email) {
		dao.toLife(email);
		
	}
	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		dao.updateUser(user);
	}
	@Override
	public void updateQuestion(PasswordAnswer passwordAnswer, String userid) {
		// TODO Auto-generated method stub
		dao.updateAnswer(passwordAnswer, userid);
	}
	@Override
	public PasswordAnswer getAnswer(String userid) {
		// TODO Auto-generated method stub
		return dao.getAnswer(userid);
	}
	@Override
	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		return dao.checkEmail(email);
	}
	@Override
	public List<User> userList() {
		// TODO Auto-generated method stub
		return dao.userList();
	}
	@Override
	public void removeById(String userid) {
		// TODO Auto-generated method stub
		dao.removeById(userid);
	}
	@Override
	public void updateUserById(User user) {
		// TODO Auto-generated method stub
		dao.updateById(user);
	}
	@Override
	public User getUserById(String userid) {
		// TODO Auto-generated method stub
		return dao.getUserByID(userid);
	}
	@Override
	public void toAdmin(String userid, String roleid) {
		// TODO Auto-generated method stub
		dao.toAdmin(userid, roleid);
	}

	
}
