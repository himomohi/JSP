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
		BoardDAO dao=new BoardDAO();
		ArrayList<BoardDTO> list=dao.list();
		
		req.setAttribute("list", list);
	}
	
}
