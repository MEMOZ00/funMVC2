package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberUpdateForm implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberUpdateForm excute()");
		// session값 가져오기
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.getMember(id);
		
		request.setAttribute("dto", dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("member/update.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
	
}
