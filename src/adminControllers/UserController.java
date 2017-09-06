package adminControllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.Option;
import entity.User;
import oracle.net.aso.l;
import service.OptionService;
import service.OptionServiceImp;
import service.UserService;
import service.UserServiceImp;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/usercontroller.do")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		UserService us = new UserServiceImp();
		Option option =null;
		OptionService os = new OptionServiceImp();
		if (type!=null&&type.equals("list")) {
			
			List<User> list = us.userList();
			System.out.println(list.get(0));
			Gson gson =new Gson();
			String json =gson.toJson(list);
			response.getWriter().write(json);
			return;
		}
		
		
		
		if (type!=null&&type.equals("remove")) {
			String userid =request.getParameter("userid");
			java.sql.Date date = new java.sql.Date(new Date().getDate());	
			option = new Option((String)request.getSession().getAttribute("admin"),us.getUserById(userid).getUloginid() , type, date);
			us.removeById(userid);
			os.insertOption(option);
			response.getWriter().write("1");
			return;
		}
		if (type!=null&&type.equals("add")) {
			String userid = UUID.randomUUID().toString();
			String email =request.getParameter("uemail");
			System.out.println(email);
			String uloginid=request.getParameter("uloginid");
			String password=request.getParameter("upassword");
			String usex=request.getParameter("sex");
			System.out.println(usex);
			String address=request.getParameter("address");
			String tel=request.getParameter("tel");
			String stateid="B5868B7A06E54DAEB19658343D3A2B28";
			String roleid="116F9526C319462780B9CA72F6BB9B41";
			User user =new User(userid, email, uloginid, password, usex, address, tel, stateid, roleid);
			us.addUser(user);
			java.sql.Date date = new java.sql.Date(new Date().getDate());
			option = new Option((String)request.getSession().getAttribute("admin"),uloginid , type, date);
			os.insertOption(option);
			response.getWriter().write("1");
			return;
		}
		if (type!=null&&type.equals("update")) {
			
			User user =new User(request.getParameter("userid"), request.getParameter("uemail"), request.getParameter("uloginid"), request.getParameter("upassword"), request.getParameter("sex"), request.getParameter("address"), request.getParameter("tel"), request.getParameter("userstate"), null);
			us.updateUserById(user);
			java.sql.Date date = new java.sql.Date(new Date().getDate());
			option = new Option((String)request.getSession().getAttribute("admin"),user.getUloginid() , type, date);
			os.insertOption(option);
			response.getWriter().write("1");
			return;
		}
		if (type!=null&&type.equals("toAdmin")) {
			if (request.getSession().getAttribute("password").equals(request.getParameter("password"))) {
				String userid = request.getParameter("userid");
				String roleid = "377D0AE90A804D289F301FB085A6E9AA";
				us.toAdmin(userid,roleid);
				System.out.println(String.valueOf(request.getSession().getAttribute("admin")));
				System.out.println(request.getParameter("username"));
				System.out.println(new java.sql.Date(new Date().getDate()));
				os.insertOption(new Option(String.valueOf(request.getSession().getAttribute("admin")), request.getParameter("username"), type, new java.sql.Date(new Date().getDate())));
				response.getWriter().write("1");
				return;
			}else {
				response.getWriter().write("0");
				return;
			}
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
