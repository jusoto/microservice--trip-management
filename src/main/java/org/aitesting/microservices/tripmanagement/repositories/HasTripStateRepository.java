package org.aitesting.microservices.tripmanagement.repositories;

import org.aitesting.microservices.tripmanagement.models.HasTripState;
import org.springframework.data.repository.CrudRepository;

public interface HasTripStateRepository extends CrudRepository<HasTripState, String> {
	
    HasTripState findByIdhasTripState(Integer id);
    
    HasTripState findByTrip(Integer id);
    
    HasTripState findLastByTrip(Integer id);
    
}
