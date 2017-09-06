package org.aitesting.microservices.tripmanagement.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="trip")
public class Trip {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idtrip")
	private Integer idtrip;
    @ManyToOne
    @JoinColumn(name = "idorigin_city")
	private City originCity;
    @ManyToOne
    @JoinColumn(name = "iddestination_city")
	private City destinationCity;
    @ManyToOne
    @JoinColumn(name = "idpassenger")
	private Passenger passenger;
    @ManyToOne
    @JoinColumn(name = "iddriver")
	private Driver driver;
    @ManyToOne
    @JoinColumn(name = "idcar")
	private Car car;
	private Double lastLocationLat;
	private Double lastLocationLon;
	private Double originLocationLat;
	private Double originLocationLon;
	private Double destinationLocationLat;
	private Double destinationLocationLon;
	private String originAddress;
	private String originZipcode;
	private String destinationAddress;
	private String destinationZipcode;

    @OneToMany
    private Set<HasTripState> hasTripState;
	
	public Trip() {
	}

	public Integer getIdtrip() {
		return idtrip;
	}

	public void setIdtrip(Integer idtrip) {
		this.idtrip = idtrip;
	}

	public City getOriginCity() {
		return originCity;
	}

	public void setOriginCity(City originCity) {
		this.originCity = originCity;
	}

	public City getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(City destinationCity) {
		this.destinationCity = destinationCity;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
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

	public String getOriginZipcode() {
		return originZipcode;
	}

	public void setOriginZipcode(String originZipcode) {
		this.originZipcode = originZipcode;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDestinationZipcode() {
		return destinationZipcode;
	}

	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}

	@Override
	public String toString() {
		return driver.toString() + " - " + passenger.toString() + " - " + car.toString();
	}
	
	
	
}
