package form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class LoginForm extends ActionForm{
	private String username;
	private String pass;
	
	public LoginForm() {
		
	}
	
	public String getUsername() {
		return username;
	}
	public String getPass() {
		return pass;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if (username == null || username.trim().isEmpty()) {
			errors.add("username",new ActionMessage("error.username.required"));
		}
		if (pass == null || pass.trim().isEmpty()) {
			errors.add("pass",new ActionMessage("error.pass.required"));
		}
		return errors;
	}
}
