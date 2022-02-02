package org.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dao.BoardDAO;

public class BoardWriteCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String title=req.getParameter("title");
		String author=req.getParameter("author");
		String content=req.getParameter("content");
		
		BoardDAO dao=new BoardDAO();
		dao.write(title, author, content);
	}
}
