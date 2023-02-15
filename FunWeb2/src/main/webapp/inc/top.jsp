<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
<%
String id=(String)session.getAttribute("id");
if(id != null) { %>
	<div id="login"><%=id %> 님 | 
	<a href="MemberLogout.me">logout</a> | 
	<a href="MemberUpdateForm.me">update</a></div>
	<% 
} else { %>
	<div id="login">
	<a href="MemberLoginForm.me">login</a> | 
	<a href="MemberInsertForm.me">join</a></div>
<%
}
%>

<div class="clear"></div>
<!-- 로고들어가는 곳 -->
<div id="logo"><img src="images/logo.gif" width="265" height="62" alt="Fun Web"></div>
<!-- 로고들어가는 곳 -->
<nav id="top_menu">
<ul>
	<li><a href="MemberMain.me">HOME</a></li>
	<li><a href="CompanyWelcome.me">회사소개</a></li>
	<li><a href="#">SOLUTIONS</a></li>
	<li><a href="BoardList.bo">CUSTOMER CENTER</a></li>
	<li><a href="#">CONTACT US</a></li>
</ul>
</nav>
</header>