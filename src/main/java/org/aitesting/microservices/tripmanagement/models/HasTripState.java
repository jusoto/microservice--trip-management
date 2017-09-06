package org.aitesting.microservices.tripmanagement.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="has_trip_state")
public class HasTripState {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idhas_trip_state")
	private Integer idhasTripState;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtrip")
	private Trip trip;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtrip_state")
	private TripState tripState;
	private Date creationDate;
	
	public HasTripState() {
		
	}

	public Integer getIdhasTripState() {
		return idhasTripState;
	}

	public void setIdhasTripState(Integer idhasTripState) {
		this.idhasTripState = idhasTripState;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public TripState getTripState() {
		return tripState;
	}

	public void setTripState(TripState tripState) {
		this.tripState = tripState;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return trip.toString() + " - " + tripState.toString() + " - " + creationDate.toString();
	}

}
