package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.experimental.theories.FromDataPoints;

import com.sun.org.apache.bcel.internal.generic.Select;

import entity.PasswordAnswer;
import entity.User;
import util.DBUtil;

public class UserDaoImp implements UserDao{
	Connection conn = null;
	PreparedStatement ps= null;
	ResultSet rs =null;
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		String sql = "insert into t_user values(?,?,?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserid());
			ps.setString(2, user.getUemail());
			ps.setString(3, user.getUloginid());
			ps.setString(4, user.getUpassword());
			ps.setString(5, user.getUsex());
			ps.setString(6, user.getUaddress());
			ps.setString(7, user.getUtel());
			ps.setString(8, user.getUstateid());
			ps.setString(9, user.getUroleid());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public boolean login(String username,String password){
		conn =DBUtil.getConnection();
		String sql ="select * from t_user where uloginid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs=ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("upassword").equals(password)) {
					return true;
				}
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		String sql = "select * from t_user where uloginid=?";
		User user = null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			rs=ps.executeQuery();
			if (rs.next()) {
				user=new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public void toLife(String email) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		String sql = "update t_user set ustateid ='B5868B7A06E54DAEB19658343D3A2B28' where uemail =?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void updateUser(User user) {
		conn =DBUtil.getConnection();
		String sql="update t_user set upassword = ?,usex=?,utel=?,uaddress=? where userid=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, user.getUpassword());
			ps.setString(2, user.getUsex());
			ps.setString(3, user.getUtel());
			ps.setString(4, user.getUaddress());
			ps.setString(5, user.getUserid());
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void updateAnswer(PasswordAnswer passwordAnswer,String userid) {
		// TODO Auto-generated method stub
		conn =DBUtil.getConnection();
		String sql="update t_passwordanswer set aquestion = ?,answer=?,email=? where userid=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, passwordAnswer.getAquestion());
			ps.setString(2, passwordAnswer.getAnswer());
			ps.setString(3, passwordAnswer.getEmail());
			ps.setString(4, userid);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public PasswordAnswer getAnswer(String userid) {
		conn =DBUtil.getConnection();
		String sql="select * from t_passwordanswer where userid=?";
		PasswordAnswer pa = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			rs=ps.executeQuery();
			if (rs.next()) {
				pa=new PasswordAnswer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), userid);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pa;
	}
	@Override
	public boolean checkEmail(String email) {
		boolean isExist = false;
		conn = DBUtil.getConnection();
		String sql = "select * from t_user where uemail = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				isExist=true;
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isExist;
	}
	@Override
	public List<User> userList() {
		// TODO Auto-generated method stub
		conn=DBUtil.getConnection();
		String sql = "select * from t_user";
		User user=null;
		List<User> list =new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
				list.add(user);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	@Override
	public void removeById(String userid) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		String sql = "delete from t_user where userid =?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, userid);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void updateById(User user) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		String sql = "update t_user set uemail = ? , upassword = ?,usex = ?,uaddress = ?,utel=?,ustateid=? where userid =?";
		try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, user.getUemail());
			ps.setString(2, user.getUpassword());
			ps.setString(3, user.getUsex());
			ps.setString(4, user.getUaddress());
			ps.setString(5, user.getUtel());
			ps.setString(6, user.getUstateid());
			ps.setString(7, user.getUserid());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Override
	public User getUserByID(String userid) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		String sql = "select * from t_user where userid=?";
		User user = null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, userid);
			rs=ps.executeQuery();
			if (rs.next()) {
				user=new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public void toAdmin(String userid, String roleid) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		String sql = "update t_user set uroleid=? where userid =?";
		try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, roleid);
			ps.setString(2, userid);
	
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
