package org.aitesting.microservices.tripmanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="car_model")
public class CarModel {
	
	@Id
    @Column(name="idcar_model")
	private Integer idcarModel;
	private Integer idcarMaking;
	private String name;
	
	public CarModel() {
		
	}

	public Integer getIdcarModel() {
		return idcarModel;
	}

	public void setIdcarModel(Integer idcarModel) {
		this.idcarModel = idcarModel;
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
    public int hashCode() {
        int hash = 0;
        hash += (idcarModel != null ? idcarModel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarModel)) {
            return false;
        }
        CarModel other = (CarModel) object;
        if ((this.idcarModel == null && other.idcarModel != null) || (this.idcarModel != null && !this.idcarModel.equals(other.idcarModel))) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return name;
	}

}
