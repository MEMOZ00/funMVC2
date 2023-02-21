package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberUpdateForm implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberUpdateForm execute()");
		// 세션 객체생성
		//String id = 세션값 가져오기
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		
		// MemberDAO 객체생성 
		// MemberDTO dto = getMember() 메서드 호출
		MemberDAO dao=new MemberDAO();
		MemberDTO dto=dao.getMember(id);
		
		// request dto 저장
		request.setAttribute("dto", dto);
		
		// member/update.jsp 이동
		ActionForward forward=new ActionForward();
		forward.setPath("member/update.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
