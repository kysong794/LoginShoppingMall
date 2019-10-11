<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="shoppingMall.*" %>
<%
	String msg2 = (String) request.getAttribute("msg2");
%>
<!DOCTYPE html>
<html>
<script type="text/javascript">
	<%if(msg2!=null){%>
		alert('<%=msg2%>');
	<%}%>
</script>
<head>
<meta charset="UTF-8">
<title>홈</title>
</head>
<body>
<div id="container">
	<header><h1></h1></header>
	<nav>
		<a href="plist">상품리스트</a>
		<a href="purchaselist">구매리스트</a>
		<a href="/basket">마이페이지</a>
		<a href="logout">로그아웃</a>
	</nav>
	<section>
		<div>사용자 홈 화면</div>
	</section>
	<footer>
		Copyright&copy; 한국쇼핑몰에 오신걸 환영합니다.
	</footer>
</div>
</body>
</html>