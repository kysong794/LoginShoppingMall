package shoppingMall;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//회원가입 UserJoin
@WebServlet("/join")
public class UserJoinSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			
		request.setAttribute("title", "User Join");
		request.setAttribute("view", "join.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		ShoppingVo vo = new ShoppingVo();
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mpw2 = request.getParameter("pw2");
		String nm = request.getParameter("nm");
		String sex = request.getParameter("sex");
		
		System.out.println("mid:"+mid);
		System.out.println("mpw:"+mpw);
		System.out.println("mpw2:"+mpw2);
		System.out.println("nm:"+nm);
		System.out.println("sex:"+sex);
		
		vo.setMid(mid);
		vo.setMpw(mpw);
		vo.setNm(nm);
		vo.setSex(sex);
		
		int result = DAO.signUp(vo);
		
		if(result==0) {
			System.out.println("회원가입 실패");
			doGet(request,response);
		}
		if(result!=0) {
			System.out.println("회원가입 성공");
			response.sendRedirect("login");
		}
		
	}

}
