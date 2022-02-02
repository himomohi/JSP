package org.service;


import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dao.BoardDAO;
import org.entity.BoardDTO;



public class BoardReplyuiCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		
		int num=Integer.parseInt(req.getParameter("num"));
		BoardDAO dao=new BoardDAO();
		BoardDTO data=dao.replyui(num);
		req.setAttribute("replyui", data);
		
		
	
		
	}
	
	

}



