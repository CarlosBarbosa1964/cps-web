package br.com.ibtechnology.cpsweb.business;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;

import br.com.ibtechnology.cpsweb.model.business.IUserController;
import br.com.ibtechnology.cpsweb.model.entities.UserEntity;
import br.com.ibtechnology.cpsweb.util.BaseBeans;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Named(value = "userController")
public class UserController extends BaseBeans implements IUserController {

	private static final long serialVersionUID = -334157061586036475L;

	@Override
	public UserEntity getAuthenticatedUser() {
		UserEntity user= new UserEntity();
		user = (UserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}



}
