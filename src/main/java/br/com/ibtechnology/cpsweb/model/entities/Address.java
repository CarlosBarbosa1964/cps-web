package br.com.ibtechnology.cpsweb.model.entities;

public class Address {
	
	private String street;
	private String district;
	private String city;
	private String state;
	private String zipcode;
	
	public Address(){
		
	}
	
	
	
	public Address(String street, String district, String city, String state, String zipcode) {
		this.street = street;
		this.district = district;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}


	//Getters and Setters
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

}
