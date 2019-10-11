package shoppingMall;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//상품 입고
@WebServlet("/admin/import")
public class AdminProductImport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		ShoppingVo avo = (ShoppingVo)session.getAttribute("Adminlogin");
		if(avo == null) {
			response.sendRedirect("/admin/login");
			return;
		}
		List<ShoppingVo> list =DAO.productList();
		List<ShoppingVo> importlist = DAO.productImportList();
		
		
		request.setAttribute("list", list);
		request.setAttribute("importlist", importlist);
		request.setAttribute("title", "상품 입고");
		request.setAttribute("view", "pImport.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String i_product = request.getParameter("i_product");
		String qty = request.getParameter("qty");
		
		System.out.println("i_product : "+ i_product);
		System.out.println("qty : "+ qty);

		ShoppingVo vo = new ShoppingVo();
		
		vo.setI_product(i_product);
		vo.setQty(qty);
		
		int result = DAO.productImport(vo);
		
		if(result ==0) {
			System.out.println("상품 입고 실패");
			doGet(request,response);
		}
		
		if(result!=0) {
			System.out.println("상품 입고 성공");
			DAO.Qty(vo);
			response.sendRedirect("/admin/import");
		}
	}

}
