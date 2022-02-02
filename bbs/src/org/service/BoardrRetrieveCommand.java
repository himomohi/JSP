package org.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dao.BoardDAO;
import org.entity.BoardDTO;

public class BoardrRetrieveCommand implements BoardCommand{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		int num=Integer.parseInt(req.getParameter("num"));
		
		BoardDAO dao=new BoardDAO();
		
		BoardDTO data=dao.retrieve(num);
		
		req.setAttribute("retrieve", data);
		
		
	}

}
