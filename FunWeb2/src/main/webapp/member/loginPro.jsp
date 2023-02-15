<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = request.getParameter("id");
String pass = request.getParameter("pass");

MemberDAO dao = new MemberDAO();
MemberDTO dto = dao.userCheck(id, pass);

if(dto!=null) {
	session.setAttribute("id", id);
    response.sendRedirect("../main/main.jsp");
}
else {
	 %>
	    <script type="text/javascript">
			alert("아이디 비밀번호 틀림");
			history.back();
	    </script>
	    <%
	}
%>