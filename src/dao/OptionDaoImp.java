package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entity.Option;
import util.DBUtil;

public class OptionDaoImp implements OptionDao{
	Connection conn=null;
	PreparedStatement ps =null;
	
	@Override
	public void insertOption(Option option) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		String sql ="insert into t_option values (?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, option.getAdminName());
			ps.setString(2, option.getUserName());
			ps.setString(3, option.getOptionName());
			ps.setDate(4, option.getDate());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
