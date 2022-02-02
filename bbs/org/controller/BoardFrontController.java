package org.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.service.BoardCommand;
import org.service.BoardListCommand;
import org.service.BoardRetriveCommand;
import org.service.BoardWriteCommand;

@WebServlet("*.do")
public class BoardFrontController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String requestURI=req.getRequestURI();
		String contextPath=req.getContextPath();
		String com=requestURI.substring(contextPath.length());
		
		BoardCommand command=null;
		String nextPage=null;
		
		if(com.equals("/list.do")) {
			command=new BoardListCommand();
			command.execute(req, resp);
			nextPage="list.jsp";
		}
		
		if(com.equals("/writeui.do")) {
			nextPage="write.jsp";
		}
		
		if(com.equals("/write.do")) {
			command=new BoardWriteCommand();
			command.execute(req, resp);
			nextPage="list.do";
		}
		
		if(com.equals("/retrieve.do")) {
			command=new BoardRetriveCommand();
			command.execute(req, resp);
			nextPage="retrive.jsp";
		}
		
		RequestDispatcher dis=req.getRequestDispatcher(nextPage);
		dis.forward(req, resp);
	}

	
}
