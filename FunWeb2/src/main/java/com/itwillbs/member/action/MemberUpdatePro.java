package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberUpdatePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberUpdatePro excute()");
		
		String id=request.getParameter("id");
		String pass=request.getParameter("pass");
		String name=request.getParameter("name");
		
		MemberDTO updateDto=new MemberDTO();
		updateDto.setId(id);
		updateDto.setPass(pass);
		updateDto.setName(name);
		
		MemberDAO dao=new MemberDAO();
		MemberDTO dto=dao.userCheck(id, pass);
		
		ActionForward forward = null;
		
		if(dto!=null){
			dao.updateMember(updateDto);
			forward = new ActionForward();
			forward.setPath("MemberMain.me");
			forward.setRedirect(true);
			
		}else{
			response.setContentType("text/html; charset=UTF-8");
			
			// 자바에서 html(자바스크립트) 동작 코드 생성
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>"); // PrintWrite 자바 출력클래스, response(HttServlet 클래스 내장객체)
			out.println("alert('아이디 비밀번호 틀림');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			forward = null;
		}
		
		return forward;
	}
	
}
