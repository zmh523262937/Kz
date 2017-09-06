package servlet;

import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Cart;
import entity.Goods;
import entity.Order;
import entity.OrderDetail;
import entity.TempUser;
import entity.User;
import service.GoodsService;
import service.GoodsServiceImp;
import service.OrderService;
import service.OrderServiceImp;

/**
 * Servlet implementation class Success2
 */
@WebServlet("/success2.do")
public class Success2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Success2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GoodsService gs = new GoodsServiceImp();
		Cookie [] cookies =request.getCookies();
		List<Goods> goods = new ArrayList<>();
		List<String> accountList = new ArrayList<>();
		List<Cart> cart =new ArrayList<>();
		Cookie c=null;
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().contains("cartGood")) {
				c=cookies[i];
				goods.add(gs.get(c.getValue()));
				 
				for (int j = 0; j < cookies.length; j++) {
					if (cookies[i].getValue().equals(cookies[j].getName().substring(cookies[j].getName().lastIndexOf("t")+1, cookies[j].getName().length()))) {
						
						accountList.add(cookies[j].getValue());
						break;
					}
				
			}
		}
		}
		OrderService os = new OrderServiceImp();
		User user =(User)request.getSession().getAttribute("user");
		TempUser tu =(TempUser)request.getSession().getAttribute("tuser");
		String orderid = String.valueOf(UUID.randomUUID());
		String userid =user.getUserid();
		double totalPrice = 0;
		List<OrderDetail> list =new ArrayList<>();
		OrderDetail orderDetail = null;
		for (int i = 0; i < goods.size(); i++) {
			
			orderDetail = new OrderDetail(String.valueOf(UUID.randomUUID()), goods.get(i).getGid(),goods.get(i).getGsaleprice()*Integer.parseInt(accountList.get(i)),accountList.get(i), orderid);
			totalPrice+=orderDetail.getGsalprice();
			list.add(orderDetail);
		}
		String address = tu.getUaddress();
		String username = tu.getUname();
		String usertel = tu.getUtel();
		java.sql.Date orderDate = new java.sql.Date(new Date().getTime());
		Order order =new Order(orderid, userid, totalPrice,orderDate,address, username, usertel);
		os.insertOrder(order);
		for (int i = 0; i < list.size(); i++) {
			os.insertOrderDetaile(list.get(i));
		}
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().contains("cart")) {
				
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
			}
		}
		request.setAttribute("isEmpty", "0");
		request.getRequestDispatcher("/WEB-INF/success2.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
