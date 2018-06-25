package com.fsoft.thangdt3.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fsoft.thangdt3.dao.StudentDAO;
import com.fsoft.thangdt3.model.Student;
import com.fsoft.thangdt3.utils.ViewUtil;

/**
 * Servlet implementation class Admin
 */
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO studentDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminController() {
		super();
		studentDAO = new StudentDAO();
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private Map<String, String> checkCheckbox(HttpServletRequest request) throws ServletException, IOException {
		Map<String, String> checkBoxMessage = new HashMap<>();
		String[] selectedIndexes = request.getParameterValues("selectedIndexes");
		if (selectedIndexes == null) {
			checkBoxMessage.put("checkCheckbox", "Choose at least one row to perform");
		}
		return checkBoxMessage;
	}

	private boolean checkStringLength(String input) {
		return input.length() <= 45;
	}

	private Map<String, String> checkAddNameAndMark(HttpServletRequest request) {
		Map<String, String> message = new HashMap<>();
		try {
			String name = request.getParameter("studentName");
			String markString = request.getParameter("studentMark");
			if (name == null || name.trim().isEmpty()) {
				message.put("name", "Name must not be empty");
			} else if (!checkStringLength(name))
				message.put("name", "Maximum name length is 45");

			if (markString == null || markString.trim().isEmpty()) {
				message.put("mark", "Mark must not be empty");
			} else {
				float mark = Float.parseFloat(markString);
				if (mark > 10 || mark < 0) {
					message.put("mark", "Mark must be from 0 to 10");
				}
			}
		} catch (NumberFormatException e) {
			message.put("mark", "Mark must be a number");
		}
		return message;
	}

	private Map<String, String> checkUpdateNameAndMark(HttpServletRequest request) {
		Map<String, String> message = new HashMap<>();
		try {
			String[] selectedIndexes = request.getParameterValues("selectedIndexes");
			for (String selectedIndex : selectedIndexes) {
				String name = request.getParameter("studentName" + selectedIndex);
				String markString = request.getParameter("studentMark" + selectedIndex);
				if (name == null || name.trim().isEmpty()) {
					message.put("name", "Name must not be empty");
				} else if (!checkStringLength(name))
					message.put("name", "Maximum name length is 45");

				if (markString == null || markString.trim().isEmpty()) {
					message.put("mark", "Mark must not be empty");
				} else {
					float mark = Float.parseFloat(markString);
					if (mark > 10 || mark < 0) {
						message.put("mark", "Mark must be from 0 to 10");
					}
				}
			}
		} catch (NumberFormatException e) {
			message.put("mark", "Mark must be a number");
		}
		return message;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String queryString = request.getQueryString();
		if (queryString != null) {
			queryString = "?" + queryString;
		} else {
			queryString = "";
		}
		request.getSession().setAttribute("queryString", queryString);
		response.sendRedirect("admin/home" + queryString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Student> studentDisplayList = (List<Student>) request.getSession().getAttribute("studentDisplayList");

		if (request.getParameter("delete") != null) {
			Map<String, String> checkboxMessage = checkCheckbox(request);
			if (!checkboxMessage.isEmpty()) {
				request.setAttribute("checkboxMessage", checkboxMessage);
				request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
				return;
			} else {
				String[] selectedIndexes = request.getParameterValues("selectedIndexes");
				List<Student> studentDeleteList = new ArrayList<>();
				for (String selectedIndex : selectedIndexes) {
					Student student = studentDisplayList.get(Integer.parseInt(selectedIndex));
					studentDeleteList.add(student);
				}
				studentDAO.deleteStudent(studentDeleteList);
			}
		} else if (request.getParameter("add") != null) {
			response.sendRedirect("admin/add");
			return;
		} else if (request.getParameter("applyAdd") != null) {
			Map<String, String> addNameAndMarkMessage = checkAddNameAndMark(request);
			if (!addNameAndMarkMessage.isEmpty()) {
				request.setAttribute("addNameAndMarkMessage", addNameAndMarkMessage);
				request.getRequestDispatcher("admin/add").forward(request, response);
			} else {
				Student student = new Student();
				student.setName(request.getParameter("studentName"));
				student.setMark(Float.parseFloat(request.getParameter("studentMark")));
				studentDAO.createStudent(student);

				int maxPageIndex = studentDAO.countAllStudents() / ViewUtil.RECORDS_PER_PAGE + 1;
				response.sendRedirect("admin?page=" + maxPageIndex);
			}
			return;
		} else if (request.getParameter("update") != null) {
			Map<String, String> checkboxMessage = checkCheckbox(request);
			if (!checkboxMessage.isEmpty()) {
				request.setAttribute("checkboxMessage", checkboxMessage);
				request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
			} else {
				String[] selectedIndexes = request.getParameterValues("selectedIndexes");
				request.getSession().setAttribute("selectedIndexes", selectedIndexes);
				response.sendRedirect("admin/edit" + (String) request.getSession().getAttribute("queryString"));
			}
			return;
		} else if (request.getParameter("applyUpdate") != null) {
			Map<String, String> updateNameAndMarkMessage = checkUpdateNameAndMark(request);
			if (!updateNameAndMarkMessage.isEmpty()) {
				request.setAttribute("updateNameAndMarkMessage", updateNameAndMarkMessage);
				request.getRequestDispatcher("admin/edit").forward(request, response);
				return;
			} else {
				String[] selectedIndexes = request.getParameterValues("selectedIndexes");
				List<Student> studentUpdateList = new ArrayList<>();
				for (String selectedIndex : selectedIndexes) {
					Student student = studentDisplayList.get(Integer.parseInt(selectedIndex));
					student.setName(request.getParameter("studentName" + selectedIndex));
					student.setMark(Float.parseFloat(request.getParameter("studentMark" + selectedIndex)));
					studentUpdateList.add(student);
				}
				studentDAO.updateStudent(studentUpdateList);
			}
		}
		response.sendRedirect("admin/home" + (String) request.getSession().getAttribute("queryString"));
	}
}
