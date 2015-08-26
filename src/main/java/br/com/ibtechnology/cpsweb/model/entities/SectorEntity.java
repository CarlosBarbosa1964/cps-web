package br.com.ibtechnology.cpsweb.model.entities;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Sectors")
@AttributeOverride(name = "id", column = @Column(name = "ID"))
public class SectorEntity extends BaseEntities<Long> {

	private static final long serialVersionUID = -4868820899450072215L;

	@ManyToOne
	@JoinColumn(name="floorID", nullable=false)
	private FloorEntity floor;

	@Column(nullable=false)
	private String name;
	
	@Lob
	@Column(nullable=true, columnDefinition="TEXT")
	private String description;
	
	@Column(nullable=false)
	private int total_car_spaces;
	
	@Column(nullable=false)
	private int total_bike_spaces;
	
	@Column(nullable=false)
	private int car_space_occupied;
	
	@Column(nullable=false)
	private int bike_space_occupied;
	
	@Column(nullable=false)
	private int car_traffic;
	
	@Column(nullable=false)
	private int bike_traffic;

	@Column(nullable=false)
	private Date last_update;

	public SectorEntity() {
		
	}

	public SectorEntity(FloorEntity floor, String name, String description, int total_car_spaces, int total_bike_spaces,
			int car_space_occupied, int bike_space_occupied, int car_traffic, int bike_traffic, Date last_update) {
		super();
		this.floor = floor;
		this.name = name;
		this.description = description;
		this.total_car_spaces = total_car_spaces;
		this.total_bike_spaces = total_bike_spaces;
		this.car_space_occupied = car_space_occupied;
		this.bike_space_occupied = bike_space_occupied;
		this.car_traffic = car_traffic;
		this.bike_traffic = bike_traffic;
		this.last_update = last_update;
	}

	public FloorEntity getFloor() {
		return floor;
	}

	public void setFloor(FloorEntity floor) {
		this.floor = floor;
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

	public int getTotal_car_spaces() {
		return total_car_spaces;
	}

	public void setTotal_car_spaces(int total_car_spaces) {
		this.total_car_spaces = total_car_spaces;
	}

	public int getTotal_bike_spaces() {
		return total_bike_spaces;
	}

	public void setTotal_bike_spaces(int total_bike_spaces) {
		this.total_bike_spaces = total_bike_spaces;
	}

	public int getCar_space_occupied() {
		return car_space_occupied;
	}

	public void setCar_space_occupied(int car_space_occupied) {
		this.car_space_occupied = car_space_occupied;
	}

	public int getBike_space_occupied() {
		return bike_space_occupied;
	}

	public void setBike_space_occupied(int bike_space_occupied) {
		this.bike_space_occupied = bike_space_occupied;
	}

	public int getCar_traffic() {
		return car_traffic;
	}

	public void setCar_traffic(int car_traffic) {
		this.car_traffic = car_traffic;
	}

	public int getBike_traffic() {
		return bike_traffic;
	}

	public void setBike_traffic(int bike_traffic) {
		this.bike_traffic = bike_traffic;
	}

	public Date getLast_update() {
		return last_update;
	}

	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}
	
}
