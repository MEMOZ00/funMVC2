package com.itwillbs.board.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.boardDAO;
import com.itwillbs.board.db.boardDTO;

public class BoardWritePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardWritePro excute()");
		
		request.setCharacterEncoding("utf-8");

		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		int readcount = 0; 

		boardDTO dto = new boardDTO();
		dto.setName(name);
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setDate(new Timestamp(System.currentTimeMillis()));
		dto.setReadcount(readcount);

		boardDAO dao = new boardDAO();
		dao.insertBoard(dto);

//		response.sendRedirect("list.jsp");
		ActionForward forward = new ActionForward();
		forward.setPath("BoardList.bo");
		forward.setRedirect(true);
		
		return forward;
	}
	
}
