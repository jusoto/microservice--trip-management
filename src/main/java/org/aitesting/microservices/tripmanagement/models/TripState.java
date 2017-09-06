package org.aitesting.microservices.tripmanagement.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="trip_state")
public class TripState {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idtrip_state")
	private Integer idtripState;
	private String name;
	private String description;

    @OneToMany
    private Set<HasTripState> hasTripStates;
	
	public TripState() {
		
	}

	public Integer getIdtripState() {
		return idtripState;
	}

	public void setIdtripState(Integer idtripState) {
		this.idtripState = idtripState;
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

	public Set<HasTripState> getHasTripStates() {
		return hasTripStates;
	}

	@Override
	public String toString() {
		return name;
	}

}
