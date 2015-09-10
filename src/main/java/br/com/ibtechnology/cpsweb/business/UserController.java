package br.com.ibtechnology.cpsweb.business;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;

import br.com.ibtechnology.cpsweb.beans.UserSession;
import br.com.ibtechnology.cpsweb.model.business.IUserController;
import br.com.ibtechnology.cpsweb.model.entities.UserEntity;
import br.com.ibtechnology.cpsweb.util.BaseBeans;

@Named(value = "userController")
@SessionScoped
public class UserController extends BaseBeans implements IUserController {

	private static final long serialVersionUID = -334157061586036475L;

	@Autowired
	private UserSession session;

	@Override
	public UserEntity getAuthenticatedUser() {
		// FacesContext context = FacesContext.getCurrentInstance();
		// HttpSession session = (HttpSession)
		// context.getExternalContext().getSession(false);
		UserEntity user = new UserEntity();
		user = session.getUser();
		return user;
	}

}
