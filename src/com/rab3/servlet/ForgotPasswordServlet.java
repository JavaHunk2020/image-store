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
@WebServlet("/forgotPassword")
public class ForgotPasswordServlet extends HttpServlet {
	/**
	 * @req - it will hold request data from the client!!
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		try {
			//Loading Driver
			Class.forName("com.mysql.jdbc.Driver");
			//Creating connection
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/happy_hrs_db","root","mysql@1234");
			String sql="select password  from profiles_tbl where email =?";
			//compiling the query
			PreparedStatement pstmt=connection.prepareStatement(sql);
			//setting values inside the compiled query
			pstmt.setString(1,email);
			//Firing the query and getting data into result set
			ResultSet resultSet=pstmt.executeQuery();
			if(resultSet.next()) {
				 //Go to home.jsp
				//Below line will forward your request from servlet to the JSP
				String password=resultSet.getString(1);
				//Hey I need this data on home.jsp
				req.setAttribute("password","Hello your password is  =  "+ password);
			}else {
				req.setAttribute("password", "Sorry!! email is not valid!!!!!!!!!!!!!!!");
			}
			
			req.getRequestDispatcher("forgotPassword.jsp").forward(req, resp);
		}catch (Exception e) {
				e.printStackTrace();
		}
	}
}
