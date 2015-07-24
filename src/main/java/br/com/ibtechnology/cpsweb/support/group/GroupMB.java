package br.com.ibtechnology.cpsweb.support.group;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import br.com.ibtechnology.cpsweb.model.entities.GroupEntity;
import br.com.ibtechnology.cpsweb.model.repositories.IGroupRepository;
import br.com.ibtechnology.cpsweb.util.BaseBeans;

// ConfigurableBeanFactory.SCOPE_SINGLETON, ConfigurableBeanFactory.SCOPE_PROTOTYPE,
// WebApplicationContext.SCOPE_REQUEST, WebApplicationContext.SCOPE_SESSION
@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Named(value = "groupMB")
public class GroupMB extends BaseBeans {

	private static final long serialVersionUID = 2234051143473990830L;

	private static final Logger logger = Logger.getLogger(GroupMB.class);

	@Inject
	private IGroupRepository groupRepository;
	
	private List<GroupEntity> groups;

	private GroupEntity selectedGroup;

	private Long id;

	public GroupMB() {
	}

	public void onLoad() {
		this.groups = this.groupRepository.findAll();
	}

	public List<GroupEntity> getGroups() {
		return this.groups;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void delete() {
		if (this.selectedGroup != null) {
			this.groupRepository.delete(this.selectedGroup.getId());
		}
	}

	public void selectGroup(SelectEvent evt) {
		try {
			if (evt.getObject() != null) {
				this.selectedGroup = (GroupEntity) evt.getObject();
				FacesMessage msg = new FacesMessage("Grupo Selecionado", selectedGroup.getName());
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				this.selectedGroup = null;
			}
		} catch (Exception e) {
			this.selectedGroup = null;

			logger.error(e.getMessage(), e);
		}
	}

	public void unselectGroup() {
		this.selectedGroup = null;
	}

	public GroupEntity getSelectedGroup() {
		return this.selectedGroup;
	}

	public void setSelectedGroup(GroupEntity selectedGroup) {
		this.selectedGroup = selectedGroup;
	}

}
