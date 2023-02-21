package com.itwillbs.board.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class ReBoardWritePro implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReBoardWritePro execute()");
		// request 한글처리
		request.setCharacterEncoding("utf-8");
		// request name, subject,content 가져와서 변수에 저장
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		int readcount=0;
		Timestamp date=new Timestamp(System.currentTimeMillis());
		// 답글 관련 
		int re_ref=Integer.parseInt(request.getParameter("re_ref"));
		int re_lev=Integer.parseInt(request.getParameter("re_lev"));
		int re_seq=Integer.parseInt(request.getParameter("re_seq"));
		
		// BoardDTO 객체생성
		// set메서드 호출해서 값 저장
		BoardDTO dto=new BoardDTO();
		dto.setName(name);
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setReadcount(readcount);
		dto.setDate(date);
		//답글
		dto.setRe_ref(re_ref);
		dto.setRe_lev(re_lev);
		dto.setRe_seq(re_seq);
		
		// BoardDAO 객체생성
		// insertBoard(dto) 메서드 호출
		BoardDAO dao=new BoardDAO();
		dao.reinsertBoard(dto);
		
		// BoardList.bo 이동
		ActionForward forward=new ActionForward();
		forward.setPath("BoardList.bo");
		forward.setRedirect(true);
		return forward;
	}
}
