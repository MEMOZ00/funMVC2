package com.itwillbs.board.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardJson implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardJson execute()");
		//최근글 5개
		BoardDAO dao=new BoardDAO();
		ArrayList<BoardDTO> boardList=dao.getBoardList(1,5);
		// boardList => json 변경
		JSONArray arr=new JSONArray();
		//날짜
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy.MM.dd");
		for(int i=0;i<boardList.size();i++) {
			BoardDTO dto=boardList.get(i);
			//한개 글
			JSONObject object=new JSONObject();
			object.put("num", dto.getNum());
			object.put("subject", dto.getSubject());
			object.put("date", dateFormat.format(dto.getDate()));
			// 배열 한칸에 저장
			arr.add(object);
		}
		// 출력
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println(arr);
		out.close();
		return null;
	}
}
