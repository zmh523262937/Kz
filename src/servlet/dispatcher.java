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
import entity.Catagory;
import entity.Goods;
import entity.Publisher;
import service.CatagoryService;
import service.CatagoryServiceImp;
import service.GoodsService;
import service.GoodsServiceImp;
import service.NewsService;
import service.NewsServiceImp;
import service.PublisherService;
import service.PublisherServiceImp;
import service.UserService;
import service.UserServiceImp;

/**
 * Servlet implementation class dispatcher
 */
@WebServlet("/dispatcher.do")
public class dispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dispatcher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=request.getParameter("type");
		String id=request.getParameter("id");
		NewsService dao =new NewsServiceImp();
		if (type!=null&&type.equals("news")) {
			request.setAttribute("news", dao.getNews(id));
			request.getRequestDispatcher("/WEB-INF/news.jsp").forward(request, response);
		}
		if (type!=null&&type.equals("goods")) {
			String gid = request.getParameter("gid");
			GoodsService gs =new GoodsServiceImp();
			Goods goods = gs.get(gid);
			
			CatagoryService cs = new CatagoryServiceImp();
			Catagory cate =cs.get(goods.getCid());
			PublisherService ps =new PublisherServiceImp();
			Publisher publisher =ps.getPublisher(goods.getPid());
			request.setAttribute("cate", cate);
			request.setAttribute("goods", goods);
			request.setAttribute("publisher", publisher);
			request.getRequestDispatcher("/WEB-INF/info.jsp").forward(request, response);
		}
		if (type!=null && type.equals("regist")) {
			request.getRequestDispatcher("/WEB-INF/regist.jsp").forward(request, response);
		}
		if (type!=null && type.equals("login")) {
			UserService us=new UserServiceImp();
			Cookie[]cookies =request.getCookies();
			Cookie cookie = null;
			
			for (int i=0;i<cookies.length;i++) {
				if (cookies[i].getName().equals("bookuser")) {
					cookie=cookies[i];
					break;
				}
			}
			
			if (cookie==null&&request.getSession().getAttribute("user")==null) {
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			}else {
				if (cookie!=null) {
					System.out.println(cookie.getValue());
					request.getSession().setAttribute("user",us.getUser(cookie.getValue()));
					request.getSession().setMaxInactiveInterval(60*60*24*1);
					request.setAttribute("user",us.getUser(cookie.getValue()) );
				}
				
				request.getRequestDispatcher("/WEB-INF/my.jsp").forward(request, response);
			}
			
		}
		if (type!=null &&type.equals("modify")) {
			
			
			request.getRequestDispatcher("WEB-INF/modifiuserinfo.jsp").forward(request, response);
		}
		if (type!=null&&type.equals("orderlist")) {
			request.getRequestDispatcher("WEB-INF/orderlist.jsp").forward(request, response);
		}
		if (type!=null &&type.equals("cart")) {
			GoodsService gs = new GoodsServiceImp();
			Cookie [] cookies =request.getCookies();
			if (cookies==null) {
				request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
				return;
			}
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
			request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
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
