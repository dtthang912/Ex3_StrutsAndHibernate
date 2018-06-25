package form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import model.Student;
import model.StudentParameter;

public class StudentForm extends ActionForm {
	// Categories for searching
	private String sPage;
	private String sName;

	// Properties of a student
	private String id;
	private String name;
	private String mark;

	// List of students for editing
	private List<StudentParameter> studentList;

	// Method for processing
	private String method;

	public StudentForm() {

	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getMark() {
		return mark;
	}

	public String getsPage() {
		return sPage;
	}

	public String getsName() {
		return sName;
	}

	public String getMethod() {
		return method;
	}

	public void setsPage(String sPage) {
		this.sPage = sPage;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<StudentParameter> getStudentList() {
		return studentList;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public void setStudentList(List<StudentParameter> studentList) {
		this.studentList = studentList;
	}

	public StudentParameter getStudent(int index) {
		while (index >= studentList.size()) {
			studentList.add(new StudentParameter());
		}
		return studentList.get(index);
	}

	public void setStudent(int index, StudentParameter student) {
		studentList.set(index, student);
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		studentList = new ArrayList<StudentParameter>();
		sPage = "1";
		sName = "";
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if ("edit".equals(method) || "delete".equals(method)) {
			if (request.getParameter("id") == null) {
				errors.add("checkbox", new ActionMessage("errors.checkbox.required"));
			}
		} else if ("update".equals(method)) {
			try {
				for (StudentParameter student : studentList) {
					if (student.getName() == null || student.getName().trim().isEmpty()) {
						errors.add("name", new ActionMessage("error.name.required"));
					} else if (student.getName().length() > 45)
						errors.add("name", new ActionMessage("error.name.length"));

					if (student.getMark() == null || student.getMark().trim().isEmpty()) {
						errors.add("mark", new ActionMessage("error.mark.required"));
					} else {
						float mark = Float.parseFloat(student.getMark());
						if (mark > 10 || mark < 0) {
							errors.add("mark", new ActionMessage("error.mark.range"));
						}
					}
				}
			} catch (NumberFormatException e) {
				errors.add("mark", new ActionMessage("error.mark.format"));
			}
		} else if ("add".equals("method")) {
			try {
				if (name.trim().isEmpty()) {
					errors.add("name", new ActionMessage("error.name.required"));
				} else if (name.length() > 45)
					errors.add("name", new ActionMessage("error.name.length"));

				if (mark.trim().isEmpty()) {
					errors.add("mark", new ActionMessage("error.mark.required"));
				} else {
					float markInNumber = Float.parseFloat(mark);
					if (markInNumber > 10 || markInNumber < 0) {
						errors.add("mark", new ActionMessage("error.mark.range"));
					}
				}
			} catch (NumberFormatException e) {
				errors.add("mark", new ActionMessage("error.mark.format"));
			}
		} else /* if( method is list) */ {
			if (sName.length() > 45) {
				sName = "";
			}
			if (sPage.length() > 6) {
				sPage = "1";
			}
			sName = sName.trim();
			sPage = sPage.trim();
		}
		return errors;
	}

}
