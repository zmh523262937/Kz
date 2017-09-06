package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Goods;
import entity.PageInfo;
import util.DBUtil;

public class GoodsDaoImp implements GoodsDao{
	Connection conn =null;
	PreparedStatement ps =null;
	ResultSet rs= null;
	@Override
	public PageInfo<Goods> pageInfo(int pageIndex, int pageSize,String cid) {
		conn=DBUtil.getConnection();
		String sql;
		if (!cid.equals("17")) {
			 sql ="select t2.* from (select t1.*,rownum rm from (select * from t_goods where cid = ?) t1 where rownum<=?)t2 where rm>=?";
		}
		else   {
			 sql = "select t2.* from (select t1.*,rownum rm from (select * from t_goods ) t1 where rownum<=?)t2 where rm>=? ";
		}
		
//		int endIndex = pageSize*(pageIndex+1);
//		int startIndex=pageIndex*pageSize+1;  从0开始的时候，实行ajax分页
		int endIndex = pageSize*(pageIndex);
		int startIndex=(pageIndex-1)*pageSize+1;
		PageInfo<Goods> pageInfo = new PageInfo<>();
		List<Goods> list =new ArrayList<>();
		try {
			ps =conn.prepareStatement(sql);
			if (!cid.equals("17")) {
				ps.setString(1, cid);
				ps.setInt(2, endIndex);
				ps.setInt(3, startIndex);
			}else {
				ps.setInt(1, endIndex);
				ps.setInt(2, startIndex);
			}
		
			rs=ps.executeQuery();
		
			Goods goods =null;
			while(rs.next()){
				goods=new Goods(rs.getString("gid"),rs.getString("gtitle") , rs.getString("gauthor"), rs.getDouble("gsaleprice"), rs.getDouble("ginprice"), rs.getString("gdesc"), rs.getString("gimg"), rs.getInt("gclicks"), rs.getString("cid"), rs.getString("pid"));
				list.add(goods);
			}
			
			
			rs.close();
			ps.close();
			conn.close();
			pageInfo.setData(list);
			pageInfo.setFirstPage(pageIndex==1);
			GoodsDao dao = new GoodsDaoImp();
			int totalCount=dao.pageCount(cid);
			pageInfo.setTotalNumber(totalCount);
			pageInfo.setTotalPage(totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1);
			pageInfo.setLastPage(pageIndex==pageInfo.getTotalPage());
			pageInfo.setPageIndex(pageIndex);
			pageInfo.setPageSize(pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		return pageInfo;
	}
	@Override
	public int pageCount(String cid) {
		if (cid==null) {
			cid = "1";
		}
		conn = DBUtil.getConnection();
		String sql;
		int totalNumber=0;
		try {
			if (cid.equals("17")) {
				sql ="select count(*) from t_goods";
				ps=conn.prepareStatement(sql);
			}else {
				sql= "select count(*) from t_goods where cid = ?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, cid);
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
	public Goods getGoods(String gid) {
		conn =DBUtil.getConnection();
		String sql ="select * from t_goods where gid=?";
		Goods goods = null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, gid);
			rs=ps.executeQuery();
			
			if (rs.next()) {
				goods =new Goods(rs.getString("gid"), rs.getString("gtitle"), rs.getString("gauthor"), rs.getDouble("gsaleprice"), rs.getDouble("ginprice"), rs.getString("gdesc"), rs.getString("gimg"), rs.getInt("gclicks"), rs.getString("cid"), rs.getString("pid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goods;
	}
	@Override
	public void removeGoodsById(String gid) {
		// TODO Auto-generated method stub
		System.out.println();
		conn =DBUtil.getConnection();
		String sql ="delete  from t_goods where gid=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, gid);
			rs=ps.executeQuery();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void imgDownload(Goods goods) {
		// TODO Auto-generated method stub
		String start =goods.getGimg();
		
		String end = "D:/新建文件夹/BookShop/WebContent/bookcover";
	}
	@Override
	public void insertGood(Goods goods) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		String sql ="insert into t_goods values(?,?,?,?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, goods.getGid());
			ps.setString(2, goods.getGtitle());
			ps.setString(3, goods.getGauthor());
			ps.setDouble(4, goods.getGsaleprice());
			ps.setDouble(5, goods.getGinprice());
			ps.setString(6, goods.getGdesc());
			ps.setString(7, goods.getGimg());
			ps.setInt(8, goods.getGclicks());
			ps.setString(9, goods.getCid());
			ps.setString(10, goods.getPid());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
