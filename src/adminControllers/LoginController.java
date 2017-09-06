package adminControllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.UserServiceImp;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/loginController.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("upassword");
		UserService us = new UserServiceImp();
		if (us.login(username, password)) {
				if (us.getUser(username).getUroleid().equals("116F9526C319462780B9CA72F6BB9B41")) {
					response.getWriter().write("1");
					return;
				
			}
			request.getSession().setAttribute("admin", username);
			request.getSession().setAttribute("password", password);
			request.getSession().setMaxInactiveInterval(60*60*24);
			response.getWriter().write("2");
			
		}else {
			response.getWriter().write("0");
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
