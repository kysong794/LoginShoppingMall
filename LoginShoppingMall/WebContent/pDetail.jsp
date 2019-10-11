<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="shoppingMall.*"%>
<% 
	ShoppingVo vo3 = (ShoppingVo) request.getAttribute("vo3");
%>

<header><h1>상품 정보</h1></header>
<form id="frm" action="pdetail" method="post" onsubmit='return che()'>
<table>
	<tr>
		<td><input type="hidden" name="i_product" value="<%=vo3.getI_product() %>"></td>
	</tr>
	<tr>
		<td>제품명: <%=vo3.getNm() %></td>
	</tr>
	<tr>	
		<td><img src="<%=vo3.getPic() %>"width="150" height="150" ></td>
	</tr>
	<tr>	
		<td>금액: <input name="price" value="<%=vo3.getPrice() %>"readonly>원</td>
	</tr>
	<tr>	
		<td>재고: <input name="qty" value="<%=vo3.getQty() %>"readonly>개</td>
	</tr>
	<tr>
		<td>정보: <input name="info" value="<%=vo3.getInfo() %>" readonly></td>
	<tr>
		<td>수량:<input type="button" name="operator" value="-" onclick="countnum(this);">
		<input type="number" name="count" value="0" readonly="readonly">
		<input type="button" name="operator" value="+" onclick="countnum(this);"></td>
	</tr>

	<tr>
		<td><input type="submit" value="장바구니에 담기" name="basket"></td>
		<td><input type="submit" value="바로구매" name="purchase" ></td>
	</tr>
</table>
</form>
<script>
	function countnum(e){
		if(e.value==='-'){
			if(frm.count.value>0){
			frm.count.value--;
		}
		}else if(e.value==='+'){
			frm.count.value++;
		}
	}
	function che(){
		if(<%=vo3.getQty() %><frm.count.value){
			alert('재고가 부족합니다.');
			return false;
		}
		if(frm.count.value==0||frm.count.value==null){
			alert('제품 수량은 0이상이어야 합니다.');
			return false;
		}
		return true;
	}
</script>