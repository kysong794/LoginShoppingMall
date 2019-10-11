package shoppingMall;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//관리자 로그인
@WebServlet("/admin/login")
public class Admin_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		request.setAttribute("title", "관리자 로그인");
		request.setAttribute("view", "login.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		
		ShoppingVo vo = new ShoppingVo();
		
		vo.setMid(mid);
		vo.setMpw(mpw);
		
		int result = DAO.adminLogin(vo);
		
		if (result == 0) {
			System.out.println("아이디 없음");
			request.setAttribute("msg", "아이디 없음");
			doGet(request, response);
		}
		if (result == -1) {
			System.out.println("비밀번호 틀림");
			request.setAttribute("msg", "비밀번호 틀림");
			doGet(request, response);
		}
		if (result > 0) {
			System.out.println("로그인 성공");
			HttpSession session = request.getSession();
			session.setAttribute("Adminlogin", vo);
			request.setAttribute("msg2", "로그인 성공");
			response.sendRedirect("home.jsp");
		}
	
	}

}
