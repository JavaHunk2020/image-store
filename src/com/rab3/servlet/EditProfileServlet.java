package com.rab3.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rab3.dto.ProfileDTO;
import com.rab3.utils.AppDBConnection;

//Do not forgot to write forward slash here 
@WebServlet("/editProfile")
public class EditProfileServlet extends HttpServlet {
	
	/**
	 * @req - it will hold request data from the client!!
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String aid=req.getParameter("aid");
		try {
			//Creating connection
			Connection connection=AppDBConnection.getConnection();	String sql="select  *  from profiles_tbl where aid =?";
			//compiling the query
			PreparedStatement pstmt=connection.prepareStatement(sql);
			//setting values inside the compiled query
			pstmt.setInt(1,Integer.parseInt(aid));
			//Firing the query and getting data into result set
			ResultSet resultSet=pstmt.executeQuery();
			if(resultSet.next()) {
				 //Go to home.jsp
				//Below line will forward your request from servlet to the JSP
				String username=resultSet.getString(2);
				String password=resultSet.getString(3);
				String name=resultSet.getString(4);
				String email=resultSet.getString(5);
				String gender=resultSet.getString(6);
				String photo=resultSet.getString(7);
				Timestamp doe=resultSet.getTimestamp(8);
				String role=resultSet.getString(9);
				ProfileDTO profileDTO=new ProfileDTO();
				profileDTO.setAid(Integer.parseInt(aid));
				profileDTO.setName(name);
				profileDTO.setEmail(email);
				profileDTO.setGender(gender);
				profileDTO.setImage(photo);
				profileDTO.setUsername(username);
				profileDTO.setPassword(password);
				profileDTO.setDoe(doe);
				profileDTO.setRole(role);
				//Hey I need this data on home.jsp
				req.setAttribute("profileDTO", profileDTO);
				req.getRequestDispatcher("editProfile.jsp").forward(req, resp);
			}
			
		}catch (Exception e) {
				e.printStackTrace();
		}
	}
}
