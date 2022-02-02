package org.service;


import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dao.BoardDAO;
import org.entity.BoardDTO;



public class BoardReplyCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		
	
			
		int num = Integer.parseInt(req.getParameter("num"));
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String content = req.getParameter("content");
		int repRoot = Integer.parseInt(req.getParameter("repRoot"));
		int repStep = Integer.parseInt(req.getParameter("repStep"));
		int repIndent = Integer.parseInt(req.getParameter("repIndent"));

		
		
		
		BoardDAO dao= new BoardDAO();
		
		dao.reply(num, title, author, content, repRoot, repStep, repIndent);
		
	
		
		
		
	
		
	}
	
	

}



