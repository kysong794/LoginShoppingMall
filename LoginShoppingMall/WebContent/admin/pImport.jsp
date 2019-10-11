<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="shoppingMall.*" %>
<%
	List<ShoppingVo> list = (List<ShoppingVo>)request.getAttribute("list");
	List<ShoppingVo> importlist = (List<ShoppingVo>)request.getAttribute("importlist");
%>
<script type="text/javascript">
	function che(){
		if(frm.i_product.value===''||frm.i_product.value===null){
			alert("상품을 선택하세요");
			return false;
		}
		if(frm.qty.value===''||frm.qty.value===null){
			alert("수량을 입력하세요");
			return false;
		}
		alert("상품 입고가 성공하였습니다");
		return true;
	}
</script>
<h1>상품 입고</h1>
<form id=frm action="import" method="post" onsubmit="return che();">
<table>
	<tr>
		<th>상품:</th>
		<td><select name="i_product"><option value="" >--선택--</option>
		<% for(ShoppingVo vo : list){ %>
		<option value ="<%=vo.getI_product() %>"><%=vo.getNm() %></option>
		<%} %>
		</select></td>
	</tr>
	<tr>
		<th>수량 :</th>
		<td><input type="text" name="qty" ></td>
	</tr>
	<tr>
		<td><input type="submit" value="입고"></td>
	</tr>
</table>
</form>

<h1>입고 리스트</h1>
<table>
	<tr>
		<th>입고 번호</th>
		<th>상품명</th>
		<th>금액</th>
		<th>수량</th>
	</tr>
	<%for (ShoppingVo vo : importlist){ %>
	<tr>
		<td><%=vo.getI_pi() %></td>
		<td><%=vo.getNm()%></td>
		<td><%=vo.getSum() %>원</td>
		<td><%=vo.getQty() %>개</td>
	</tr>
	<%} %>
</table>
