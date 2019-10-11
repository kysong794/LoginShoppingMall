<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="shoppingMall.*" %>
<%
	String nm = (String)request.getAttribute("nm");
	String price = (String)request.getAttribute("price");
	String pic = (String)request.getAttribute("pic");
	String vn_sale = (String)request.getAttribute("vn_sale");
	String info = (String)request.getAttribute("info");

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
		if(frm.vn_sale.value===''||frm.vn_sale.value===null){
			alert("판매여부를 선택하세요");
			return false;
		}
		if(frm.info.value===''||frm.info.value===null){
			alert("설명을 적어주세요");
			return false;
		}
		alert("상품수정이 성공하였습니다");
		return true;
	}
</script>
<h1>상품 수정</h1>
<form id=frm action="mod" method="post" onsubmit="return che();">
<table>
	<tr>
		<th>제품명:</th>
		<td><input type="text" name="nm"  value="<%=nm %>"></td>
	</tr>
	<tr>
		<th>금액:</th>
		<td><input type="text" name="price" value="<%=price %>"></td>
	</tr>
	<tr>
		<th>사진:</th>
		<td><input type="text" name="pic" value="<%=pic %>"> (이미지 업로드 X, 웹 이미지 주소)</td>
	</tr>
	<tr>
		<th>판매여부:</th>
		<td><select name="vn_sale" >
		<option value="" >판매여부 선택</option>
		<option value ="1">판매중</option>
		<option value ="2">준비중</option>
		</select></td>
	</tr>
	<tr>
		<th>설명:</th>
		<td><input type="text" name="info" value="<%=info%>"></td>
	</tr>
	<tr>
		<td><input type="submit" value="수정"></td>
	</tr>
</table>
</form>