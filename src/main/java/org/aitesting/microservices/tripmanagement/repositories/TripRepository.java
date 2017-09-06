package org.aitesting.microservices.tripmanagement.repositories;

import org.aitesting.microservices.tripmanagement.models.Trip;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip, String> {
	
    Trip findByIdtrip(Integer id);
    
}
