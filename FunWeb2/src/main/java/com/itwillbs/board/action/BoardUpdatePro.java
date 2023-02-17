package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.boardDAO;
import com.itwillbs.board.db.boardDTO;

public class BoardUpdatePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardUpdatePro excute()");
		
		request.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		boardDTO dto = new boardDTO();
		dto.setNum(num);
		dto.setName(name);
		dto.setSubject(subject);
		dto.setContent(content);

		boardDAO dao = new boardDAO();
		dao.updateBoard(dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("BoardList.bo");
		forward.setRedirect(true);
		
		return forward;
	}
	
}
