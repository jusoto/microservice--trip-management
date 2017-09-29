package org.aitesting.microservices.tripmanagement.controllers;

import java.io.IOException;

import org.aitesting.microservices.tripmanagement.TripManagementApplication;
import org.aitesting.microservices.tripmanagement.amqp.CustomMessageDriver;
import org.aitesting.microservices.tripmanagement.models.Trip;
import org.aitesting.microservices.tripmanagement.repositories.TripRepository;
import org.aitesting.microservices.tripmanagement.util.Util;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

import com.google.maps.GaeRequestHandler;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;

@Controller
@RequestMapping("api/trips")
public class TripController {

	private final RabbitTemplate rabbitTemplate;

	@Autowired
	public TripController(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
	
    @Autowired
    private TripRepository repository;

    @GetMapping("/")
    public @ResponseBody Iterable<Trip> getTrips(){
    	// 1="Active", 0="Not Active"
        return repository.findAll();
    }

    @GetMapping("{id}")
    public @ResponseBody Trip getTrip(@PathVariable("id") Integer id){
        return repository.findByIdtrip(id);
    }

    @PostMapping("add")
    public @ResponseBody Trip addTrip(@RequestBody Trip trip) {
		GeoApiContext context = new GeoApiContext.Builder(new GaeRequestHandler.Builder())
				.apiKey("AIzaSyB4uaoaPtxAHijM9oFBKrgxAcQBeqXSlKE").build();
		
    	// TODO: Get Address from Origin Lat Lon
		String originAddress = "2250 N Commerce Pkwy, Weston, FL 33326 ";
		try {
			originAddress = Util.getReverseGeocoding(context, trip.getOriginLocationLat(), trip.getOriginLocationLon());
		} catch (ApiException | InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		trip.setOriginAddress(originAddress);
    	
    	// TODO: Get Lat Lon from Destination Address
		LatLng latlng = new LatLng(25.756606, -80.373957);
		try {
			latlng = Util.getGeocoding(context, trip.getDestinationAddress());
		} catch (ApiException | InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	trip.setDestinationLocationLat(latlng.lat);
    	trip.setDestinationLocationLon(latlng.lng);
    	
    	// TODO: Retrieve directions from Google API
        String origin = trip.getOriginLocationLat() + "," + trip.getOriginLocationLon();
        String destination = trip.getDestinationAddress();
        DirectionsResult directions = Util.getDirections(context, origin, destination);
        trip.setDirections(directions.toString());
    	
        Trip newTrip = repository.save(trip);
        
        // TODO: Notify all available drivers
//        RestTemplate restTemplate = new RestTemplate();
//
//    	System.out.println("**************************** Before requesting Drivers *****************************");
//		List<Driver> drivers = restTemplate.getForObject("http://localhost:8082/api/drivers/nearlocation?latitude="+trip.getOriginLocationLat()+"&longitude="+trip.getOriginLocationLon(), List.class);
//    	System.out.println("**************************** After requesting Drivers *****************************");       
                
        return newTrip;
    }

    public void sendMessageDriver(final CustomMessageDriver customMessage) {
        rabbitTemplate.convertAndSend(TripManagementApplication.EXCHANGE_NAME, TripManagementApplication.TRIP_ROUTING_KEY, customMessage);
    }

    @PutMapping("update/{id}")
    public @ResponseBody Trip updateTrip(@PathVariable("id") Integer id, @RequestBody Trip updatedTrip){
        Trip trip = repository.findByIdtrip(id);
        trip.setIdpassenger(updatedTrip.getIdpassenger() != null ? updatedTrip.getIdpassenger() : trip.getIdpassenger());
        trip.setIddriver(updatedTrip.getIddriver() != null ? updatedTrip.getIddriver() : trip.getIddriver());
        trip.setIdcar(updatedTrip.getIdcar() != null ? updatedTrip.getIdcar() : trip.getIdcar());
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
    
    @PutMapping("update/origin-location/{id}")
    public @ResponseBody Trip updateTripOriginLocation(@PathVariable("id") Integer id, @RequestParam Double lat, @RequestParam Double lon){
        Trip trip = repository.findByIdtrip(id);
        trip.setOriginLocationLat(lat != null ? lat : trip.getOriginLocationLat());
        trip.setOriginLocationLon(lon != null ? lon : trip.getOriginLocationLon());
        repository.save(trip);

        return trip;
    }
    
    @PutMapping("update/destination-location/{id}")
    public @ResponseBody Trip updateTripDestinationLocation(@PathVariable("id") Integer id, @RequestParam Double lat, @RequestParam Double lon){
        Trip trip = repository.findByIdtrip(id);
        trip.setDestinationLocationLat(lat != null ? lat : trip.getDestinationLocationLat());
        trip.setDestinationLocationLon(lon != null ? lon : trip.getDestinationLocationLon());
        repository.save(trip);

        return trip;
    }
    
    @PutMapping("update/last-location/{id}")
    public @ResponseBody Trip updateTripLastLocation(@PathVariable("id") Integer id, @RequestParam Double lat, @RequestParam Double lon){
        Trip trip = repository.findByIdtrip(id);
        trip.setLastLocationLat(lat != null ? lat : trip.getLastLocationLat());
        trip.setLastLocationLon(lon != null ? lon : trip.getLastLocationLon());
        repository.save(trip);

        return trip;
    }

    @GetMapping("delete/{id}")
    public @ResponseBody void deleteTrip(@PathVariable("id") String id){
        repository.delete(id);
    }
}
