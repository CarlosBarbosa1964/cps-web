package br.com.ibtechnology.cpsweb.model.entities;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.ibtechnology.cpsweb.model.entities.BaseEntities;

@Entity
@Table(name = "Persons")
@AttributeOverride(name = "id", column = @Column(name = "ID"))
public class PersonEntity extends BaseEntities<Long> {
		
	private static final long serialVersionUID = 7624262457868325283L;

	private String name;
	
	private String doc;
	
	//private Address address;
	private String street;
	private String district;
	private String city;
	private String state;
	private String zipcode;
	private Date last_update;

	public PersonEntity (){
		
	}

	public PersonEntity(String name, String doc, String street, String district, String city, String state,
			String zipcode, Date last_update) {
		super();
		this.name = name;
		this.doc = doc;
		this.street = street;
		this.district = district;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.last_update = last_update;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
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

	public Date getLast_update() {
		return last_update;
	}

	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}
		
}
