package org.service;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardCommand {
	public void execute(HttpServletRequest req,HttpServletResponse resp);

}
