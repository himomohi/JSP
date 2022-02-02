package org.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.service.BoardCommand;
import org.service.BoardDeleteCommand;
import org.service.BoardListCommand;
import org.service.BoardReplyCommand;
import org.service.BoardReplyuiCommand;
import org.service.BoardSearchCommand;
import org.service.BoardUpdateCommand;
import org.service.BoardWriteCommand;
import org.service.BoardrRetrieveCommand;



@WebServlet("*.do")
public class BoardFrontController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI= req.getRequestURI();		
		String contextPath = req.getContextPath();
		String com=requestURI.substring(contextPath.length());

		
		
		BoardCommand command=null;
		String nextPage=null;
		
		if(com.equals("/list.do")) {
			command = new BoardListCommand();
			command.execute(req, resp);
			System.out.println("테스트2,"+command.toString());
			
			nextPage= "list.jsp";
			
		}
		
		if(com.equals("/write.do")) {
			command = new BoardWriteCommand();
			command.execute(req, resp);
			System.out.println("테스트2,"+command.toString());
			
			nextPage= "list.do";
			
		}
		
		if(com.equals("/retrieve.do")) {
			command = new BoardrRetrieveCommand();
			command.execute(req, resp);
			System.out.println("테스트2,"+command.toString());
			
			nextPage= "retrieve.jsp";
			
		}
		
		if(com.equals("/delete.do")) {
			command = new BoardDeleteCommand();
			command.execute(req, resp);
			System.out.println("삭제커맨드"+command.toString());
			
			nextPage= "list.do";
			
		}
		
		
		if(com.equals("/update.do")) {
			command = new BoardUpdateCommand();
			command.execute(req, resp);
			System.out.println("업데이트 커맨드 적용 됨");
			
			nextPage= "list.do";
			
		}
		
		

		if(com.equals("/update.do")) {
			command = new BoardUpdateCommand();
			command.execute(req, resp);
			System.out.println("업데이트 커맨드 적용 됨");
			
			nextPage= "list.do";
			
		}
		
		if(com.equals("/search.do")) {
			command = new BoardSearchCommand();
			command.execute(req, resp);
			System.out.println("검색 커맨드 적용 됨");
			
			nextPage= "list.jsp";
			
		}
		
		if(com.equals("/replyui.do")) {
			command = new BoardReplyuiCommand();
			command.execute(req, resp);
			System.out.println("리플 커맨드 적용 됨");
			
			nextPage= "reply.jsp";
			
		}
		
		if(com.equals("/reply.do")) {
			command = new BoardReplyCommand();	
			command.execute(req, resp);
			
			System.out.println("댓글 커맨드 적용 됨");
			
			nextPage= "list.do";
			
		}
		RequestDispatcher dis=req.getRequestDispatcher(nextPage);
		
		dis.forward(req, resp);
	
	}
	
	
	
	

}
