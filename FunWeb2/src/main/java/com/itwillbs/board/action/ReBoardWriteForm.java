package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDTO;

public class ReBoardWriteForm implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReBoardWriteForm execute()");
		
		BoardDTO dto=new BoardDTO();
		dto.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
		dto.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
		dto.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
		
		request.setAttribute("dto", dto);
		
		ActionForward forward=new ActionForward();
		forward.setPath("center/rewrite.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
