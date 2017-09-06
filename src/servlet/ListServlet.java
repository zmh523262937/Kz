package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Catagory;
import entity.Goods;
import entity.PageInfo;
import service.CatagoryServiceImp;
import service.GoodsService;
import service.GoodsServiceImp;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet("/list.do")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pageIndex=request.getParameter("pageIndex");
		if (pageIndex==null) {
			pageIndex="1";
		}
		
		String cid = request.getParameter("cid");
		int pageSize = 10;
		GoodsService dao =new GoodsServiceImp();
		PageInfo<Goods> pageInfo = dao.getPageInfo(Integer.parseInt(pageIndex), pageSize, cid);
		Catagory cate = new CatagoryServiceImp().get(cid);
		request.setAttribute("cid", request.getParameter("cid"));
		request.setAttribute("cate", cate);
		request.setAttribute("pageInfo", pageInfo);
		request.getRequestDispatcher("/WEB-INF/list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
