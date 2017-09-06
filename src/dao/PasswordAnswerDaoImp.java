package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entity.PasswordAnswer;
import util.DBUtil;

public class PasswordAnswerDaoImp implements PasswordAnswerDao{
	Connection conn=null;
	PreparedStatement ps =null;
	@Override
	public void insertData(PasswordAnswer pa) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		String sql ="insert into t_passwordanswer values(?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pa.getAnswerid());
			ps.setString(2, pa.getAquestion());
			ps.setString(3, pa.getAnswer());
			ps.setString(4, pa.getEmail());
			ps.setString(5, pa.getUserid());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
