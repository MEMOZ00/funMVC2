package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;

public class BoardDeletePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardDeletePro execute()");
		// request  => num 파라미터 => 변수 저장 
		int num=Integer.parseInt(request.getParameter("num"));
		// BoardDAO 객체생성
		BoardDAO dao=new BoardDAO();
		// deleteBoard(num) 메서드 호출
		dao.deleteBoard(num);
		// BoardList.bo 이동
		ActionForward forward=new ActionForward();
		forward.setPath("BoardList.bo");
		forward.setRedirect(true);
		return forward;
	}

}
