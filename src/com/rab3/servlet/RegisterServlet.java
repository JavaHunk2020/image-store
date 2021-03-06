package com.rab3.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rab3.utils.AppDBConnection;

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
		try {
			//Creating connection
			Connection connection=AppDBConnection.getConnection();
			String sql="insert into profiles_tbl(username,password,name,email,gender,photo,doe)  values(?,?,?,?,?,?,?);";
			//compiling the query
			PreparedStatement pstmt=connection.prepareStatement(sql);
			//setting values inside the compiled query
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			pstmt.setString(3,name);
			pstmt.setString(4,email);
			pstmt.setString(5,gender);
			pstmt.setString(6,image);
			Timestamp timestamp=new Timestamp(new Date().getTime());
			pstmt.setTimestamp(7,timestamp);
			//Firing the query and getting data into result set
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		req.setAttribute("msg", "You are successfully registered!!!");
		req.getRequestDispatcher("register.jsp").forward(req, resp);
	}

}
