<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="shoppingMall.*"%>
<%
	List<ShoppingVo> basketlist = (List<ShoppingVo>) request.getAttribute("basketlist");
%>

<h1>장바구니</h1>
<form id="frm" action="basket" method="post" onsubmit='return che();'>
<a href="basket">장바구니</a>
<a href="changePw">비밀번호변경</a>
<table>
	<tr>
		<td>장바구니 번호</td>
		<td>이미지</td>
		<td>상품명</td>
		<td>금액</td>
		<td>수량</td>
		<td>비고</td>
	</tr>
	<%for (ShoppingVo vo : basketlist){ %>
	<tr>
		<td><%=vo.getI_basket() %></td>
		<td><img src="<%=vo.getPic() %>"width="150" height="150"></td>
		<td><%=vo.getNm() %></td>
		<td>단가:<%=vo.getPrice() %>원</br> 구매 금액:<%=vo.getSumprice() %>원</td>
		<td>현재고:<%=vo.getSumqty() %>개</br> 구매 수량:<%=vo.getQty() %>개</td>
		<td></td>
	</tr>
	<%} %>
</table>
</form>