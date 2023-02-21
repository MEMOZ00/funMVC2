package com.itwillbs.board.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardWritePro implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardWritePro execute()");
		// request 한글처리
		request.setCharacterEncoding("utf-8");
		// request name, subject,content 가져와서 변수에 저장
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		int readcount=0;
		Timestamp date=new Timestamp(System.currentTimeMillis());
		
		// BoardDTO 객체생성
		// set메서드 호출해서 값 저장
		BoardDTO dto=new BoardDTO();
		dto.setName(name);
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setReadcount(readcount);
		dto.setDate(date);
		
		// BoardDAO 객체생성
		// insertBoard(dto) 메서드 호출
		BoardDAO dao=new BoardDAO();
		dao.insertBoard(dto);
		
		// BoardList.bo 이동
		ActionForward forward=new ActionForward();
		forward.setPath("BoardList.bo");
		forward.setRedirect(true);
		return forward;
		
	}
}
