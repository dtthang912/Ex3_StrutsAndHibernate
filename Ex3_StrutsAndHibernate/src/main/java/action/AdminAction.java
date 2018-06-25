package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import dao.StudentDAO;
import form.StudentForm;
import model.Student;
import model.StudentParameter;
import util.BeanUtilities;
import util.ViewUtil;

public class AdminAction extends DispatchAction {

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StudentDAO studentDAO = new StudentDAO();
		StudentForm studentForm = (StudentForm) form;
		List<Student> studentList = new ArrayList<Student>();
		List<Integer> pageIndexes = new ArrayList<Integer>();

		int maxPageIndex = 1;
		int currentPage = Integer.parseInt(studentForm.getsPage());

		try {
			// Refine current page index
			maxPageIndex = studentDAO.countStudentsByName(studentForm.getsName()) / ViewUtil.RECORDS_PER_PAGE + 1;

			if (currentPage > maxPageIndex) {
				currentPage = maxPageIndex;
			} else if (currentPage <= 0) {
				currentPage = 1;
			}

			// Calculate number of pages that will be displayed
			pageIndexes = ViewUtil.getPageIndexes(currentPage, maxPageIndex);
			studentList = studentDAO.getStudentsByName(studentForm.getsName(),
					(currentPage - 1) * ViewUtil.RECORDS_PER_PAGE, ViewUtil.RECORDS_PER_PAGE);

			request.setAttribute("pageIndexes", pageIndexes);
			request.setAttribute("studentDisplayList", studentList);
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		return mapping.findForward("list");
	}

	public ActionForward showAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("add");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StudentForm studentForm = (StudentForm) form;
		if (isCancelled(request)) {
			return new ActionForward(
					"process.do?method=list&sName=" + studentForm.getsName() + "&sPage=" + studentForm.getsPage(),
					true);
		}
		StudentDAO studentDAO = new StudentDAO();
		Student student = new Student();
		BeanUtilities.copyProperties(student, studentForm);

		studentDAO.createStudent(student);

		// Return to the last page index
		int maxPageIndex = studentDAO.countAllStudents() / ViewUtil.RECORDS_PER_PAGE + 1;
		return new ActionForward("process.do?method=list&sPage=" + maxPageIndex, true);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Integer> selectIds = new ArrayList<Integer>();
		StudentForm studentForm = (StudentForm) form;
		StudentDAO studentDAO = new StudentDAO();

		// Change format of id params to integer
		String[] selectedIdParams = request.getParameterValues("id");
		for (String selectedIdParam : selectedIdParams) {
			selectIds.add(Integer.parseInt(selectedIdParam));
		}

		// Get selected students from db and delete
		List<Student> studentList = studentDAO.getStudentsByIds(selectIds);
		studentDAO.deleteStudent(studentList);
		return new ActionForward(
				"process.do?method=list&sName=" + studentForm.getsName() + "&sPage=" + studentForm.getsPage(), true);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Integer> selectIds = new ArrayList<Integer>();
		StudentForm studentForm = (StudentForm) form;
		StudentDAO studentDAO = new StudentDAO();

		// Change format of id params to integer
		String[] selectedIdParams = request.getParameterValues("id");
		for (String selectedIdParam : selectedIdParams) {
			selectIds.add(Integer.parseInt(selectedIdParam));
		}

		// Get selected students from db and transfer to StudentParameter type,
		// set it to student form
		List<Student> selectedStudents = studentDAO.getStudentsByIds(selectIds);
		List<StudentParameter> selectedStudentParams = new ArrayList<StudentParameter>();
		for (Student student : selectedStudents) {
			StudentParameter studentParameter = new StudentParameter();
			BeanUtilities.copyProperties(studentParameter, student);
			selectedStudentParams.add(studentParameter);
		}
		studentForm.setStudentList(selectedStudentParams);

		return mapping.findForward("edit");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StudentForm studentForm = (StudentForm) form;
		if (!isCancelled(request)) {
			List<Student> studentList = new ArrayList<Student>();
			StudentDAO studentDAO = new StudentDAO();

			// Transfer StudentParameter to Student type
			for (StudentParameter studentParameter : studentForm.getStudentList()) {
				Student student = new Student();
				BeanUtilities.copyProperties(student, studentParameter);
				studentList.add(student);
			}
			studentDAO.updateStudent(studentList);
		}
		return new ActionForward(
				"process.do?method=list&sName=" + studentForm.getsName() + "&sPage=" + studentForm.getsPage(), true);
	}
}
