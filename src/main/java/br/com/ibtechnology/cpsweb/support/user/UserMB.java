package br.com.ibtechnology.cpsweb.support.user;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import br.com.ibtechnology.cpsweb.model.entities.UserEntity;
import br.com.ibtechnology.cpsweb.model.repositories.IGroupRepository;
import br.com.ibtechnology.cpsweb.model.repositories.IUserRepository;
import br.com.ibtechnology.cpsweb.util.BaseBeans;

// ConfigurableBeanFactory.SCOPE_SINGLETON, ConfigurableBeanFactory.SCOPE_PROTOTYPE,
// WebApplicationContext.SCOPE_REQUEST, WebApplicationContext.SCOPE_SESSION
@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Named(value = "userMB")
public class UserMB extends BaseBeans {

	private static final long serialVersionUID = 201404221641L;

	private static final Logger logger = Logger.getLogger(UserMB.class);

	@Inject
	private IUserRepository userRepository;
	
	@Inject
	private IGroupRepository groupRepository;
	
	@Inject
	private FacesContext context;

	private List<UserEntity> users;

	private UserEntity selectedUser;

	private Long id;

	public UserMB() {
	}

	public void onLoad() {
		this.users = this.userRepository.findAll();
	}

	public List<UserEntity> getUsers() {
		return this.users;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void delete() {
		if (this.selectedUser != null) {
			if(!this.selectedUser.isProtected()){
			this.userRepository.delete(this.selectedUser.getId());
			}else
			{
				FacesMessage msg = new FacesMessage(this.getResourceProperty("labels", "user_cant_deleted"), this.selectedUser.getUsername());
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
	}

	public void selectUser(SelectEvent evt) {
		try {
			if (evt.getObject() != null) {
				this.selectedUser = (UserEntity) evt.getObject();
			} else {
				this.selectedUser = null;
			}
		} catch (Exception e) {
			this.selectedUser = null;

			logger.error(e.getMessage(), e);
		}
	}
	
	public void testClass(){
		if(this.selectedUser != null){
		System.out.println(this.selectedUser.getUsername());
		}else {
			System.out.println("Usuário Nulo");
			
		}
	}

	public void unselectUser() {
		this.selectedUser = null;
	}

	public UserEntity getSelectedUser() {
		return this.selectedUser;
	}

	public void setSelectedUser(UserEntity selectedUser) {
		this.selectedUser = selectedUser;
	}

	private String getResourceProperty(String resource, String label) {
		context = FacesContext.getCurrentInstance();
		Application application = this.context.getApplication();
		ResourceBundle bundle = application.getResourceBundle(this.context, resource);

		return bundle.getString(label);
	}
}
