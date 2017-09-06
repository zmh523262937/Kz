package dao;

import java.util.List;

import entity.Order;
import entity.OrderDetail;
import entity.PageInfo;

public interface OrderDao {
	public void inserOrder(Order order);
	public void insertOrderDetail (OrderDetail od);
	public PageInfo<Order> list (int pageSize,int pageIndex);
	public List<OrderDetail> getOrderDetails(String orderid);
}
