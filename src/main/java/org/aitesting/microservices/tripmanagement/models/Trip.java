package org.aitesting.microservices.tripmanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="trip")
public class Trip {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idtrip")
	private Integer idtrip;
	private Integer idcityOrigin;
	private Integer idcityDestination;
	private Integer idpassenger;
	private Integer iddriver;
	private Integer idcar;
	private Double lastLocationLat;
	private Double lastLocationLon;
	private Double originLocationLat;
	private Double originLocationLon;
	private Double destinationLocationLat;
	private Double destinationLocationLon;
	private String originAddress;
	private String destinationAddress;
	private Integer active; // 1 = active, 0 = inactive
	
	public Trip() {
	}

	public Integer getIdtrip() {
		return idtrip;
	}

	public void setIdtrip(Integer idtrip) {
		this.idtrip = idtrip;
	}

	public Integer getIdcityOrigin() {
		return idcityOrigin;
	}

	public void setIdcityOrigin(Integer idcityOrigin) {
		this.idcityOrigin = idcityOrigin;
	}

	public Integer getIdcityDestination() {
		return idcityDestination;
	}

	public void setIdcityDestination(Integer idcityDestination) {
		this.idcityDestination = idcityDestination;
	}

	public Integer getIdpassenger() {
		return idpassenger;
	}

	public void setIdpassenger(Integer idpassenger) {
		this.idpassenger = idpassenger;
	}

	public Integer getIddriver() {
		return iddriver;
	}

	public void setIddriver(Integer iddriver) {
		this.iddriver = iddriver;
	}

	public Integer getIdcar() {
		return idcar;
	}

	public void setIdcar(Integer idcar) {
		this.idcar = idcar;
	}

	public Double getLastLocationLat() {
		return lastLocationLat;
	}

	public void setLastLocationLat(Double lastLocationLat) {
		this.lastLocationLat = lastLocationLat;
	}

	public Double getLastLocationLon() {
		return lastLocationLon;
	}

	public void setLastLocationLon(Double lastLocationLon) {
		this.lastLocationLon = lastLocationLon;
	}

	public Double getOriginLocationLat() {
		return originLocationLat;
	}

	public void setOriginLocationLat(Double originLocationLat) {
		this.originLocationLat = originLocationLat;
	}

	public Double getOriginLocationLon() {
		return originLocationLon;
	}

	public void setOriginLocationLon(Double originLocationLon) {
		this.originLocationLon = originLocationLon;
	}

	public Double getDestinationLocationLat() {
		return destinationLocationLat;
	}

	public void setDestinationLocationLat(Double destinationLocationLat) {
		this.destinationLocationLat = destinationLocationLat;
	}

	public Double getDestinationLocationLon() {
		return destinationLocationLon;
	}

	public void setDestinationLocationLon(Double destinationLocationLon) {
		this.destinationLocationLon = destinationLocationLon;
	}

	public String getOriginAddress() {
		return originAddress;
	}

	public void setOriginAddress(String originAddress) {
		this.originAddress = originAddress;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}
	
}
