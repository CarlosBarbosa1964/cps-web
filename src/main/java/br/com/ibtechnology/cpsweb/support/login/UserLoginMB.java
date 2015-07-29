package br.com.ibtechnology.cpsweb.support.login;

import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import br.com.ibtechnology.cpsweb.model.entities.UserEntity;
import br.com.ibtechnology.cpsweb.model.repositories.IUserRepository;
import br.com.ibtechnology.cpsweb.support.util.MyUtil;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Named("userLoginMB")
@ManagedBean
public class UserLoginMB implements Serializable {

	private static final long serialVersionUID = -6349314345774744750L;

	// private static final Logger logger = Logger.getLogger(UserLoginMB.class);

	@Inject
	private IUserRepository userRepository;

	@Inject
	private MyUtil myUtil;

	@Inject
	private FacesContext context;

	private UserEntity user;

	private String username;

	private String password;

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login(ActionEvent event) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage message = null;
		boolean loggedIn = false;
		String route = "";
		String _password = "";
		if (username != null && password != null) {
			_password = myUtil.CriptoPass(password);
			System.out.println("-------------------------------");
			System.out.println(username);
			System.out.println(_password);
			System.out.println("-------------------------------");
		}
		this.user = new UserEntity();
		this.user = userRepository.findByUsernameAndPassword(this.username, _password);
		if (this.user!=null) {
			loggedIn = true;
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentUser",
					this.user.getUsername());
			route = myUtil.getBasePathLogin() + "pages/main.cps";
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, this.getResourceProperty("labels", "login_wellcome"), this.user.getPerson().getName());
		} else {
			loggedIn = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, this.getResourceProperty("labels", "login_fail"),
					this.getResourceProperty("labels", "login_invalid"));
		}

		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("loggedIn", loggedIn);
		context.addCallbackParam("route", route);
	}

	private String getResourceProperty(String resource, String label) {
		context = FacesContext.getCurrentInstance();
		Application application = this.context.getApplication();
		ResourceBundle bundle = application.getResourceBundle(this.context, resource);

		return bundle.getString(label);
	}
}
