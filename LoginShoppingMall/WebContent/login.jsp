<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="shoppingMall.*"%>
<%
	String msg = (String) request.getAttribute("msg");


%>


<html>
<script type="text/javascript">
	function chk() {
		if (frm.mid.value == '' || frm.mid.value == null) {
			alert("존재하지 않는 아이디 입니다");
			return false;
		}
		if (frm.mpw.value == '' || frm.mpw.value == null) {
			alert("비밀번호를 확인해 주세요");
			return false;
		}
	}
	<% if(msg!=null){%>
		alert('<%=msg%>');
	<%}%>


</script>
<body>
	<header>
		<h1>User Login</h1>
	</header>
	<form id="frm" action="login" method="post" onsubmit="return chk();">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="mid" autocomplete = "off"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="mpw"></td>
			</tr>
			<tr>
				<th><input type="submit" value="로그인"></th>
				<th></th>
			</tr>
		</table>
	</form>
			<a href="join"><button>회원 가입</button></a>
</body>