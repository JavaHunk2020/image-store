package com.rab3.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Do not forgot to write forward slash here 
@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
	
	/**
	 * @req - it will hold request data from the client!!
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		try {
			//Loading Driver
			Class.forName("com.mysql.jdbc.Driver");
			//Creating connection
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/happy_hrs_db","root","mysql@1234");
			String sql="select  *  from profiles_tbl where username =? and password = ?";
			//compiling the query
			PreparedStatement pstmt=connection.prepareStatement(sql);
			//setting values inside the compiled query
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			//Firing the query and getting data into result set
			ResultSet resultSet=pstmt.executeQuery();
			if(resultSet.next()) {
				 //Go to home.jsp
				//Below line will forward your request from servlet to the JSP
				String name=resultSet.getString(4);
				String email=resultSet.getString(5);
				String gender=resultSet.getString(6);
				String photo=resultSet.getString(7);
				Timestamp doe=resultSet.getTimestamp(8);
				ProfileDTO profileDTO=new ProfileDTO();
				profileDTO.setName(name);
				profileDTO.setEmail(email);
				profileDTO.setGender(gender);
				profileDTO.setImage(photo);
				profileDTO.setUsername(username);
				profileDTO.setPassword(password);
				profileDTO.setDoe(doe);
				//Hey I need this data on home.jsp
				req.setAttribute("profileDTO", profileDTO);
				req.getRequestDispatcher("home.jsp").forward(req, resp);
			}else {
				 //go to login.jsp
				//req ->is kind of HashMap
				//msg - key will be always string and value could be anything
				req.setAttribute("msg", "Sorry!! username and password are not valid!!!!!!!!!!!!!!!");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
			
		}catch (Exception e) {
				e.printStackTrace();
		}
	}
}
