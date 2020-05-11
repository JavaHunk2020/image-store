package com.rab3.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rab3.dto.ProfileDTO;
import com.rab3.utils.AppDBConnection;

//Do not forgot to write forward slash here 
@WebServlet("/updateProfile")
public class UpdateProfileServlet extends HttpServlet {
	
	/**
	 * @req - it will hold request data from the client!!
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String aid=req.getParameter("aid");
		String username=req.getParameter("username");
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
			String sql="update profiles_tbl set username=? ,name =? ,email =? ,gender =? ,photo= ?  where aid =?";
			//compiling the query
			PreparedStatement pstmt=connection.prepareStatement(sql);
			//setting values inside the compiled query
			pstmt.setString(1,username);
			pstmt.setString(2,name);
			pstmt.setString(3,email);
			pstmt.setString(4,gender);
			pstmt.setString(5,image);
			pstmt.setInt(6,Integer.parseInt(aid));
			//Firing the query and getting data into result set
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		ProfileDTO profileDTO=new ProfileDTO();
		profileDTO.setAid(Integer.parseInt(aid));
		profileDTO.setName(name);
		profileDTO.setEmail(email);
		profileDTO.setGender(gender);
		profileDTO.setImage(image);
		profileDTO.setUsername(username);
		req.setAttribute("magic", profileDTO);
		req.setAttribute("msg", "Your profile is successfully updated!!");
		req.getRequestDispatcher("home.jsp").forward(req, resp);
	}

}
