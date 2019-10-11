<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="shoppingMall.*"%>


<script type="text/javascript">
	function chk() {
		if (frm.mid.value == ''||frm.mid.value==null) {
			alert("아이디를 입력하세요");
			return false;
		}
		if (frm.mpw.value == ''||frm.mpw.value==null) {
			alert("비밀번호를 입력하세요");
			return false;
		}
		alert("로그인이 성공하였습니다");
		return true;
	}
</script>

<body>
	<header>
		<h1>Admin Login</h1>
	</header>
	<form id="frm" action="/admin/login" method="post" onsubmit="return chk();">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="mid"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="mpw"></td>
			</tr>
			<tr>
				<th><input type="submit" value="관리자 로그인"></th>
			</tr>
		</table>
	</form>
</body>
</html>