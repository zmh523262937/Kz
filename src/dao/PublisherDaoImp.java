package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Publisher;
import util.DBUtil;

public class PublisherDaoImp implements PublisherDao {
	Connection conn= null;
	PreparedStatement ps = null;
	ResultSet rs=null;

	@Override
	public Publisher getPublisher(String pid) {
		conn = DBUtil.getConnection();
		String sql="select * from t_publisher where pid =?";
		Publisher publisher =null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, pid);
			rs=ps.executeQuery();
			
			if (rs.next()) {
				publisher=new Publisher(pid, rs.getString("pname"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return publisher;
	}

	@Override
	public String getPublisherByPname(String pname) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		String sql="select pid from t_publisher where pname =?";
		String pid = null ;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, pname);
			rs=ps.executeQuery();
			
			if (rs.next()) {
				pid = rs.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(pid);
		return pid;
	}

}
