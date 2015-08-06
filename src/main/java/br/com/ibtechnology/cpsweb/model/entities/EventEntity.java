package br.com.ibtechnology.cpsweb.model.entities;

import java.sql.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Events")
@AttributeOverride(name = "id", column = @Column(name = "ID") )
public class EventEntity extends BaseEntities<Long> {

	private static final long serialVersionUID = 4436301468137891968L;

	@Column(nullable=false)
	private String name;

	@Column(nullable=false)
	private String source;

	@Column(nullable=false)
	private String levelLog;

	@Column(nullable=false)
	private String category;
	
	@Column(nullable=false)
	private String keyword;
	
	@Column(nullable=false)
	private String textLog;
	
	private String ipaddress;
	
	@ManyToOne
	@JoinColumn(name = "userID", nullable = true)
	private UserEntity user;

	@Column(nullable=false)
	private Date date_time;

	public EventEntity() {
	}

	public EventEntity(String name, String source, String levelLog, String category, String keyword, String textLog,
			String ipaddress, UserEntity user, Date date_time) {
		super();
		this.name = name;
		this.source = source;
		this.levelLog = levelLog;
		this.category = category;
		this.keyword = keyword;
		this.textLog = textLog;
		this.ipaddress = ipaddress;
		this.user = user;
		this.date_time = date_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getLevelLog() {
		return levelLog;
	}

	public void setLevelLog(String levelLog) {
		this.levelLog = levelLog;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getTextLog() {
		return textLog;
	}

	public void setTextLog(String textLog) {
		this.textLog = textLog;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Date getDate_time() {
		return date_time;
	}

	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}


}
