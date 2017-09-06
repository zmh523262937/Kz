package dao;

import java.util.List;

import entity.PasswordAnswer;
import entity.User;

public interface UserDao {
	public void addUser(User user);
	public boolean login(String username , String password);
	public User getUser(String username);
	public void toLife(String email);
	public void updateUser(User user);
	public void updateAnswer(PasswordAnswer passwordAnswer,String userid);
	public PasswordAnswer getAnswer(String userid);
	public boolean checkEmail(String email);
	public List<User> userList();
	public void removeById(String userid);
	public void updateById(User user);
	public User getUserByID(String userid);
	public void toAdmin(String userid,String roleid);
}
