package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import entity.Catagory;
import util.DBUtil;

public class CatagoryDaoImp implements CatagoryDao{
	Connection conn= null;
	PreparedStatement ps =null;
	ResultSet rs =null;
	@Override
	public List<Catagory> list() {
				conn=DBUtil.getConnection();
				String sql ="select * from t_category order by ordervalue asc";
				List<Catagory> list =new ArrayList<>();
				try {
					ps =conn.prepareStatement(sql);
					rs=ps.executeQuery();
					
					Catagory catagory =null;
					while(rs.next()){
						catagory = new Catagory(rs.getString("cid"),rs.getString("cname"));
						list.add(catagory);
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
	public Catagory get(String cid) {
		conn = DBUtil.getConnection();
		String sql = "select * from t_category where cid=?";
		Catagory catagory =null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, cid);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				catagory = new Catagory(cid, rs.getString("cname"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return catagory;
	}
	@Override
	public String getCid(String cname) {
		conn = DBUtil.getConnection();
		String sql = "select cid from t_category where cname=?";
		String cid = null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, cname);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				cid = rs.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cid;
	}
	}


