package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.Goods;
import entity.PageInfo;
import service.GoodsService;
import service.GoodsServiceImp;

/**
 * Servlet implementation class PaginationServlet
 */
@WebServlet("/page.do")
public class PaginationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaginationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cid = request.getParameter("cid");
		if (cid==null) {
			cid="1";
		}
		String pageIndex=request.getParameter("pageIndex");
		if (pageIndex==null) {
			pageIndex="0";
		}
		String pageSize =request.getParameter("pageSize");
			if (pageSize==null) {
				pageSize="10";
			}
		
		GoodsService gs = new GoodsServiceImp();
		PageInfo<Goods> pageInfo =gs.getPageInfo(Integer.parseInt(pageIndex), Integer.parseInt(pageSize), cid);
		Gson gson =new Gson();
		response.getWriter().write(gson.toJson(pageInfo));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
