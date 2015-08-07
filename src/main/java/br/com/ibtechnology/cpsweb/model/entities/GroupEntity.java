package br.com.ibtechnology.cpsweb.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.ibtechnology.cpsweb.model.entities.BaseEntities;

@Entity
@Table(name = "Groups")
@AttributeOverride(name = "id", column = @Column(name = "ID"))
public class GroupEntity extends BaseEntities<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2461159396458524370L;
	
	private String name;
	
	private String description;
	
	private boolean active;
	
	private Date last_update;
	
	@OneToMany(mappedBy="group", cascade=CascadeType.ALL)
	private List<UserEntity> users;

	public GroupEntity () {
	}

	public GroupEntity(String name, String description, boolean active, Date last_update) {
		super();
		this.name = name;
		this.description = description;
		this.active = active;
		this.last_update = last_update;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getLast_update() {
		return last_update;
	}

	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

}
