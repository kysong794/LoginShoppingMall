package shoppingMall;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//User Login 유저 로그인
@WebServlet("/login")
public class UserLoginSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("title", "User Login");
		request.setAttribute("view", "login.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");

		ShoppingVo vo = new ShoppingVo();

		vo.setMid(mid);
		vo.setMpw(mpw);

		int result = DAO.userlogin(vo);
		
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
			String i_member = DAO.i_member(vo);
			System.out.println("로그인 성공");
			HttpSession session = request.getSession();
			session.setAttribute("userVo", vo);
			session.setAttribute("i_member", i_member);
			request.setAttribute("msg2", "로그인 성공");
			response.sendRedirect("home.jsp");
		}

	}

}
