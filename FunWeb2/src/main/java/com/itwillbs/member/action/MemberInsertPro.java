package com.itwillbs.member.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberInsertPro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberInsertPro excute()");
				//폼에서 입력한 내용이 서버에 전달 => request 내장객체 저장
				//request 한글처리
				request.setCharacterEncoding("utf-8");
				// request 태그이름에 해당하는 값을 가져오기 => 변수에 저장
				String id=request.getParameter("id");
				String pass=request.getParameter("pass");
				String name=request.getParameter("name");
				//가입날짜 => 시스템 날짜 
				// java.sql.Timestamp 자바내장객체 => 날짜 
				Timestamp date=new Timestamp(System.currentTimeMillis());
				
				MemberDTO dto=new MemberDTO();
				System.out.println("MemberDTO 바구니 주소 : "+dto);
				// => 기억장소에 id, pass, name, date 값을 저장
				dto.setId(id);
				dto.setPass(pass);
				dto.setName(name);
				dto.setDate(date);
				
				MemberDAO dao=new MemberDAO();
				System.out.println("MemberDAO 주소 : "+dao);
				// dao.insertMember(id, pass, name, date);
				dao.insertMember(dto);
				
				//MemberLoginForm.me 주소변경되면서 이동정보 저장 
				ActionForward forward=new ActionForward();
				forward.setPath("MemberLoginForm.me");
				forward.setRedirect(true);		
		
		return forward;
	}
	
}
