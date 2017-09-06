package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Cart;
import entity.Goods;
import service.GoodsService;
import service.GoodsServiceImp;
import service.UserService;
import service.UserServiceImp;

/**
 * Servlet implementation class Order
 */
@WebServlet("/order.do")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if (request.getSession().getAttribute("user")==null) {
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			return;
		}
		
		
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
		for (int i = 0; i < goods.size(); i++) {
			if (goods.get(i).getGid().equals(request.getParameter("good"))) {
				continue;
			}
			cart.add(new Cart(goods.get(i).getGid(), goods.get(i).getGtitle(), goods.get(i).getGsaleprice(), goods.get(i).getGinprice(), accountList.get(i)));
		}
		request.setAttribute("cart", cart);
		request.getRequestDispatcher("/WEB-INF/order.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
