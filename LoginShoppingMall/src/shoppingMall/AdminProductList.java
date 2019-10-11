package shoppingMall;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//관리자 상품 리스트
@WebServlet("/admin/list")
public class AdminProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		ShoppingVo avo = (ShoppingVo)session.getAttribute("Adminlogin");
		if(avo == null) {
			response.sendRedirect("/admin/login");
			return;
		}
		//상품 삭제
		String nm = request.getParameter("nm");
		String i_prodcut = request.getParameter("i_product");
		
		DAO.deleteproduct(nm,i_prodcut);
		
		List<ShoppingVo> list = DAO.productList();

		request.setAttribute("list", list);
		request.setAttribute("title", "관리자 상품 리스트");
		request.setAttribute("view", "pList.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		

	}

}
