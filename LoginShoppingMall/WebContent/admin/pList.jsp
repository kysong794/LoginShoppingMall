<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="shoppingMall.*" %>
<%
	List<ShoppingVo> list = (List<ShoppingVo>) request.getAttribute("list");
	
%>

<h1>상품 리스트</h1>
<table>
	<tr>
		<th>상품 번호</th>
		<th>이미지</th>
		<th>상품명</th>
		<th>금액</th>
		<th>수량</th>
		<th>판매여부</th>
		<th>비고</th>
	</tr>
	<%for(ShoppingVo vo : list){ %>
	<tr>
		<td><%=vo.getI_product() %></td>
		<td><img src="<%=vo.getPic() %>" width="150" height="150"></td>
		<td><%=vo.getNm() %></td>
		<td><%=vo.getPrice() %>원</td>
		<td><%=vo.getQty() %></td>
		<%if(vo.getVn_sale().equals("1")){ %>
		<td>판매중</td>
		<%} else{ %>
		<td>준비중</td>
		<%} %>
		<td><a href="mod?nm=<%=vo.getNm() %>&price=<%=vo.getPrice() %>&pic=<%=vo.getPic() %>&info=<%=vo.getInfo()%>"type="button">수정</a></td>
		<td><a href="list?nm=<%=vo.getNm() %>&i_product=<%=vo.getI_product()%>"type="button">삭제</a></td>
	</tr>
		<%} %>
</table>