package com.itwillbs.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardList implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardList execute()");
		// 한글처리
		request.setCharacterEncoding("utf-8");
		// 검색어 가져오기
		String search=request.getParameter("search");
		
		//한 페이지에 보여줄 글 가져오기
		BoardDAO dao=new BoardDAO();
		int pageSize=10;
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null){
			pageNum="1";		
		}
		int currentPage=Integer.parseInt(pageNum);
		int startRow=(currentPage-1)*pageSize+1;
		int endRow = startRow+pageSize-1;
		
		//검색어 
		ArrayList<BoardDTO> boardList=null;
		if(search==null) {
			//검색어 없음
			boardList=dao.getBoardList(startRow,pageSize);
		}else {
			// 검색어 있음
			boardList=dao.getBoardList(startRow,pageSize,search);			
		}
		
		
		// 페이징 관련 계산식
		int pageBlock=10;
		int startPage=(currentPage-1)/pageBlock*pageBlock+1;
		int endPage=startPage+pageBlock-1;
		
		int count=0;
		//검색어 
		if(search==null) {
			//검색어 없음
			count = dao.getBoardCount();
		}else {
			// 검색어 있음
			count = dao.getBoardCount(search);			
		}
		
		
		int pageCount=count/pageSize+(count%pageSize==0?0:1);
		if(endPage > pageCount){
			endPage = pageCount;
		}
		
		// request 가져온 데이터 담기
		request.setAttribute("boardList", boardList);
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		
		//검색어
		request.setAttribute("search", search);
		
		//이동 center/notice.jsp
		ActionForward forward=new ActionForward();
		forward.setPath("center/notice.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
