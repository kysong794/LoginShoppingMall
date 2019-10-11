<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="shoppingMall.*" %>
<%
	List<ShoppingVo> psalemon = (List<ShoppingVo>) request.getAttribute("psalemon");
	List<ShoppingVo> list = (List<ShoppingVo>)request.getAttribute("list");

	String year = (String) request.getAttribute("year");
	String mon = (String) request.getAttribute("mon");
	String i_product = (String) request.getAttribute("i_product");

%>

<form id="frm" action="psalemon" method="post" onsubmit='return che();'>
<table>
	<tr>
		<th>연도:</th>
		<td><select name="year" >
			<%for (int i=2019; i<=2024; i++){ %>
				<% if(year != null && i == Integer.parseInt(year)) { %>
					<option value ="<%=i %>" selected><%=i %></option>
				<% } else { %>
					<option value ="<%=i %>"><%=i %></option>
				<% } %>
			<%} %>
			</select></td>
			
		<th>월:</th>
		<td><select name="mon" >
			<%for (int i=1; i<=12;i++){ %>
				<% if(mon != null && i == Integer.parseInt(mon)) { %>
					<option value ="<%=i %>" selected="selected"><%=i %></option>
				<% } else { %>
					<option value ="<%=i %>"><%=i %></option>
				<% } %>
			<%} %>
		</select></td>			
		<th>상품:</th>
		<td>
			<select name="i_product">
				<option value="0">--전체--</option>
				<% for(ShoppingVo vo : list){ %>										
					<option value ="<%=vo.getI_product() %>"
						<% if(vo.getI_product().equals(i_product) ){ %>
						selected="selected"
						<% } %>					
					><%=vo.getNm() %></option>					
				<% } %>		
			</select>
		</td>
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
	<%if(psalemon !=null){ %>
	<%for(ShoppingVo vo : psalemon){ %>
	<tr>
		<td><%=vo.getR_dt() %></td>
		<td><img src="<%=vo.getPic() %>"width="150" height="150"></td>
		<td><%=vo.getNm() %></td>
		<td><%=vo.getPrice() %>원</td>
		<td><%=vo.getQty() %>개</td>
		<td><%=vo.getSumprice() %>원</td>
	</tr>
	<% }  } %>
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