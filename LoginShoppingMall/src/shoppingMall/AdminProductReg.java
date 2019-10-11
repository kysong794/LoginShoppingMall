package shoppingMall;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//관리자 상품 등록
@WebServlet("/admin/reg")
public class AdminProductReg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ShoppingVo avo = (ShoppingVo) session.getAttribute("Adminlogin");
		if (avo == null) {
			response.sendRedirect("/admin/login");
			return;
		}
		request.setAttribute("title", "상품 등록");
		request.setAttribute("view", "pReg.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String nm = request.getParameter("nm");
		String price = request.getParameter("price");
		String pic = request.getParameter("pic");

		System.out.println("mn:" + nm);
		System.out.println("price:" + price);
		System.out.println("pic:" + pic);

		ShoppingVo vo = new ShoppingVo();

		vo.setNm(nm);
		vo.setPrice(price);
		vo.setPic(pic);

		int result = DAO.productReg(vo);

		if (result == 0) {
			System.out.println("상품 등록 실패");
			doGet(request, response);
		}

		if (result != 0) {
			System.out.println("상품 등록 성공");
			request.setAttribute("title", "상품 등록");
			request.setAttribute("view", "pReg.jsp");
			request.getRequestDispatcher("temp.jsp").forward(request, response);
		}
	}

}
