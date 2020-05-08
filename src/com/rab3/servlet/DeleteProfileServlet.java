package com.rab3.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Do not forgot to write forward slash here 
@WebServlet("/deleteProfile")
public class DeleteProfileServlet extends HttpServlet {
	
	/**
	 * @req - it will hold request data from the client!!
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uname=req.getParameter("uname");
		try {
			//Loading Driver
			Class.forName("com.mysql.jdbc.Driver");
			//Creating connection
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/happy_hrs_db","root","mysql@1234");
			String sql="delete  from  profiles_tbl where username =?";
			//compiling the query
			PreparedStatement pstmt=connection.prepareStatement(sql);
			//setting values inside the compiled query
			pstmt.setString(1,uname);
			//Firing the query and getting data into result set
			pstmt.executeUpdate();
			//Dispatcher from servle tp servlet
			req.getRequestDispatcher("profiles").forward(req, resp);
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
