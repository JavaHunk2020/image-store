package com.rab3.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Do not forgot to write forward slash here 
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	/**
	 * @req - it will hold request data from the client!!
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String gender=req.getParameter("gender");
		String image=req.getParameter("image");
		//Code to save all these data into the database!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 ///JDBC Programming
		//
		req.setAttribute("msg", "You are successfully registered!!!");
		req.getRequestDispatcher("register.jsp").forward(req, resp);
	}

}
