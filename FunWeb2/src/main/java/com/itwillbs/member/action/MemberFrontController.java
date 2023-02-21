package com.itwillbs.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFrontController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController doGet()");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController doPost()");
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController doProcess()");
		
		//가상 주소 뽑아오기
		System.out.println("뽑은 가상주소 : "+request.getServletPath());
		String sPath=request.getServletPath();
		
		//------------------------------------------------
		//가상 주소 매핑(비교)
		Action action=null;
		ActionForward forward=null;
		if(sPath.equals("/MemberInsertForm.me")) {
			// member/join.jsp 이동
			forward=new ActionForward();
			forward.setPath("member/join.jsp");
			forward.setRedirect(false);
		}else if(sPath.equals("/MemberInsertPro.me")) {
			action=new MemberInsertPro();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(sPath.equals("/MemberLoginForm.me")) {
			forward=new ActionForward();
			forward.setPath("member/login.jsp");
			forward.setRedirect(false);
		}else if(sPath.equals("/MemberLoginPro.me")) {
			action=new MemberLoginPro();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(sPath.equals("/MemberMain.me")) {
			forward=new ActionForward();
			forward.setPath("main/main.jsp");
			forward.setRedirect(false);
		}else if(sPath.equals("/MemberLogout.me")) {
			action=new MemberLogout();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(sPath.equals("/MemberIdCheck.me")) {
			action=new MemberIdCheck();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(sPath.equals("/MemberUpdateForm.me")) {
			action=new MemberUpdateForm();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(sPath.equals("/MemberUpdatePro.me")) {
			action=new MemberUpdatePro();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//----------------------------------------------
		//주소 이동
		if(forward!=null) {
			if(forward.isRedirect()==true) {
				response.sendRedirect(forward.getPath());
			}else {
				//forward.isRedirect()==false
				RequestDispatcher dispatcher=
				request.getRequestDispatcher(forward.getPath());
		        dispatcher.forward(request, response);
			}//if
		}//if
		
		
	}//doProcess()

}//MemberFrontController
