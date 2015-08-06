package br.com.ibtechnology.cpsweb.support.user;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import br.com.ibtechnology.cpsweb.model.entities.GroupEntity;
import br.com.ibtechnology.cpsweb.model.entities.PersonEntity;
import br.com.ibtechnology.cpsweb.model.entities.UserEntity;
import br.com.ibtechnology.cpsweb.model.repositories.IGroupRepository;
import br.com.ibtechnology.cpsweb.model.repositories.IUserRepository;
import br.com.ibtechnology.cpsweb.util.BaseBeans;


//ConfigurableBeanFactory.SCOPE_SINGLETON, ConfigurableBeanFactory.SCOPE_PROTOTYPE,
//WebApplicationContext.SCOPE_REQUEST, WebApplicationContext.SCOPE_SESSION
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@Named(value = "userAddEditMB")
public class UserAddEditMB extends BaseBeans {

	private static final long serialVersionUID = 201311132355L;

	private static final Logger logger = Logger.getLogger(UserAddEditMB.class);

	@Inject
	private IGroupRepository groupRepository;

	@Inject
	private IUserRepository userRepository;

//	@Inject
//	private UserMB mbUserBean;
	
	@Inject
	private FacesContext context;

	private UserEntity user;

	private String title;
	
	private List<GroupEntity> groups;
	
	private List<SelectItem> selectOneGroup;

	public UserAddEditMB() {
		this.user = new UserEntity();
		this.user.setPerson(new PersonEntity());
		this.user.setGroup(new GroupEntity());
	}

	public void onLoad() {
		this.groups = this.groupRepository.findAll();
	}
	
	public List<SelectItem> getSelectOneGroup() {
		this.selectOneGroup = new ArrayList<SelectItem>();
		this.groups = this.groupRepository.findAll();
		
		for (GroupEntity gr : groups) {
			SelectItem selectItem = new SelectItem(gr.getId(), gr.getName());
			this.selectOneGroup.add(selectItem);
		}
		
		return selectOneGroup;
	}

	public UserEntity getUser() {
		return this.user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public List<GroupEntity> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupEntity> groups) {
		this.groups = groups;
	}

	public void add() {
		this.title = this.getResourceProperty("labels", "user_add");
	}

	public void update() {
//		this.user = this.mbUserBean.getSelectedUser();
		this.title = this.getResourceProperty("labels", "user_update");
	}

	public void cancel() {
//		this.mbUserBean.unselectUser();
	}

	public void save() {
		if (this.user != null) {
			if (this.user.getId() == null) {
				// Add
				this.userRepository.save(this.user);
			} else {
				// Update
				this.userRepository.save(this.user);
			}
		}
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String getResourceProperty(String resource, String label) {
		Application application = this.context.getApplication();
		ResourceBundle bundle = application.getResourceBundle(this.context, resource);

		return bundle.getString(label);
	}

	public void onGroupChange() {
		try {
			if(this.user !=null) {
				FacesMessage msg = new FacesMessage("Grupo Selecionado", this.user.getGroup().getId().toString());
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
