package br.com.ibtechnology.cpsweb.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class AuthorizationListener implements PhaseListener {

	private static final long serialVersionUID = 4090293841206601998L;

	@Override
	public void afterPhase(PhaseEvent arg0) {
//		FacesContext context = arg0.getFacesContext();
//		String currentPage = context.getViewRoot().getViewId();
//		boolean isLogginPage = currentPage.lastIndexOf("/login.xhtml") > -1 ? true : false;
//		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
//		Object currentUser = session.getAttribute("currentUser");
//		
//		if (!isLogginPage && currentUser == null) {
//			NavigationHandler nh = context.getApplication().getNavigationHandler();
//			nh.handleNavigation(context, null, "/login.cps");
//		}
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        if (session != null) {
            String mensagem = (String) session.getAttribute("msg");
 
            if (mensagem != null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null));
                session.setAttribute("msg", null);
            }
        }
  		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
