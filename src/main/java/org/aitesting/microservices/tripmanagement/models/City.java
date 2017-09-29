package org.aitesting.microservices.tripmanagement.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="city")
public class City {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idcity")
	private Integer idcity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idstate", referencedColumnName = "idstate")
	private State state;
	private String name;

    @OneToMany(mappedBy = "originCity")
    private Set<Trip> tripsOrigin;
    
    @OneToMany(mappedBy = "destinationCity")
    private Set<Trip> tripsDestination;
	
	public City() {
		
	}

	public Integer getIdcity() {
		return idcity;
	}

	public void setIdcity(Integer idcity) {
		this.idcity = idcity;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
    public Set<Trip> getTripsOrigin() {
		return tripsOrigin;
	}

	@JsonIgnore
	public Set<Trip> getTripsDestination() {
		return tripsDestination;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idcity != null ? idcity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof City)) {
            return false;
        }
        City other = (City) object;
        if ((this.idcity == null && other.idcity != null) || (this.idcity != null && !this.idcity.equals(other.idcity))) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return name;
	}
	
}
