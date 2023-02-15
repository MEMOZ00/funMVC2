<%@page import="com.itwillbs.board.db.boardDTO"%>
<%@page import="com.itwillbs.board.db.boardDAO"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
boardDAO dao = new boardDAO();
List<boardDTO> boardList = dao.getBoardList(1, 5);

JSONArray arr = new JSONArray();
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

for(int i = 0; i < boardList.size(); i++) {
	boardDTO dto = boardList.get(i);
	
	JSONObject object = new JSONObject();
	object.put("num", dto.getNum());
	object.put("subject", dto.getSubject());
	object.put("date", dateFormat.format(dto.getDate()));
	
	arr.add(object);
}
%>
<%=arr%>