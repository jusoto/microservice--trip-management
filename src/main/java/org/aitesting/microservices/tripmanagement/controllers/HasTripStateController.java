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
@RequestMapping("api/has_trip_state")
public class HasTripStateController {
	
    @Autowired
    private HasTripStateRepository repository;

    @GetMapping("/")
    public @ResponseBody Iterable<HasTripState> getHasTripStates(){
    	// 1="Active", 0="Not Active"
        return  repository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody HasTripState getHasTripState(@PathVariable("id") Integer id){
        return repository.findByTrip(id);
    }

    @GetMapping("last/{id}")
    public @ResponseBody HasTripState getLastHasTripState(@PathVariable("id") Integer id){
        return repository.findLastByTrip(id);
    }

    @PostMapping("add")
    public @ResponseBody HasTripState addHasTripState(@RequestBody HasTripState obj){
        repository.save(obj);
        return obj;
    }
}
