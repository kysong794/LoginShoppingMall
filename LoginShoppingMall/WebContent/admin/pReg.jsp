<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="shoppingMall.*" %>
<%

%>
<script type="text/javascript">
	function che(){
		if(frm.nm.value===''||frm.nm.value===null){
			alert("제품명을 입력하세요");
			return false;
		}
		if(frm.price.value===''||frm.price.value===null){
			alert("금액을 입력하세요");
			return false;
		}
		if(frm.pic.value===''||frm.pic.value===null){
			alert("사진을 입력하세요");
			return false;
		}
		alert("제품등록이 성공하였습니다");
		return true;
	}
</script>
<h1>상품 등록</h1>
<form id=frm action="reg" method="post" onsubmit="return che();">
<table>
	<tr>
		<th>상품 번호:</th>
		<td><input type="number" name="i_product"  readOnly></td>
	</tr>
	<tr>
		<th>제품명:</th>
		<td><input type="text" name="nm" ></td>
	</tr>
	<tr>
		<th>금액:</th>
		<td><input type="text" name="price" ></td>
	</tr>
	<tr>
		<th>사진:</th>
		<td><input type="text" name="pic"  > (이미지 업로드 X, 웹 이미지 주소)</td>
	</tr>
	<tr>
		<td><input type="submit" value="등록"></td>
	</tr>
</table>
</form>