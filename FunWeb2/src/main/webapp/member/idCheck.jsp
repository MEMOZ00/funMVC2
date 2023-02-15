<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id =request.getParameter("id");
MemberDAO dao = new MemberDAO();
MemberDTO dto = dao.getMember(id);
String result = "";
if(dto!=null) {
	result = "중복된 아이디 입니다";
	}
else {
	result = "사용 가능한 아이디입니다";
	}
%>
<%=result%>