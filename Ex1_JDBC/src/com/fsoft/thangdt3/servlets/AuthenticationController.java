package com.fsoft.thangdt3.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fsoft.thangdt3.dao.UserDAO;
import com.fsoft.thangdt3.model.Role;
import com.fsoft.thangdt3.model.User;

/**
 * Servlet implementation class Authentication
 */
public class AuthenticationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthenticationController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("username");
		String pass = request.getParameter("pass");
		Map<String, String> loginMessage = new HashMap<>();

		if (userName == null || userName.trim().isEmpty()) {
			loginMessage.put("username", "Please enter username");
		}
		if (pass == null || pass.isEmpty()) {
			loginMessage.put("pass", "Please enter password");
		}

		if (loginMessage.isEmpty()) {
			UserDAO userDAO = new UserDAO();
			User user = userDAO.login(userName, pass);
			if (user != null) {
				if (user.getRole() == Role.NORMAL) {
					request.getSession().setAttribute("user", user);
					response.sendRedirect("index");
					return;
				} else {
					request.getSession().setAttribute("user", user);
					response.sendRedirect("admin");
					return;
				}

			}
			else{
				loginMessage.put("login", "Wrong username or password");
			}
		}
		request.setAttribute("loginMessage", loginMessage);
		doGet(request, response);
	}
}
