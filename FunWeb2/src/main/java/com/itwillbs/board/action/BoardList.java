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
		
		request.setCharacterEncoding("utf-8");
		
		String search = request.getParameter("search");
		
		
		//현재 게시글에서 사용자가 보고있는 페이지 번호 가져오기
		boardDAO dao = new boardDAO();

		String pageNum = request.getParameter("pageNum");
		
		if(pageNum==null) {
			pageNum="1";
		}
		
		//현재 게시글에서 지정된 페이지 번호
		int currentPage=Integer.parseInt(pageNum); 
		
		//지정된 페이지에서 가져올 시작행 계산
		int pageSize = 3; // 한 페이지에 올릴 게시글 수
		int startRow = pageSize*(currentPage-1)+1; // 페이지 번호가 달라지는 것에 대한 시작행 계산 
		
		//지정된 페이지에서 가져올 끝행 계산
		int endRow = startRow+pageSize-1;
		
		//mysql limit 시작행 및 시작행으로부터 가져올 갯수
		int start = startRow-1; // 시작행
		int num = pageSize; // 시작행부터 가져올 행 개수
		
		ArrayList<boardDTO> dtolist = null;
		
		//검색어가 있는 글 DB에 불러오기
		if(search==null) {
			dtolist = dao.getBoardList(start, num);	
		}else{
			dtolist = dao.getBoardList(start, num, search);

		}
		
		//하단에 보여지는 페이지 번호
		int pageBlock = 3;
		int startPage = (currentPage-1)/pageBlock*pageBlock+1;
		int endPage = startPage+pageBlock-1;
		
		//총 게시글 개수 세기
		int count = 0;
		
		if(search==null) {
			count = dao.getBoardPage();
		}else{
			count = dao.getBoardPage(search);
		}
		
		// 나타낼 페이지 수
		int pageCount = count/pageSize+(count%pageSize==0?0:1);
		
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
		request.setAttribute("count", count);
		request.setAttribute("pageCount", pageCount);

		//검색어
		request.setAttribute("search", search);
		
		//이동
		ActionForward forward = new ActionForward();
		forward.setPath("center/notice.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
	
}
