package adminControllers;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "uploadfile.do", urlPatterns = { "/uploadfile.do" })
@MultipartConfig
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Part part = request.getPart("filename");
		String root = request.getServletContext().getRealPath("/file");
		String headname = part.getHeader("content-disposition");
		String ext = headname.substring(headname.lastIndexOf("."),headname.length()-1);
		String filename = root+"\\"+UUID.randomUUID().toString()+ext;
		System.out.println(filename);
		part.write(filename);
		System.out.println("上传成功！！");
		response.getWriter().write("1");
		request.getRequestDispatcher("/1.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
