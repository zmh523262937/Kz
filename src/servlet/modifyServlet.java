package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.PasswordAnswer;
import entity.User;
import service.UserService;
import service.UserServiceImp;

/**
 * Servlet implementation class modifyServlet
 */
@WebServlet("/modify.do")
public class modifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		if (!request.getSession().getAttribute("codes").toString().equalsIgnoreCase(request.getParameter("check"))) {
			request.getRequestDispatcher("/WEB-INF/modifiuserinfo.jsp").forward(request, response);
			return ;
		}
		User user =(User) request.getSession().getAttribute("user");
		System.out.println(user);
		user.setUpassword(request.getParameter("password"));
		user.setUsex(request.getParameter("sex"));
		user.setUtel(request.getParameter("tel"));
		user.setUaddress(request.getParameter("address"));
		UserService us = new UserServiceImp();
		us.updateUser(user);
		PasswordAnswer pa = us.getAnswer(user.getUserid());
		pa.setAquestion(request.getParameter("question"));
		pa.setAnswer(request.getParameter("answer"));
		pa.setEmail(request.getParameter("bkemail"));
		us.updateQuestion(pa, user.getUserid());
		request.getRequestDispatcher("/WEB-INF/modifySuccess.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
