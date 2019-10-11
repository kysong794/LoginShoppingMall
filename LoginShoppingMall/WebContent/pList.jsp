<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="shoppingMall.*"%>
<%
	List<ShoppingVo> plist = (List<ShoppingVo>)request.getAttribute("plist");
%>

<header><h1>상품 리스트</h1></header>
<table>
	<%for (ShoppingVo vo : plist){ %>
	<tr>
		<td><a href="pdetail?i_product=<%=vo.getI_product() %>"><img src="<%=vo.getPic() %>" width="150" height="150" ></a></td>
		<td><%=vo.getNm() %></td>
		<td><%=vo.getQty() %>개</td>
		<td><%=vo.getPrice() %>원</td>
	</tr>
	<%} %>
</table>