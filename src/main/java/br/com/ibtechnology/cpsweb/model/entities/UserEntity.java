package br.com.ibtechnology.cpsweb.model.entities;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.ibtechnology.cpsweb.model.entities.BaseEntities;

@Entity
@Table(name="Users")
@AttributeOverride(name = "id", column = @Column(name = "ID"))
public class UserEntity extends BaseEntities<Long> {

	private static final long serialVersionUID = 5244643112563217776L;

	@OneToOne
	@JoinColumn(name="personID", nullable=false)
	private PersonEntity person;
	
	private String username;
	
	private String password;
	
	@ManyToOne
	@JoinColumn(name="groupID", nullable=false)
	private GroupEntity group;
	
	private boolean active;
	
	private boolean changePassNextLogon;
	
	private boolean canChangePass;
	
	private Date expire_date;
	
	private Date last_update;
	
	private String role;

	public UserEntity() {
	}

	public UserEntity(Long id, PersonEntity person, String username, String password, GroupEntity group, boolean active,
			boolean changePassNextLogon, boolean canChangePass, Date expire_date, Date last_update, String role) {
		super();
		this.person = person;
		this.username = username;
		this.password = password;
		this.group = group;
		this.active = active;
		this.changePassNextLogon = changePassNextLogon;
		this.canChangePass = canChangePass;
		this.expire_date = expire_date;
		this.last_update = last_update;
		this.role = role;
	}

	public PersonEntity getPerson() {
		return person;
	}

	public void setPerson(PersonEntity person) {
		this.person = person;
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

	public GroupEntity getGroup() {
		return group;
	}

	public void setGroup(GroupEntity group) {
		this.group = group;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isChangePassNextLogon() {
		return changePassNextLogon;
	}

	public void setChangePassNextLogon(boolean changePassNextLogon) {
		this.changePassNextLogon = changePassNextLogon;
	}

	public boolean isCanChangePass() {
		return canChangePass;
	}

	public void setCanChangePass(boolean canChangePass) {
		this.canChangePass = canChangePass;
	}

	public Date getExpire_date() {
		return expire_date;
	}

	public void setExpire_date(Date expire_date) {
		this.expire_date = expire_date;
	}

	public Date getLast_update() {
		return last_update;
	}

	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


}
