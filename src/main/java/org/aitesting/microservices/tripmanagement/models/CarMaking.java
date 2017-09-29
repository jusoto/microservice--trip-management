package org.aitesting.microservices.tripmanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="car_making")
public class CarMaking {
	
	@Id
    @Column(name="idcar_making")
	private Integer idcarMaking;
	private String name;
	
	public CarMaking() {
		
	}

	public Integer getIdcarMaking() {
		return idcarMaking;
	}

	public void setIdcarMaking(Integer idcarMaking) {
		this.idcarMaking = idcarMaking;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
