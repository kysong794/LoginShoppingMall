<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="shoppingMall.*" %>

<%
	List<ShoppingVo> psaleday = (List<ShoppingVo>) request.getAttribute("psaleday");
	String R_dt = (String) request.getAttribute("R_dt");
	String R_dt2 = (String) request.getAttribute("R_dt2");
	String format_time = (String) request.getAttribute("format_time");
	
	if(R_dt == null){
		R_dt=format_time;
		R_dt2=format_time;
	}
%>
<form id="frm" action="psaleday" method="post" onsubmit='return che();'>
<table>
	<tr>
		<td><input type="date" name="R_dt" value="<%=R_dt %>">~<input type="date" name="R_dt2" value="<%=R_dt2%>"></td>
		<td><input type="submit" value="검색"></td>
	</tr>
	<tr>
		<td>날짜</td>
		<td>이미지</td>
		<td>상품명</td>
		<td>단가</td>
		<td>총 수량</td>
		<td>총 금액</td>
	</tr>
	<%if(psaleday !=null){ %>
	<%for(ShoppingVo vo : psaleday){ %>
	<tr>
		<td><%=vo.getR_dt() %></td>
		<td><img src="<%=vo.getPic() %>"width="150" height="150"></td>
		<td><%=vo.getNm() %></td>
		<td><%=vo.getPrice() %></td>
		<td><%=vo.getQty() %></td>
		<td><%=vo.getSumprice() %></td>
	<%} }%>
	</tr>
</table>
</form>
<script>
	function che(){
		if(frm.R_dt.value>frm.R_dt2.value){
			alert('날짜입력이 잘못돼었습니다.');
			return false;
		}
		if(frm.R_dt.value===null||frm.R_dt.value===''){
			alert('시작 날짜를 입력해 주세요.');
			return false;
		}
		if(frm.R_dt2.value===null||frm.R_dt2.value===''){
			alert('종료 날짜를 입력해 주세요.');
			return false;
		}
	}
</script>