package service;

import java.util.List;

import dao.OrderDao;
import dao.OrderDaoImp;
import entity.Order;
import entity.OrderDetail;
import entity.PageInfo;

public class OrderServiceImp implements OrderService{
	OrderDao dao = new OrderDaoImp();
	@Override
	public void insertOrder(Order order) {
		// TODO Auto-generated method stub
		 dao.inserOrder(order);
	}

	@Override
	public void insertOrderDetaile(OrderDetail od) {
		// TODO Auto-generated method stub
		dao.insertOrderDetail(od);
	}

	@Override
	public PageInfo<Order> list(int pageSize, int pageIndex) {
		// TODO Auto-generated method stub
		return dao.list(pageSize, pageIndex);
	}

	@Override
	public List<OrderDetail> getOrderDetails(String orderid) {
		// TODO Auto-generated method stub
		return dao.getOrderDetails(orderid);
	}

}
