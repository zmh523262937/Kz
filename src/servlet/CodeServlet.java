package servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CodeServlet
 */
@WebServlet("/CodeServlet.do")
public class CodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String chars="QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm0123456789";
		String codes = "";
		Random rand =new Random();
		
		for (int i = 0; i < 4; i++) {
			int index = rand.nextInt(61);
			codes+=chars.charAt(index);
		}
		request.getSession().setAttribute("codes", codes);
		BufferedImage bufferedImage = new BufferedImage(90, 30, BufferedImage.TYPE_INT_BGR);
		Graphics g =bufferedImage.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 90, 30);
		g.setColor(Color.RED);
		g.drawRect(0, 0, 88, 28);
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("宋体", Font.BOLD, 20));
		g.drawString(codes, 25, 20);
		g.setColor(Color.GREEN);
		for (int i = 0; i < 10; i++) {
			g.drawLine(rand.nextInt(88), rand.nextInt(28),rand.nextInt(88), rand.nextInt(28));
		}
		
		response.setContentType("image/jpeg");
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
