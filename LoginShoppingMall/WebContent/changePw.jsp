<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="shoppingMall.*"%>
<%

%>
<body onload="frm.mpw.focus();">
<h1>비밀번호 변경</h1>
<form id="frm" action="changePw" method="post" onsubmit="return che();">
<table>
  <tr>
    <th>현재 비밀번호</th>
    <td><input type="text" name="mpw"></td>
  </tr>
  <tr>
    <th>변경 비밀번호</th>
    <td><input type="text" name="newMpw"></td>
  </tr>
  <tr>
    <th>변경 비밀번호 확인</th>
  	 <td><input type="text" name="newMpw2"></td>
  </tr>
  <tr>
  	<td><input type="submit" value="비밀번호 수정"></td>
  </tr>
</table>
</form>
</body>
<script>
	function che(){
		if(frm.mpw.value==''||frm.mpw.value==null){
			alert('현재 비밀번호를 확인해주세요.');
			return false;
		}if(frm.newMpw.value==''||frm.newMpw.value==null){
			alert('변경 비밀번호를 확인해주세요.');
			return false;
		}if(frm.newMpw.value!==frm.newMpw2.value){
			alert('비밀 번호가 일치하지 않습니다.');
			return false;
		}
		alert('비밀 번호 변경이 성공 하였습니다.');
		return true;
	}
</script>