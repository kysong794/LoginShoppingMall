package shoppingMall;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//상품 정보 (유저)
@WebServlet("/pdetail")
public class UserpDetailSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		ShoppingVo avo = (ShoppingVo) session.getAttribute("userVo");
		if (avo == null) {
			response.sendRedirect("/login");
			return;
		}
		
		String i_product2 = request.getParameter("i_product");
		ShoppingVo vo3 = DAO.selectVo(i_product2);

		request.setAttribute("vo3", vo3);

		request.setAttribute("title", "상품 정보");
		request.setAttribute("view", "pDetail.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String basket = request.getParameter("basket");
		String purchase = request.getParameter("purchase");

		System.out.println("basket:" + basket);
		System.out.println("purchase:" + purchase);

		HttpSession session = request.getSession();
		String i_member = (String) session.getAttribute("i_member");

		ShoppingVo vo = new ShoppingVo();

		String count = request.getParameter("count");
		String i_product = request.getParameter("i_product");
		String price = request.getParameter("price");
		String qty = request.getParameter("qty");
		
		vo.setCount(count);
		vo.setI_product(i_product);
		vo.setI_member(i_member);
		vo.setPrice(price);
		vo.setQty(qty);

		if (basket != null) {
			int result = DAO.basket(vo);

			if (result == 0) {
				System.out.println("실패");
				doGet(request, response);
			}
			if (result != 0) {
				System.out.println("성공");
				response.sendRedirect("/plist");
			}
		}
		if (purchase != null) {
			int result2 = DAO.purchase(vo);
			DAO.mQty(vo);
			if (result2 == 0) {
				System.out.println("실패");
				doGet(request, response);
			}
			if (result2 != 0) {
				System.out.println("성공");
				response.sendRedirect("/plist");
			}
		}
	}
}

