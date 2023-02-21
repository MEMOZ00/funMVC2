package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberUpdatePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberUpdatePro execute()");
		//request 한글처리
		request.setCharacterEncoding("utf-8");
		//request 태그이름에 해당하는 값을 가져오기 
		String id=request.getParameter("id");
		String pass=request.getParameter("pass");
		String name=request.getParameter("name");
		// 수정할 내용을 바구니 객체생성 => 바구니에 저장
		MemberDTO updateDto=new MemberDTO();
		updateDto.setId(id);
		updateDto.setPass(pass);
		updateDto.setName(name);
		
		// MemberDAO 객체생성
		MemberDAO dao=new MemberDAO();
		// MemberDTO dto = userCheck(id,pass) 메서드 호출
		MemberDTO dto=dao.userCheck(id, pass);
		// 아이디 비밀번호 일치 dto!=null =>
		//              dao.updateMember(updateDto) 메서드 호출
		//              MemberMain.me 이동
		// 아이디 비밀번호 틀림 dto==null => 아이디 비밀번호 틀
		ActionForward forward=null;
		if(dto!=null) {
			dao.updateMember(updateDto);
			
			forward=new ActionForward();
			forward.setPath("MemberMain.me");
			forward.setRedirect(true);
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script type='text/javascript'>");
			out.println("alert('아이디 비밀번호 틀림');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		return forward;
	}

}
