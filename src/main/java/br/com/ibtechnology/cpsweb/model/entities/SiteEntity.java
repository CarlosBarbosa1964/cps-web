package br.com.ibtechnology.cpsweb.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Sites")
@AttributeOverride(name = "id", column = @Column(name = "ID"))
public class SiteEntity extends BaseEntities<Long> {

	private static final long serialVersionUID = 8563896335074894087L;
	
	@Column(nullable=false)
	private String name;
	
	@Lob
	@Column(nullable=true)
	private String desc;
	
	@Column(nullable=true)
	private Address address;
	
	@OneToMany(mappedBy="site", cascade=CascadeType.ALL)
	private List<FloorEntity> floors;
	
	@Column(nullable=false)
	private Date last_update;

	public SiteEntity(){
		
	}
	

	public SiteEntity(String name, String desc, Address address, List<FloorEntity> floors, Date last_update) {
		super();
		this.name = name;
		this.desc = desc;
		this.address = address;
		this.floors = floors;
		this.last_update = last_update;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public List<FloorEntity> getFloors() {
		return floors;
	}


	public void setFloors(List<FloorEntity> floors) {
		this.floors = floors;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Date getLast_update() {
		return last_update;
	}

	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}


}
