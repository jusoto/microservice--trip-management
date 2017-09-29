package org.aitesting.microservices.tripmanagement.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="car")
public class Car {
	
	@Id
	private Integer idcar;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idstate", referencedColumnName = "idstate")
	private State state;
	private Integer iddriver;
	private Integer idcarModel;
	private String plate;
	private String imageUri;
	private Integer year;
	
	public Car() {
		
	}

	public Car(State state, Integer iddriver, Integer idcarModel, String plate, String imageUri, Integer year) {
		super();
		this.state = state;
		this.iddriver = iddriver;
		this.idcarModel = idcarModel;
		this.plate = plate;
		this.imageUri = imageUri;
		this.year = year;
	}

	public Integer getIdcar() {
		return idcar;
	}

	public void setIdcar(Integer idcar) {
		this.idcar = idcar;
	}


	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Integer getIddriver() {
		return iddriver;
	}

	public void setIddriver(Integer iddriver) {
		this.iddriver = iddriver;
	}

	public Integer getIdcarModel() {
		return idcarModel;
	}

	public void setIdcarModel(Integer idcarModel) {
		this.idcarModel = idcarModel;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idcar != null ? idcar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.idcar == null && other.idcar != null) || (this.idcar != null && !this.idcar.equals(other.idcar))) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return plate;
	}

}
