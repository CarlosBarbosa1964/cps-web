package br.com.ibtechnology.cpsweb.support.group;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import br.com.ibtechnology.cpsweb.model.entities.GroupEntity;
import br.com.ibtechnology.cpsweb.model.repositories.IGroupRepository;
import br.com.ibtechnology.cpsweb.util.BaseBeans;


//ConfigurableBeanFactory.SCOPE_SINGLETON, ConfigurableBeanFactory.SCOPE_PROTOTYPE,
//WebApplicationContext.SCOPE_REQUEST, WebApplicationContext.SCOPE_SESSION
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@Named(value = "groupAddEditMB")
public class GroupAddEditMB extends BaseBeans {

	private static final long serialVersionUID = -5469091166134847386L;
	
	private static final Logger logger = Logger.getLogger(GroupAddEditMB.class);

	@Inject
	private IGroupRepository groupRepository;

	@Inject
	private GroupMB mbGroupBean;
	
	@Inject
	private FacesContext context;

	private GroupEntity group;
	
	private List<GroupEntity> groups;

	private String title;
	
	public GroupAddEditMB() {
		this.group = new GroupEntity();
	}

	public void onLoad() {
		this.groups = this.groupRepository.findAll();
	}
	
	public GroupEntity getGroup() {
		return group;
	}

	public void setGroup(GroupEntity group) {
		this.group = group;
	}

	public void add() {
		this.title = this.getResourceProperty("labels", "group_add");
	}

	public void update() {
		this.group = this.mbGroupBean.getSelectedGroup();
		this.title = this.getResourceProperty("labels", "group_update");
	}

	public void cancel() {
		this.mbGroupBean.unselectGroup();
	}

	public void save() {
		if (this.group != null) {
			this.group.setLast_update(new Date());
			if (this.group.getId() == null) {
				// Add
				this.groupRepository.save(this.group);
			} else {
				// Update
				this.groupRepository.save(this.group);
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

}
