package com.rab3.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//You have intercept all the incoming request
@WebFilter("/*")
public class SessionCheckerFilter implements Filter{
	
	 private Set<String> allowedResources=new HashSet<>();
		
	 @Override
		public void init(FilterConfig filterConfig) throws ServletException {
		 	allowedResources.add("/login.jsp");
			allowedResources.add("/register.jsp");
			allowedResources.add("/register");
			allowedResources.add("/forgotPassword.jsp");
			allowedResources.add("/forgotPassword");
		 	allowedResources.add("/accessDenied.jsp");
		 	allowedResources.add("/auth");
		}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest=(HttpServletRequest)request;
		String resourceName=httpServletRequest.getServletPath();
		System.out.println("Incoming request from client is  "+resourceName+" at  "+LocalDateTime.now());
		//Go Ahead
		if(allowedResources.contains(resourceName) || resourceName.contains("/images")) {
			  chain.doFilter(request, response);
		}else {
			//Allowed anything else after login
			HttpSession  session=httpServletRequest.getSession(false);
			if(session!=null &&  session.getAttribute("profileDTO")!=null) {
				chain.doFilter(request, response);
			}else {
				HttpServletResponse httpServletResponse=(HttpServletResponse)response;
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/"+"accessDenied.jsp");
			}
		}
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}


}
