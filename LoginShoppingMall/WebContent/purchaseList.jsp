<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="shoppingMall.*"%>
<%
	List<ShoppingVo> purchaseList = (List<ShoppingVo>) request.getAttribute("purchaseList");
%>

<h1>구매 리스트</h1>
<table>
	<tr>
		<td>구매 번호</td>
		<td>이미지</td>
		<td>상품명</td>
		<td>금액</td>
		<td>수량</td>
		<td>구매일</td>
		<td>비고</td>
	</tr>
	<%for (ShoppingVo vo : purchaseList){ %>
	<tr>
		<td><%=vo.getI_purchase() %></td>
		<td><img src="<%=vo.getPic() %>" width="150" height="150"></td>
		<td><%=vo.getNm() %></td>
		<td>단가:<%=vo.getPrice() %>원</br> 구매 금액:<%=vo.getSumprice() %>원</td>
		<td>현재고:<%=vo.getQty() %>개</br> 구매 수량:<%=vo.getSumqty() %>개</td>
		<td><%=vo.getR_dt() %>	
		</td>
		<% if(vo.getVn_sale().equals("1") ){%>
		<td><a href="pdetail?i_product=<%=vo.getI_product() %>" >상품보기</a></td>
		<%} %>
	</tr>
	<%} %>
</table>
