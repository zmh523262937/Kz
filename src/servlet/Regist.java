package servlet;

import java.io.IOException;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.PasswordAnswer;
import entity.User;
import service.PasswordAnswerServiceImp;
import service.PasswordAnwserService;
import service.UserService;
import service.UserServiceImp;
import util.MailUtil;

/**
 * Servlet implementation class Regist
 */
@WebServlet("/Regist.do")
public class Regist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Regist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		if (!request.getParameter("ucheck").equalsIgnoreCase(request.getSession().getAttribute("codes").toString())) {
			request.getRequestDispatcher("/WEB-INF/regist.jsp").forward(request, response);
			return;
		}
		String uname = request.getParameter("uname");
		String upassword=request.getParameter("upassword");
		String email=request.getParameter("uemail");
		String tel= request.getParameter("utel");
		String sex=request.getParameter("usex");
		String addr=request.getParameter("uaddr");
		String userid=UUID.randomUUID().toString();
		String ustateid="36D0F394FC6A45829385E0BE11208263";
		String uroleid="116F9526C319462780B9CA72F6BB9B41";
		UserService us = new UserServiceImp();
		us.addUser(new User(userid, email, uname, upassword, sex, addr, tel, ustateid, uroleid));
		String answerid = UUID.randomUUID().toString();
		String aquestion=request.getParameter("aquestion");
		String answer =request.getParameter("answer");
		String backupemail = request.getParameter("bkemail");
		PasswordAnswer pa =new PasswordAnswer(answerid, aquestion, answer, backupemail, userid);
		PasswordAnwserService pas = new PasswordAnswerServiceImp();
		pas.insertData(pa);
		try {
			MailUtil.sendMail(email, "这是一封激活邮件，请点击<a href='http://localhost:8080/BookShop/checkServlet.do?email="+email+"'>激活</a>");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/registersuccess.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
