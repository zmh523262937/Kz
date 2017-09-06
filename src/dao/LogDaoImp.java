package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Goods;
import entity.Keyword;
import entity.Option;
import entity.PageInfo;
import util.DBUtil;

public class LogDaoImp implements LogDao {
	Connection conn=null;
	PreparedStatement ps =null;
	ResultSet rs = null;
	@Override
	public PageInfo<Option> getList(int pageIndex, int pageSize,String adminName) {
		// TODO Auto-generated method stub
		PageInfo<Option> pageInfo = new PageInfo<>();
		conn = DBUtil.getConnection();
		String sql;
		if (adminName.equals("")) {
			sql = "select t2.* from(select t1.*,rownum rw from (select * from t_option order by optiondate desc)t1 where rownum<?)t2 where rw>?";
		}else {
			sql = "select t2.* from(select t1.*,rownum rw from (select * from t_option where adminname like ? order by optiondate desc)t1 where rownum<?)t2 where rw>?";
		}
		
		try {
			ps=conn.prepareStatement(sql);
			int endIndex =  pageSize*pageIndex+1;
			int startIndex=(pageIndex-1)*pageSize;
			if (adminName.equals("")) {
				ps.setInt(1, endIndex);
				ps.setInt(2, startIndex);
			}else {
				ps.setString(1, "%"+adminName+"%");
				ps.setInt(2, endIndex);
				ps.setInt(3, startIndex);
			}
			
			
			List<Option> list =new ArrayList<>();
			rs=ps.executeQuery();
			Option option = null;
			while(rs.next()){
				option = new Option(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4));
				list.add(option);
			}
			pageInfo.setData(list);
			int totalCount = getTotal(adminName);
			int totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
			pageInfo.setPageIndex(pageIndex);
			pageInfo.setPageSize(pageSize);
			pageInfo.setTotalNumber(totalCount);
			pageInfo.setTotalPage(totalPage);
			pageInfo.setFirstPage(pageIndex==1);
			pageInfo.setLastPage(pageIndex==totalPage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pageInfo;
	}
	public int getTotal(String adminName){
		
		conn = DBUtil.getConnection();
		String sql;
		if (adminName.equals("")) {
			 sql="select count(*) from t_option";
		}else {
			 sql="select count(*) from t_option where adminname like ?";
		}
	
		int totalNumber=0;
		try {
			ps=conn.prepareStatement(sql);
			if (!adminName.equals("")) {
				ps.setString(1, "%"+adminName+"%");
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				totalNumber = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return totalNumber;
	}
	@Override
	public List<String> search(String key) {
		Connection conn =DBUtil.getConnection();
		List<String>list =new ArrayList<>();
		String k ;
		String sql ="select t1.*,rownum from (select distinct adminname from t_option where adminname like ?)t1 where rownum<6";
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ps.setString(1, "%"+key+"%");
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				k = rs.getString(1);
				list.add(k);
			}
			System.out.println(list.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
}
