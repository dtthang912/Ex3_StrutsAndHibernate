package action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.LocaleForm;

public class LocaleAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		LocaleForm localeForm = (LocaleForm) form;
//		if (localeForm.getLanguage().equals("vi")) {
//			request.getSession().setAttribute(Globals.LOCALE_KEY, new Locale("vi_VN"));
//		} else {
//			request.getSession().setAttribute(Globals.LOCALE_KEY, Locale.ENGLISH);
//		}
//		return mapping.findForward("list");
		
		LocaleForm localeForm = (LocaleForm) form;
		request.getSession().setAttribute(Globals.LOCALE_KEY, new Locale(localeForm.getLanguage()));
		return mapping.findForward("list");
	}
}
