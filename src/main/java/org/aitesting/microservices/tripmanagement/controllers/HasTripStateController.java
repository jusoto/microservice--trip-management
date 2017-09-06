package org.aitesting.microservices.tripmanagement.controllers;

import org.aitesting.microservices.tripmanagement.models.HasTripState;
import org.aitesting.microservices.tripmanagement.repositories.HasTripStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api")
public class HasTripStateController {
	
    @Autowired
    private HasTripStateRepository repository;

    @GetMapping("has_trip_state")
    public @ResponseBody Iterable<HasTripState> getHasTripStates(){
    	// 1="Active", 0="Not Active"
        return  repository.findAll();
    }

    @GetMapping("has_trip_state/{id}")
    public @ResponseBody HasTripState getHasTripState(@PathVariable("id") Integer id){
        return repository.findByTrip(id);
    }

    @GetMapping("has_trip_state/last/{id}")
    public @ResponseBody HasTripState getLastHasTripState(@PathVariable("id") Integer id){
        return repository.findLastByTrip(id);
    }

    @PostMapping("has_trip_state/add")
    public @ResponseBody HasTripState addHasTripState(@RequestBody HasTripState obj){
        repository.save(obj);
        return obj;
    }
}
