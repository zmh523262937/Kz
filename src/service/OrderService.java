package service;

import java.util.List;

import entity.Order;
import entity.OrderDetail;
import entity.PageInfo;

public interface OrderService {
	public void insertOrder(Order order);
	public void insertOrderDetaile(OrderDetail od);
	public PageInfo<Order> list(int pageSize,int pageIndex);
	public List<OrderDetail> getOrderDetails(String orderid);
}
