package org.service;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dao.BoardDAO;
import org.entity.BoardDTO;



public class BoardListCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		resp.setContentType("text/html; charset=utf-8");
		BoardDAO dao= new BoardDAO();
		
		
		ArrayList<BoardDTO> list=dao.select();
		
		System.out.println("테스트"+list);
		req.setAttribute("list",list );
		
	}
	
	

}



