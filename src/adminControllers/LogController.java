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

import entity.Keyword;
import entity.Option;
import entity.PageInfo;
import service.GoodsService;
import service.GoodsServiceImp;
import service.LogService;
import service.LogServiceImp;
import service.SearchService;
import service.SearchServiceImp;

/**
 * Servlet implementation class LogController
 */
@WebServlet("/log.do")
public class LogController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogController() {
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
		LogService ls =new LogServiceImp();
		if (type!=null&&type.equals("list")) {
			
			String adminName = request.getParameter("adminName");
			int pageSize = Integer.parseInt(request.getParameter("rows"));
			int pageIndex=Integer.parseInt(request.getParameter("page"));
			PageInfo<Option> pageInfo =ls.getPageInfo(pageIndex, pageSize,adminName);
			Map<String, Object> map =new HashMap<>();
			map.put("total", pageInfo.getTotalNumber());
			map.put("rows", pageInfo.getData());
			Gson gson =new Gson();
			response.getWriter().write(gson.toJson(map));
		}
		if (type!=null&&type.equals("search")) {
	
			String key =request.getParameter("key");
			List<String> list = ls.Search(key);
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
