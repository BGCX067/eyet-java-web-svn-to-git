package lib.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 验证码图片
 * @author 
 * 
 * session 验证码属性值 Login_Image_Code
 *
 */
public class CheckServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 得到随机颜色
	 * 
	 * @param fc
	 * @param bc
	 * @return
	 */
	private static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 定义字符串
	 */
	private static String charsLong = "23456789abcdefghjklmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";

	/**
	 * 定义字符串
	 */
	private static String charsShort = "0123456789";

	/**
	 * 定义字符串并赋值
	 */
	private static String chars = charsShort;

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int charsLength = chars.length();

			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			int width = 70, height = 20;
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);

			Graphics g = image.getGraphics();

			Random random = new Random();

			g.setColor(getRandColor(200, 250));
			g.fillRect(0, 0, width, height);

			g.setFont(new Font("Times New Roman", Font.ITALIC, height));

			g.setColor(getRandColor(160, 200));
			for (int i = 0; i < 10; i++) {
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				int xl = random.nextInt(12);
				int yl = random.nextInt(12);
				g.drawLine(x, y, x + xl, y + yl);
			}

			StringBuilder sRand = new StringBuilder();
			String[] fontNames = { "Times New Roman", "Arial", "Book antiqua",
					"" };

			for (int i = 0; i < 4; i++) {
				g.setFont(new Font(fontNames[random.nextInt(3)], Font.ITALIC,
						height));
				char rand = chars.charAt(random.nextInt(charsLength));
				sRand.append(rand);

				g.setColor(new Color(20 + random.nextInt(110), 20 + random
						.nextInt(110), 20 + random.nextInt(110)));
				g.drawString(String.valueOf(rand), 16 * i + random.nextInt(6)
						+ 3, height - random.nextInt(4));
			}

			g.setColor(getRandColor(160, 200));
			for (int i = 0; i < 10; i++) {
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				int xl = random.nextInt(width);
				int yl = random.nextInt(width);
				g.drawLine(x, y, x + xl, y + yl);
			}

			request.getSession().setAttribute("Login_Image_Code",
					sRand.toString());

			g.dispose();

			try {
				Thread.sleep(100);
			} catch (Exception ex) {
			}
			OutputStream os = response.getOutputStream();
			ImageIO.write(image, "JPEG", os);
			os.flush();
			os.close();
		} catch (Exception ex) {

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}