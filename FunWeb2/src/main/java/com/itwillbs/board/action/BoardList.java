package com.itwillbs.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.boardDAO;
import com.itwillbs.board.db.boardDTO;

public class BoardList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardList excute()");
		
		boardDAO dao = new boardDAO();

		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		
		if(pageNum==null) {
			pageNum="1";
		}
		
		//DB에서 가져올 페이지 번호
		//현재 지정된 페이지 번호
		int currentPage=Integer.parseInt(pageNum);
		
		//시작 페이지 번호
		int startRow = pageSize*(currentPage-1)+1;
		
		//끝 페이지 번호
		int endRow = startRow+pageSize-1;
		
		//mysql limit 시작행 및 시작행으로부터 가져올 갯수
		int start = startRow-1;
		int num = pageSize;
		ArrayList<boardDTO> dtolist = dao.getBoardList(start, num);
		
		//하단에 보여지는 페이지 번호
		int pageBlock = 10;
		int startPage = (currentPage-1)/pageBlock*pageBlock+1;
		int endPage = startPage+pageBlock-1;
		
		//총 게시글 수
		int allPage = dao.getBoardPage();
		
		// 나타낼 페이지 수
		int pageCount = allPage/pageSize+(allPage%pageSize==0?0:1);
		
		// endPage 재설정
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		//request에 담아서 list.jsp로 가져가지
		request.setAttribute("dtolist", dtolist);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("endPage", endPage);
		request.setAttribute("allPage", allPage);

		//이동
		ActionForward forward = new ActionForward();
		forward.setPath("center/notice.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
	
}
