package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberIdCheck implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberIdCheck excute()");
		
		String id = request.getParameter("id");
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.getMember(id);
		
		String result = "";
		// forward 값 넘기지 않고 출력
		if(dto!=null) {
			result = "아이디 중복";
		} else {
			result = "아이디 사용 가능";
		}
		
		response.setContentType("text/html; charset=UTF-8"); // 자바에서 html(자바스크립트) 동작 코드 생성
		
		PrintWriter out = response.getWriter(); // PrintWrite 자바 출력클래스, response(HttServlet 클래스 내장객체)
		out.println(result);
		out.close();	
		
		return null;
	}

}
