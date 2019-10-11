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

//일별 판매 현황
@WebServlet("/admin/psaleday")
public class AdminPSaleDay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		ShoppingVo avo = (ShoppingVo)session.getAttribute("Adminlogin");
		if(avo == null) {
			response.sendRedirect("/admin/login");
			return;
		}
		//지금(현재) 날짜 넣기
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				
		String format_time = format.format(System.currentTimeMillis());
		
		System.out.println("format_time:"+format_time);
		
		request.setAttribute("format_time", format_time);
		
		request.setAttribute("title", "일별 판매 현황");
		request.setAttribute("view", "pSaleDay.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String R_dt = request.getParameter("R_dt");
		String R_dt2 = request.getParameter("R_dt2");

		System.out.println("R_dt:"+R_dt);
		System.out.println("R_dt2:"+R_dt2);
	
		List<ShoppingVo> psaleday = DAO.PSaleDay(R_dt,R_dt2);
		request.setAttribute("R_dt",R_dt);
		request.setAttribute("R_dt2",R_dt2);
		request.setAttribute("psaleday", psaleday);
		doGet(request,response);
	
	}

}
