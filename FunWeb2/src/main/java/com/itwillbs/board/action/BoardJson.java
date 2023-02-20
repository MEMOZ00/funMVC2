package com.itwillbs.board.action;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.itwillbs.board.db.boardDAO;
import com.itwillbs.board.db.boardDTO;

public class BoardJson implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardJson excute()");
		
		boardDAO dao = new boardDAO();
		ArrayList<boardDTO> dtolist = new ArrayList<>();
		dtolist = dao.getBoardList(1, 5);
		//dtolist -> json 변경
		
		JSONArray arr = new JSONArray();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		
		for(int i = 0; i < dtolist.size(); i++) {
			boardDTO dto = dtolist.get(i);
			
			JSONObject obj = new JSONObject(); // JSON obj = boardDTO dto 하나의 객체에 여러개의 멤버변수를 담을 수 있음
			
			obj.put("num", dto.getNum()); // 키, 값의 맵 형태로 넘겨줌
			obj.put("subject", dto.getSubject());
			obj.put("date", dateFormat.format(dto.getDate()));
			
			arr.add(obj);
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(arr);
		out.close();
		
		return null;
	}
	
}
