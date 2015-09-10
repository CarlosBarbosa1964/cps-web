package br.com.ibtechnology.cpsweb.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.ibtechnology.cpsweb.util.LogTraceListener;

@Entity
@Table(name="Sites")
@AttributeOverride(name = "id", column = @Column(name = "ID"))
public class SiteEntity extends BaseEntities<Long> {

	private static final long serialVersionUID = 8563896335074894087L;
	
	@Column(nullable=false)
	private String name;
	
	@Lob
	@Column(nullable=true, columnDefinition="TEXT")
	private String description;
	
	//private Address address;
	private String street;
	private String district;
	private String city;
	private String state;
	private String zipcode;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="site")
	private List<FloorEntity> floors;
	
	@Column(nullable=false)
	private Date last_update;

	public SiteEntity(){
		
	}
	

	public SiteEntity(String name, String description, String street, String district, String city, String state,
			String zipcode, List<FloorEntity> floors, Date last_update) {
		super();
		this.name = name;
		this.description = description;
		this.street = street;
		this.district = district;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.floors = floors;
		this.last_update = last_update;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getZipcode() {
		return zipcode;
	}


	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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

	public Date getLast_update() {
		return last_update;
	}

	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}


}
