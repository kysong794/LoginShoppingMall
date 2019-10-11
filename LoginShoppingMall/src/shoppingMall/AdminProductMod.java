package shoppingMall;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//관리자 상품 수정
@WebServlet("/admin/mod")
public class AdminProductMod extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					
		String nm = request.getParameter("nm");
		String price = request.getParameter("price");
		String pic = request.getParameter("pic");
		String vn_sale = request.getParameter("vn_sale");
		String info = request.getParameter("info");
		
		request.setAttribute("nm", nm);
		request.setAttribute("price", price);
		request.setAttribute("pic", pic);
		request.setAttribute("vn_sale", vn_sale);
		request.setAttribute("info", info);
		
		request.setAttribute("title", "상품 수정");
		request.setAttribute("view", "pMod.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String nm = request.getParameter("nm");
		String price = request.getParameter("price");
		String pic = request.getParameter("pic");
		String vn_sale = request.getParameter("vn_sale");
		String info = request.getParameter("info");

		System.out.println("mn:" + nm);
		System.out.println("price:" + price);
		System.out.println("pic:" + pic);
		System.out.println("vn_sale:" + vn_sale);
		System.out.println("info:" + info);
		
		ShoppingVo vo = new ShoppingVo();

		vo.setNm(nm);
		vo.setPrice(price);
		vo.setPic(pic);
		vo.setVn_sale(vn_sale);
		vo.setInfo(info);
		
		int result = DAO.productMod(vo);
		
		if(result==0) {
			System.out.println("수정 실패");
			doGet(request,response);
		}
		if(result != 0) {
			System.out.println("수정 성공");
			response.sendRedirect("list");
		}
		
	}

}
