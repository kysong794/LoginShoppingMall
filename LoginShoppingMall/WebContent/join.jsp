<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="shoppingMall.*" %>
<%

%>
    
<!DOCTYPE html>
<html>
<script type="text/javascript">
	function che(){
		if(frm.mid.value===''||frm.mid.value===null){
			alert("아이디를 입력하세요");
			return false;
		}
		if(frm.mpw.value===''||frm.mpw.value===null){
			alert("비밀번호를 입력하세요");
			return false;
		}
		if(frm.mpw2.value===''||frm.mpw2.value===null){
			alert("비밀번호 확인을 입력하세요");
			return false;
		}
		if(frm.nm.value===''||frm.nm.value===null){
			alert("이름을 입력하세요");
			return false;
		}
		if(frm.mpw.value!==frm.mpw2.value){
			alert("비밀번호가 일치하지 안습니다.");
			return false;
		}
		alert("회원가입이 성공하였습니다");
		return true;
	}
</script>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
	<header><h1>User Join</h1></header>
<form id="frm" action="join" method="post" onsubmit="return che();">	
	<table>
		<tr>
			<th>아이디</th>
			<td><input type="text" name="mid" ></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="mpw"></td>
		</tr>
		<tr>
			<th>비밀번호 확인</th>
			<td><input type="password" name="mpw2"></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="nm" ></td>
		</tr>
		<tr>
			<th>성별</th>
			<td><input type="radio" name="sex" value="2" checked="checked">남
			<input type="radio" name="sex" value="1" >여</td>
		</tr>
		<tr>
			<th><input type="submit" value="회원가입"></th>
		</tr>
	</table>
</form>
</body>
</html>