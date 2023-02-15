<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");

MemberDTO dto = new MemberDTO();
dto.setId(request.getParameter("id"));
dto.setPass(request.getParameter("pass"));
dto.setName(request.getParameter("name"));
dto.setDate(new Timestamp(System.currentTimeMillis()));
dto.setEmail(request.getParameter("email"));
dto.setAddress(request.getParameter("address"));
dto.setPhone(request.getParameter("phone"));
dto.setMobile(request.getParameter("mobile"));

MemberDAO dao = new MemberDAO();
dao.insertMember(dto);

response.sendRedirect("login.jsp");
%>