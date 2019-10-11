package shoppingMall;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//월별 상품 판매 현황
@WebServlet("/admin/psalemon")
public class AdminPSaleMonSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		ShoppingVo avo = (ShoppingVo)session.getAttribute("Adminlogin");
		if(avo == null) {
			response.sendRedirect("/admin/login");
			return;
		}
		//관리자 상품 리스트
		List<ShoppingVo> list =DAO.productList();
		
		request.setAttribute("list", list);
		
		request.setAttribute("title", "월별 상품 판매 현황");
		request.setAttribute("view", "pSaleMon.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		String year = request.getParameter("year");
		int mon2 = Integer.parseInt(request.getParameter("mon"));
		String i_product = request.getParameter("i_product");
		String mon =null;
		
		if(mon2 <10) {
			mon = "0"+mon2;
		}else {
			mon = String.valueOf(mon2);
		}
		
		System.out.println("year:"+ year);
		System.out.println("mon:"+ mon);
		System.out.println("i_product:"+ i_product);
		
		String yymm = year+"-"+mon;
		System.out.println("yymm:"+yymm);
		
		List<ShoppingVo> psalemon = DAO.PSaleMon(yymm,i_product);
		request.setAttribute("year",year);
		request.setAttribute("mon",mon);
		request.setAttribute("i_product", i_product);
		request.setAttribute("psalemon", psalemon);
		doGet(request,response);

	}

}
