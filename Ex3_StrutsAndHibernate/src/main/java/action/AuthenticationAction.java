package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import dao.UserDAO;
import form.LoginForm;
import model.User;

public class AuthenticationAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			UserDAO userDAO = new UserDAO();
			LoginForm loginForm = (LoginForm) form;
			ActionMessages errors = new ActionMessages();
			User user = userDAO.login(loginForm.getUsername(), loginForm.getPass());
			if (user != null){ 
				request.getSession().setMaxInactiveInterval(-1);
				request.getSession().setAttribute("user", user);
				return mapping.findForward("list");
			}
			else{ 
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("login.invalid"));
				super.saveErrors(request, errors);
				return mapping.findForward("login");
			}
	}
}
