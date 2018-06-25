package form;

import org.apache.struts.action.ActionForm;

public class LocaleForm extends ActionForm {
	private String language;

	public LocaleForm() {
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
