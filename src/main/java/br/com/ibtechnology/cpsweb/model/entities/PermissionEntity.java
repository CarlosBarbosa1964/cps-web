package br.com.ibtechnology.cpsweb.model.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.ibtechnology.cpsweb.model.entities.BaseEntities;

@Entity
@Table(name = "Permissions")
@AttributeOverride(name = "id", column = @Column(name = "ID"))
public class PermissionEntity extends BaseEntities<Long> {

	private static final long serialVersionUID = 2425970982248417764L;
	
	private Long groupID;
	
	private boolean system_access;

	public PermissionEntity(){
		
	}

	public PermissionEntity(Long groupID, boolean system_access) {
		super();
		this.groupID = groupID;
		this.system_access = system_access;
	}

	public Long getGroupID() {
		return groupID;
	}

	public void setGroupID(Long groupID) {
		this.groupID = groupID;
	}

	public boolean isSystem_access() {
		return system_access;
	}

	public void setSystem_access(boolean system_access) {
		this.system_access = system_access;
	}

}
