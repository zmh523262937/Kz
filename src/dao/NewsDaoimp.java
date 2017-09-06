package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.News;
import util.DBUtil;

public class NewsDaoimp implements NewsDao{
	Connection conn= null;
	PreparedStatement ps =null;
	ResultSet rs =null;
	@Override
	public List<News> list() {
		conn=DBUtil.getConnection();
		String sql ="select * from t_news";
		List<News> list =new ArrayList<>();
		try {
			ps =conn.prepareStatement(sql);
			rs=ps.executeQuery();
			
			News news =null;
			while(rs.next()){
				news = new News(rs.getString("tid"), rs.getString("title"), rs.getString("tcontent"), rs.getDate("tpubdate"));
				list.add(news);
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
	public News getById(String id) {
		conn = DBUtil.getConnection();
		String sql ="select * from t_news where tid = ?";
		News news =null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs=ps.executeQuery();
			
			if (rs.next()) {
				news = new News(id, rs.getString("title"), rs.getString("tcontent"), rs.getDate("tpubdate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return news;
	}

}
