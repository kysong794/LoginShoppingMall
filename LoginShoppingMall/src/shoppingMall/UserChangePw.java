package shoppingMall;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//비밀번호변경
@WebServlet("/changePw")
public class UserChangePw extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		request.setAttribute("title", "비밀번호변경");
		request.setAttribute("view", "changePw.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String mpw = request.getParameter("mpw");
		String newMpw = request.getParameter("newMpw");
		String newMpw2 = request.getParameter("newMpw2");
		
		System.out.println("mpw:"+mpw);
		System.out.println("newMpw:"+newMpw);
		System.out.println("newMpw2:"+newMpw2);
		
		HttpSession session = request.getSession();
		String i_member = (String) session.getAttribute("i_member");
		
		System.out.println("i_member:"+i_member);
		
		ShoppingVo vo = new ShoppingVo();
		
		vo.setMpw(newMpw);
		vo.setI_member(i_member);
		
		int result = DAO.updatePw(vo);
		
		if(result == 0) {
			System.out.println("비밀번호 변경 실패");
			doGet(request,response);
			
		}if(result!=0) {
			System.out.println("비밀번호 변경 성공");
			response.sendRedirect("login");
		}
		
		
	}

}
