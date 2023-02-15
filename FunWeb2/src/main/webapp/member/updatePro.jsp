<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String id = (String)session.getAttribute("id");
String pass = request.getParameter("pass");
String name = request.getParameter("name");
String email = request.getParameter("email");
String address = request.getParameter("address");
String phone = request.getParameter("phone");
String mobile = request.getParameter("mobile");

MemberDTO updateDto = new MemberDTO();
updateDto.setId(id);
updateDto.setPass(pass);
updateDto.setName(name);
updateDto.setEmail(email);
updateDto.setAddress(address);
updateDto.setPhone(phone);
updateDto.setMobile(mobile);

MemberDAO dao = new MemberDAO();
MemberDTO dto = dao.userCheck(id, pass);

if(dto!=null) {
	dao.updateMember(updateDto);
	response.sendRedirect("../main/main.jsp");
} else { %>
	<script type="text/javascript">                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
			alert("아이디 비밀번호 틀림");
			history.back();
	</script>
<% 
}
%>