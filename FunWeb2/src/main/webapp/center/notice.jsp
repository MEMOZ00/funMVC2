<%@page import="com.itwillbs.board.db.boardDAO"%>
<%@page import="com.itwillbs.board.db.boardDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="css/subpage.css" rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE 6]>
 <script src="../script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]-->
</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
<jsp:include page="../inc/top.jsp" />
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 메인이미지 -->
<div id="sub_img_center"></div>
<!-- 메인이미지 -->

<!-- 왼쪽메뉴 -->


<nav id="sub_menu">
<ul>
<li><a href="#">Notice</a></li>
<li><a href="#">Public News</a></li>
<li><a href="#">Driver Download</a></li>
<li><a href="#">Service Policy</a></li>
</ul>
</nav>
<!-- 왼쪽메뉴 -->
<%
/* String id = (String)session.getAttribute("id");
boardDAO dao = new boardDAO();

int pageSize = 10;
String pageNum = request.getParameter("pageNum");
if(pageNum==null) {
	pageNum="1";
}
int currentPage=Integer.parseInt(pageNum);
//시작 페이지 번호
int startRow = pageSize*(currentPage-1)+1;
//끝 페이지 번호
int endRow = startRow+pageSize-1;
//mysql limit 시작행 및 시작행으로부터 가져올 갯수
int start = startRow-1;
int num = pageSize;
ArrayList<boardDTO> dtolist = dao.getBoardList(start, num); */

SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd"); 

ArrayList<boardDTO> dtolist = (ArrayList<boardDTO>)request.getAttribute("dtolist");

int currentPage = (Integer)request.getAttribute("currentPage");
int startPage = (Integer)request.getAttribute("startPage");
int pageBlock = (Integer)request.getAttribute("pageBlock");
int endPage= (Integer)request.getAttribute("endPage");
int allPage = (Integer)request.getAttribute("allPage");
%>
<!-- 게시판 -->
<article>
<h1>Notice</h1>
<table id="notice">

<tr><th class="tno">No.</th>
    <th class="ttitle">Title</th>
    <th class="twrite">Writer</th>
    <th class="tdate">Date</th>
    <th class="tread">Read</th></tr>
	<% 
	for(int i = 0; i < dtolist.size(); i++) {
		boardDTO dto = dtolist.get(i);
	%>
	<tr>
	<td><%=dto.getNum() %></td>
	<td class="left"><a href="BoardContent.bo?num=<%=dto.getNum()%>"><%=dto.getSubject() %></a></td>
	<td><%=dto.getName() %></td>
	<td><%=dateFormat.format(dto.getDate()) %></td>
	<td><%=dto.getReadcount() %></td>
	</tr>	
		<%
	}
	%>
</table>

<div id="table_search">
<input type="text" name="search" class="input_box">
<input type="button" value="검색" class="btn" onclick="location.href='BoardWriteForm.bo'">
<%
String id =(String)session.getAttribute("id");
if(id!=null) {
%>
	<input type="button" value="글쓰기" class="btn" onclick="location.href='BoardWriteForm.bo'">
<%
}
%>
</div>
<div class="clear"></div>
<div id="page_control">
<%-- <% 
// startPage~endPage 1~10, 11~20, 21~30
int pageBlock = 10;
int startPage = (currentPage-1)/pageBlock*pageBlock+1;
int endPage = startPage+pageBlock-1;
//총 게시글 수
int allPage = dao.getBoardPage();
// 나타낼 페이지 수
int pageCount = allPage/pageSize+(allPage%pageSize==0?0:1);
// endPage 재설정
if(endPage > pageCount) {
	endPage = pageCount;
}
//
%> --%>
<%
if(startPage > pageBlock) {
%>
	<a href="BoardList.bo?pageNum=<%= currentPage-pageBlock%>">[10페이지 이전]</a>
<% 
}
for(int i = startPage; i <= endPage; i++) {	
%>
	<a href="BoardList.bo?pageNum=<%= i%>"><%= i%></a> 
<% 
}
if(endPage > allPage) {
%>
	<a href="BoardList.bo?pageNum=<%= currentPage+pageBlock%>">[10페이지 다음]</a> 
<%
}
%>
</div>
</article>
<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp" />
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>