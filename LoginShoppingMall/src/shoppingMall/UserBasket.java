package shoppingMall;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//장바구니
@WebServlet("/basket")
public class UserBasket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		ShoppingVo avo = (ShoppingVo) session.getAttribute("userVo");
		if (avo == null) {
			response.sendRedirect("/login");
			return;
		}
		String i_member = (String) session.getAttribute("i_member");
		
		List<ShoppingVo> basketlist = DAO.basketList(i_member);
		
		request.setAttribute("basketlist", basketlist);
		
		request.setAttribute("title", "장바구니");
		request.setAttribute("view", "basket.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		
	}

}
