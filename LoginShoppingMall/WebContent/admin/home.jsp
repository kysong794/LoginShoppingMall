<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="shoppingMall.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin home</title>
</head>
<body>
<div id="container">
	<header><h1></h1></header>
	<nav>
		<a href="reg">상품등록</a>
		<a href="list">상품 리스트</a>
		<a href="import">상품 입고</a>
		<a href="psaleday">판매 현황(day)</a>
		<a href="psalemon">판매 현황(mon)</a>
		<a href="">판매 현황(year))</a>
		<a href="/admin/admin_logout">로그아웃</a>
	</nav>
	<section>
		<div>관리자 홈</div>
	</section>
	<footer>
		Copyright&copy; 한국쇼핑몰에 오신걸 환영합니다.
	</footer>
</div>
</body>
</html>