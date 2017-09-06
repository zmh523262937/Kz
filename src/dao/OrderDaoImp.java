package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Goods;
import entity.Option;
import entity.Order;
import entity.OrderDetail;
import entity.PageInfo;
import service.GoodsService;
import service.GoodsServiceImp;
import util.DBUtil;

public class OrderDaoImp implements OrderDao{
	Connection conn =null;
	PreparedStatement ps = null;
	ResultSet rs =null;
	@Override
	public void inserOrder(Order order) {
		// TODO Auto-generated method stub
	   conn = DBUtil.getConnection();
		String sql ="insert into t_order values(?,?,?,?,?,?,?)";
		Date date=new Date();
		java.sql.Date date2 = new java.sql.Date(date.getTime());
		
		try {
			 ps =conn.prepareStatement(sql);
			ps.setString(1, order.getOrderid());
			ps.setString(2, order.getUserid());
			ps.setDouble(3, order.getTotalprice());
			ps.setDate(4, date2);
			ps.setString(5, order.getAddress());
			ps.setString(6, order.getUsername());
			ps.setString(7, order.getUsertel());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void insertOrderDetail(OrderDetail od) {
		conn = DBUtil.getConnection();
		String sql ="insert into t_orderdetail values(?,?,?,?,?)";
		
		try {
			 ps =conn.prepareStatement(sql);
			ps.setString(1, od.getOrderdetailid());
			ps.setString(2, od.getGtitle());
			ps.setDouble(3, od.getGsalprice());
			ps.setString(4, od.getGnumber());
			ps.setString(5, od.getOrderid());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public PageInfo<Order> list(int pageSize, int pageIndex) {
		PageInfo<Order> pageInfo = new PageInfo<>();
		conn = DBUtil.getConnection();
		String sql;
		sql = "select t2.* from(select t1.*,rownum rw from (select * from t_order order by orderdate desc)t1 where rownum<?)t2 where rw>?";
		
		
		try {
			ps=conn.prepareStatement(sql);
			int endIndex =  pageSize*pageIndex+1;
			int startIndex=(pageIndex-1)*pageSize;
			
				ps.setInt(1, endIndex);
				ps.setInt(2, startIndex);
		
			
			
			List<Order> list =new ArrayList<>();
			rs=ps.executeQuery();
			Order order =null;
			while(rs.next()){
				order = new Order(rs.getString(1), rs.getString(2), rs.getDouble(3),rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7));
				list.add(order);
			}
			pageInfo.setData(list);
			int totalCount = getTotal();
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
	public int getTotal(){
		conn = DBUtil.getConnection();
		String  sql="select count(*) from t_order";
	
	
		int totalNumber=0;
		try {
			ps=conn.prepareStatement(sql);
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
	public List<OrderDetail> getOrderDetails(String orderid) {
		GoodsService gs = new GoodsServiceImp();
		System.out.println(orderid);
		conn = DBUtil.getConnection();
		String sql ="select * from t_orderdetail where orderid = ?";
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderid);
			rs = ps.executeQuery();
			OrderDetail od =null;
			Goods goods = null;
			while(rs.next()){
				goods=gs.get(rs.getString(2));
				if (goods==null) {
					continue;
				}
				od=new OrderDetail(rs.getString(1), goods.getGtitle(), rs.getDouble(3), rs.getString(4), rs.getString(5));
				
				list.add(od);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
