package shoppingMall;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//상품 리스트 유저
@WebServlet("/plist")
public class UserpListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		ShoppingVo avo = (ShoppingVo)session.getAttribute("userVo");
		if(avo == null) {
			response.sendRedirect("/login");
			return;
		}
		
		List<ShoppingVo> plist = DAO.pList();
		
		System.out.println(plist.size());
		
		request.setAttribute("plist", plist);
		request.setAttribute("title", "상품 리스트");
		request.setAttribute("view", "pList.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	}

}
