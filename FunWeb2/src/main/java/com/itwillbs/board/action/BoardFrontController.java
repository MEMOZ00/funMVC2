package com.itwillbs.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.action.MemberInsertPro;

public class BoardFrontController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardFrontController doGet()");
		doProcess(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardFrontController doPost()");
		doProcess(request,response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardFrontController doProcess()");
		System.out.println("BoardFrontController doProcess()");
		System.out.println("뽑은 가상주소 : "+request.getServletPath());
		String sPath=request.getServletPath();
		
		ActionForward forward=null;
		Action action=null;
		
		if(sPath.equals("/BoardList.bo")) {
			action = new BoardList();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			} 
		} 
		
		else if(sPath.equals("/BoardWriteForm.bo")) {
			forward = new ActionForward();
			forward.setPath("center/write.jsp");
			forward.setRedirect(false);
			
		}
		
		else if(sPath.equals("/BoardWritePro.bo")) {
			action = new BoardWritePro();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			} 
		}
		
		
			if(forward != null) {
				//이동방식비교
				if(forward.isRedirect()==true) {
					// excute함수가 실행되며 forward에 저장된 다른 페이지 주소로 이동함
					response.sendRedirect(forward.getPath());
				}else {
					// 주소가 유지되며 request 값과 가상주소를 Object class형으로 들고감
					RequestDispatcher dispatcher=
					request.getRequestDispatcher(forward.getPath());
			        dispatcher.forward(request, response);
				}
			}
	}
}
