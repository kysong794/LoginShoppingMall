package shoppingMall;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//구매리스트
@WebServlet("/purchaselist")
public class UserPurchaseListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		ShoppingVo avo = (ShoppingVo) session.getAttribute("userVo");
		if (avo == null) {
			response.sendRedirect("/login");
			return;
		}
		String i_member = (String) session.getAttribute("i_member");
		
		List<ShoppingVo> purchaseList = DAO.purchaseList(i_member);
		
		request.setAttribute("purchaseList", purchaseList);
		
		request.setAttribute("title", "구매리스트");
		request.setAttribute("view", "purchaseList.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
		
	}

		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	}

}
