package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.NewsService;
import service.NewsServiceImp;
import service.UserService;
import service.UserServiceImp;

/**
 * Servlet implementation class NewsServlet
 */
@WebServlet("/NewsServlet.do")
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserService us =new UserServiceImp();
		Cookie [] cookies =request.getCookies();
		Cookie cookie =null;
		if (cookies!=null) {
			
		
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("bookuser")) {
				cookie=cookies[i];
			}
		}
		if (cookie!=null) {
			request.getSession().setAttribute("user", us.getUser(cookie.getValue()));
			request.getSession().setMaxInactiveInterval(60*60*24);
		}
		}
		NewsService dao = new NewsServiceImp();
		request.setAttribute("news", dao.newslist());
		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
