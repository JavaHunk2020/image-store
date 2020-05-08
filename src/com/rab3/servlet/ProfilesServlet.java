package com.rab3.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Do not forgot to write forward slash here 
@WebServlet("/profiles")
public class ProfilesServlet extends HttpServlet {
	
	/**
	 * @req - it will hold request data from the client!!
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//Loading Driver
			Class.forName("com.mysql.jdbc.Driver");
			//Creating connection
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/happy_hrs_db","root","mysql@1234");
			String sql="select  aid,username,password,name,email,gender,photo,doe,role  from profiles_tbl";
			//compiling the query
			PreparedStatement pstmt=connection.prepareStatement(sql);
			//Firing the query and getting data into result set
			ResultSet resultSet=pstmt.executeQuery();
			List<ProfileDTO> profileDTOs=new ArrayList<>();
			while(resultSet.next()) {
				 //Go to home.jsp
				//Below line will forward your request from servlet to the JSP
				int aid=resultSet.getInt(1);
				String username=resultSet.getString(2);
				String password=resultSet.getString(2);
				String name=resultSet.getString(4);
				String email=resultSet.getString(5);
				String gender=resultSet.getString(6);
				String photo=resultSet.getString(7);
				Timestamp doe=resultSet.getTimestamp(8);
				String role=resultSet.getString(9);
				ProfileDTO profileDTO=new ProfileDTO();
				profileDTO.setAid(aid);
				profileDTO.setName(name);
				profileDTO.setEmail(email);
				profileDTO.setGender(gender);
				profileDTO.setImage(photo);
				profileDTO.setUsername(username);
				profileDTO.setPassword(password);
				profileDTO.setDoe(doe);
				profileDTO.setRole(role);
				profileDTOs.add(profileDTO);
			}
			
			//Hey I need this data on home.jsp
			req.setAttribute("profileDTOs", profileDTOs);
			req.getRequestDispatcher("profiles.jsp").forward(req, resp);
			
		}catch (Exception e) {
				e.printStackTrace();
		}
	}
}
