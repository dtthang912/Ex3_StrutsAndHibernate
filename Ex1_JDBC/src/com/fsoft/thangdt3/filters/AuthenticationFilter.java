package com.fsoft.thangdt3.filters;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

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

import com.fsoft.thangdt3.model.Role;
import com.fsoft.thangdt3.model.User;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

	/**
	 * Default constructor.
	 */
	private Map<String, List<Role>> permissionList;

	public AuthenticationFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		boolean isLoggedIn = (session != null) && (session.getAttribute("user") != null);
		boolean isLogInRequest = req.getRequestURI().equals(req.getContextPath() + "/login");

		System.out.println(req.getRequestURI());
		if (!isLoggedIn && isLogInRequest) {
			chain.doFilter(request, response);
		} else if (isLoggedIn && !isLogInRequest) {
			List<Role> roleList = permissionList.get(req.getRequestURI());
			Role userRole = ((User) session.getAttribute("user")).getRole();
			if (req.getRequestURI().contains("/Ex1_JDBC/js/") || 
					req.getRequestURI().contains("/Ex1_JDBC/css/") ||
					roleList.contains(userRole)) {
				chain.doFilter(request, response);
			} else {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
			}
		} else if (isLoggedIn && isLogInRequest) {
			if (((User) session.getAttribute("user")).getRole() == Role.ADMIN) {
				res.sendRedirect("/Ex1_JDBC/admin");
			} else {
				res.sendRedirect("/Ex1_JDBC/index");
			}
		} else {
			res.sendRedirect("/Ex1_JDBC/login");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		permissionList = new HashMap<>();
		InputStream inp = null;
		Properties properties = new Properties();
		try {
			inp = fConfig.getServletContext().getResourceAsStream("/WEB-INF/permission.properties");
			properties.load(inp);
			for (Object key : properties.keySet()) {
				List<Role> roleList = new ArrayList<Role>();
				StringTokenizer stringTokenizer = new StringTokenizer(properties.getProperty((String) key), ",");
				while (stringTokenizer.hasMoreElements())
					roleList.add(Role.valueOf(stringTokenizer.nextToken()));
				permissionList.put((String) key, roleList);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inp != null) {
				try {
					inp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
