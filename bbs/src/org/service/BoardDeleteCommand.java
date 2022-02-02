package org.service;





import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dao.BoardDAO;




public class BoardDeleteCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {

		int num=Integer.parseInt(req.getParameter("num"));
		
		BoardDAO dao=new BoardDAO();
		
		
		dao.delete(num);

		
	}
	
	

}



