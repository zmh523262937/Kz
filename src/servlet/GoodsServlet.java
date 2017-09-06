package servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;

import entity.PageInfo;
import service.CatagoryService;
import service.CatagoryServiceImp;
import service.GoodsService;
import service.GoodsServiceImp;
import service.PublisherService;
import service.PublisherServiceImp;

/**
 * Servlet implementation class GoodsServlet
 */
@WebServlet("/goods.do")
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PublisherService ps =new PublisherServiceImp();
		request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html;charset=UTF-8");
		
		String type =request.getParameter("type");
		GoodsService gs =new GoodsServiceImp();
		CatagoryService cs =new CatagoryServiceImp();
		if (type!=null&&type.equals("list")) {
			String cid= request.getParameter("cid");
			int pageSize = Integer.parseInt(request.getParameter("rows"));
			int pageIndex=Integer.parseInt(request.getParameter("page"));
			PageInfo<entity.Goods> pageInfo =gs.getPageInfo(pageIndex, pageSize, cid);
			for (int i = 0; i < pageInfo.getData().size(); i++) {
				pageInfo.getData().get(i).setPid(ps.getPublisher(pageInfo.getData().get(i).getPid()).getPname());
				pageInfo.getData().get(i).setCid(cs.get(pageInfo.getData().get(i).getCid()).getCname());
			}
			Map<String, Object> map =new HashMap<>();
			map.put("total", pageInfo.getTotalNumber());
			map.put("rows", pageInfo.getData());
			Gson gson =new Gson();
			response.getWriter().write(gson.toJson(map));
		}
		if (type!=null &&type.equals("remove")) {
			String gid = request.getParameter("gid");
			gs.deleteGoodsById(gid);
			response.getWriter().write("1");
		}
		if (type!=null &&type.equals("add")) {
			String pname = request.getParameter("gpublish");
			String cname = request.getParameter("gcate");
			String pid = ps.getPublisherByPname(pname);
			String cid =cs.getCid(cname);
			if (pid==null) {
				response.getWriter().write("0");
				return;
			}
			if (cid==null) {
				response.getWriter().write("1");
				return;
			}
			if (cid!=null&&pid!=null) {
//				  boolean flag=ServletFileUpload.isMultipartContent(request);
//				  System.out.println(flag);
//				  FileItemFactory factory=new DiskFileItemFactory();
//				  ServletFileUpload upload=new ServletFileUpload(factory);
//				  try {
//					List<FileItem> lstForms=upload.parseRequest((RequestContext) request);
//				} catch (FileUploadException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				DiskFileItemFactory factory =new DiskFileItemFactory();
//				factory.setRepository(new File("E:/1.jpg"));
//				factory.setSizeThreshold(1024*1024);
//				ServletFileUpload upload =new ServletFileUpload(factory);
//				try {
//					List<FileItem>list =upload.parseRequest( request);
//					for(FileItem item :list){
//						if (item.isFormField()) {
//							String name = item.getFieldName();
//							System.out.println("------------."+name);
//						}
//					}
//				} catch (FileUploadException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				String gname = request.getParameter("gname");
				String gauthor =request.getParameter("gauthor");
				double gsaleprice =Double.parseDouble(request.getParameter("gsaleprice"));
				double ginprice=Double.parseDouble(request.getParameter("ginprice"));
				String gdisc =request.getParameter("disc");				
				int gcount = 0;
				String img = request.getParameter("img");
				entity.Goods goods =new entity.Goods(UUID.randomUUID().toString(),gname, gauthor, gsaleprice, ginprice, gdisc, img, gcount, cid, pid);
				gs.insertGoods(goods);
				System.out.println("iamhere");
				response.getWriter().write("2");
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
