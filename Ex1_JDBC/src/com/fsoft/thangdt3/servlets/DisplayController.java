package com.fsoft.thangdt3.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fsoft.thangdt3.dao.StudentDAO;
import com.fsoft.thangdt3.model.Student;
import com.fsoft.thangdt3.utils.ViewUtil;

/**
 * Servlet implementation class DisplayController
 */
public class DisplayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO studentDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DisplayController() {
		super();
		studentDAO = new StudentDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private boolean checkStringLength(String input) {
		return input.length() <= 45;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Student> studentList = new ArrayList<>();
		List<Integer> pageIndexes = new ArrayList<>();
		String searchName = request.getParameter("searchName");
		String pageString = request.getParameter("page");
		int maxPageIndex = 0;
		int currentPage = 1;
		try {
			if (searchName == null)
				searchName = "";

			if (!searchName.trim().isEmpty() && checkStringLength(searchName)) {
				maxPageIndex = studentDAO.countStudentsByName(searchName) / ViewUtil.RECORDS_PER_PAGE + 1;
			} else {
				maxPageIndex = studentDAO.countAllStudents() / ViewUtil.RECORDS_PER_PAGE + 1;
			}

			if (pageString != null && pageString.length() < 10 && Integer.parseInt(pageString) <= maxPageIndex) {
				currentPage = Integer.parseInt(pageString);
			} else if (pageString != null) {
				throw new NumberFormatException();
			}

			pageIndexes = ViewUtil.getPageIndexes(currentPage, maxPageIndex);
			studentList = studentDAO.getStudentsByName(searchName, (currentPage - 1) * ViewUtil.RECORDS_PER_PAGE,
					ViewUtil.RECORDS_PER_PAGE);

			request.getSession().setAttribute("pageIndexes", pageIndexes);
			request.getSession().setAttribute("studentDisplayList", studentList);

			if (request.getRequestURI().contains("admin")) {
				request.getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
			}
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
