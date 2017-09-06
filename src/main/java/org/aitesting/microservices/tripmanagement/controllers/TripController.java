package org.aitesting.microservices.tripmanagement.controllers;

import org.aitesting.microservices.tripmanagement.models.Trip;
import org.aitesting.microservices.tripmanagement.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api")
public class TripController {
	
    @Autowired
    private TripRepository repository;

    @GetMapping("trips")
    public @ResponseBody Iterable<Trip> getTrips(){
    	// 1="Active", 0="Not Active"
        return repository.findAll();
    }

    @GetMapping("trips/{id}")
    public @ResponseBody Trip getTrip(@PathVariable("id") Integer id){
        return repository.findByIdtrip(id);
    }

    @PostMapping("trips/add")
    public @ResponseBody Trip addTrip(@RequestBody Trip trip){
        repository.save(trip);
        return trip;
    }

    @PutMapping("trips/update/{id}")
    public @ResponseBody Trip updateTrip(@PathVariable("id") Integer id, @RequestBody Trip updatedTrip){
        Trip trip = repository.findByIdtrip(id);
        trip.setPassenger(updatedTrip.getPassenger() != null ? updatedTrip.getPassenger() : trip.getPassenger());
        trip.setDriver(updatedTrip.getDriver() != null ? updatedTrip.getDriver() : trip.getDriver());
        trip.setCar(updatedTrip.getCar() != null ? updatedTrip.getCar() : trip.getCar());
        trip.setOriginCity(updatedTrip.getOriginCity() != null ? updatedTrip.getOriginCity() : trip.getOriginCity());
        trip.setDestinationCity(updatedTrip.getDestinationCity() != null ? updatedTrip.getDestinationCity() : trip.getDestinationCity());
        trip.setOriginAddress(updatedTrip.getOriginAddress() != null ? updatedTrip.getOriginAddress() : trip.getOriginAddress());
        trip.setDestinationAddress(updatedTrip.getDestinationAddress() != null ? updatedTrip.getDestinationAddress() : trip.getDestinationAddress());
        trip.setLastLocationLat(updatedTrip.getLastLocationLat() != null ? updatedTrip.getLastLocationLat() : updatedTrip.getLastLocationLat());
        trip.setLastLocationLon(updatedTrip.getLastLocationLon() != null ? updatedTrip.getLastLocationLon() : updatedTrip.getLastLocationLon());
        trip.setOriginLocationLat(updatedTrip.getOriginLocationLat() != null ? updatedTrip.getOriginLocationLat() : updatedTrip.getOriginLocationLat());
        trip.setOriginLocationLon(updatedTrip.getOriginLocationLon() != null ? updatedTrip.getOriginLocationLon() : updatedTrip.getOriginLocationLon());
        trip.setDestinationLocationLat(updatedTrip.getDestinationLocationLat() != null ? updatedTrip.getDestinationLocationLat() : updatedTrip.getDestinationLocationLat());
        trip.setDestinationLocationLon(updatedTrip.getDestinationLocationLon() != null ? updatedTrip.getDestinationLocationLon() : updatedTrip.getDestinationLocationLon());
        repository.save(trip);
        return trip;
    }
    
    @PutMapping("trips/update/origin-location/{id}")
    public @ResponseBody Trip updateTripOriginLocation(@PathVariable("id") Integer id, @RequestParam Double lat, @RequestParam Double lon){
        Trip trip = repository.findByIdtrip(id);
        trip.setOriginLocationLat(lat != null ? lat : trip.getOriginLocationLat());
        trip.setOriginLocationLon(lon != null ? lon : trip.getOriginLocationLon());
        repository.save(trip);

        return trip;
    }
    
    @PutMapping("trips/update/destination-location/{id}")
    public @ResponseBody Trip updateTripDestinationLocation(@PathVariable("id") Integer id, @RequestParam Double lat, @RequestParam Double lon){
        Trip trip = repository.findByIdtrip(id);
        trip.setDestinationLocationLat(lat != null ? lat : trip.getDestinationLocationLat());
        trip.setDestinationLocationLon(lon != null ? lon : trip.getDestinationLocationLon());
        repository.save(trip);

        return trip;
    }
    
    @PutMapping("trips/update/last-location/{id}")
    public @ResponseBody Trip updateTripLastLocation(@PathVariable("id") Integer id, @RequestParam Double lat, @RequestParam Double lon){
        Trip trip = repository.findByIdtrip(id);
        trip.setLastLocationLat(lat != null ? lat : trip.getLastLocationLat());
        trip.setLastLocationLon(lon != null ? lon : trip.getLastLocationLon());
        repository.save(trip);

        return trip;
    }

    @GetMapping("trips/delete/{id}")
    public @ResponseBody void deleteTrip(@PathVariable("id") String id){
        repository.delete(id);
    }
}
