package adminControllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.Option;
import entity.Order;
import entity.OrderDetail;
import entity.PageInfo;
import service.LogService;
import service.LogServiceImp;
import service.OrderService;
import service.OrderServiceImp;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet(name = "OrderBackServlet", urlPatterns = { "/orderback.do" })
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type");
		OrderService os = new OrderServiceImp();
		if (type!=null&&type.equals("list")) {
			
			int pageSize = Integer.parseInt(request.getParameter("rows"));
			int pageIndex=Integer.parseInt(request.getParameter("page"));
			PageInfo<Order> pageInfo =os.list(pageSize, pageIndex);
			System.out.println(pageInfo);
			Map<String, Object> map =new HashMap<>();
			map.put("total", pageInfo.getTotalNumber());
			map.put("rows", pageInfo.getData());
			Gson gson =new Gson();
			response.getWriter().write(gson.toJson(map));
		}
		if (type!=null&&type.equals("detail")) {
			List<OrderDetail> list =os.getOrderDetails(request.getParameter("orderid"));
			System.out.println("list-->"+list);
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(list));
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
