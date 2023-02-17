package com.itwillbs.board.action;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.board.db.boardDAO;

public class BoardDeletePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int num = Integer.parseInt(request.getParameter("num"));
		
		boardDAO dao = new boardDAO();
		dao.deleteBoard(num);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/BoardList.bo");
		forward.setRedirect(true);
		
		return forward;
	}
	
}
