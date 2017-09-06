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

import org.apache.catalina.connector.Request;

import entity.Cart;
import entity.Goods;
import oracle.net.aso.r;
import service.GoodsService;
import service.GoodsServiceImp;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart.do")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("option")!=null&&request.getParameter("option").equals("add")) {
			
		
		GoodsService gs = new GoodsServiceImp();
		Cookie [] cookies =request.getCookies();
		List<Goods> goods = new ArrayList<>();
		List<String> accountList = new ArrayList<>();
		List<Cart> cart =new ArrayList<>();
		int [] account=null;
		Cookie c=null;
		boolean isExist=false;
		if (cookies==null) {
			goods.add(gs.get(request.getParameter("good")));
			accountList.add("1");
			Cookie newcookie = new Cookie("cartGood"+request.getParameter("good"), request.getParameter("good"));
			Cookie newcookie2=new Cookie("cartAccount"+request.getParameter("good"), "1");
			newcookie.setMaxAge(60*60*24*365);
			newcookie2.setMaxAge(60*60*24*365);
			response.addCookie(newcookie);
			response.addCookie(newcookie2);
			cart.add(new Cart(goods.get(0).getGid(), goods.get(0).getGtitle(), goods.get(0).getGsaleprice(), goods.get(0).getGinprice(), accountList.get(0)));
			request.setAttribute("cart", cart);
			request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
			return;
		}
	
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
		if (c==null) {
			goods.add(gs.get(request.getParameter("good")));
			accountList.add("1");
			Cookie newcookie = new Cookie("cartGood"+request.getParameter("good"), request.getParameter("good"));
			Cookie newcookie2=new Cookie("cartAccount"+request.getParameter("good"), "1");
			newcookie.setMaxAge(60*60*24*365);
			newcookie2.setMaxAge(60*60*24*365);
			response.addCookie(newcookie);
			response.addCookie(newcookie2);
			cart.add(new Cart(goods.get(0).getGid(), goods.get(0).getGtitle(), goods.get(0).getGsaleprice(), goods.get(0).getGinprice(), accountList.get(0)));
			request.setAttribute("cart", cart);
			request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
			return;
		}
		account =new int [accountList.size()];
		for (int i = 0; i < account.length; i++) {
			account[i]=Integer.parseInt(accountList.get(i));
		}
		for (int i = 0; i < goods.size(); i++) {
			if (goods.get(i).getGid().equals(request.getParameter("good"))) {
				 account[i]++;
				 Cookie cookie =new Cookie("cartAccount"+request.getParameter("good"), String.valueOf(account[i]));
				 cookie.setMaxAge(60*60*24*365);
				 response.addCookie(cookie);
				 accountList.set(i,String.valueOf(account[i]));
				 isExist=true;
				 break;
			}else {
				Cookie newcookie = new Cookie("cartGood"+request.getParameter("good"), request.getParameter("good"));
				Cookie newcookie2=new Cookie("cartAccount"+request.getParameter("good"), "1");
				newcookie.setMaxAge(60*60*24*365);
				newcookie2.setMaxAge(60*60*24*365);
				response.addCookie(newcookie);
				response.addCookie(newcookie2);
			}
		}
		if (!isExist) {
			goods.add(gs.get(request.getParameter("good")));
			accountList.add("1");
		}
		for (int i = 0; i < goods.size(); i++) {
			cart.add(new Cart(goods.get(i).getGid(), goods.get(i).getGtitle(), goods.get(i).getGsaleprice(), goods.get(i).getGinprice(), accountList.get(i)));
		}
		
		request.setAttribute("cart", cart);
		
		request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
		}
		if (request.getParameter("option")!=null&&request.getParameter("option").equals("remove")) {
			Cookie [] cookies =request.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("cartGood"+request.getParameter("good"))||cookies[i].getName().equals("cartAccount"+request.getParameter("good"))) {
					
						cookies[i].setMaxAge(0);
					response.addCookie(cookies[i]);
					
				}
			
			}
			request.setAttribute("good", request.getAttribute("good"));
			request.getRequestDispatcher("/dispatcher.do?type=cart").forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
