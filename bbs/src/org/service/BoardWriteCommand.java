package org.service;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dao.BoardDAO;
import org.entity.BoardDTO;



public class BoardWriteCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resp.setContentType("text/html; charset=utf-8");
		BoardDAO dao= new BoardDAO();
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String content = req.getParameter("content");
		
		dao.write(title, author, content);
		
	
	
		
	}
	
	

}



