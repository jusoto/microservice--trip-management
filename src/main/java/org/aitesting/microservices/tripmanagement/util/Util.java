package org.aitesting.microservices.tripmanagement.util;

import java.io.IOException;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

public class Util {
	
	public static String getReverseGeocoding(GeoApiContext context, Double latitude, Double longitude) throws ApiException, InterruptedException, IOException {
		
		String address = "";
//		GeoApiContext context = new GeoApiContext.Builder(new GaeRequestHandler.Builder())
//				.apiKey("AIzaSyB4uaoaPtxAHijM9oFBKrgxAcQBeqXSlKE").build();
		
		 LatLng latlng = new LatLng(latitude, longitude);
	     GeocodingResult[] results = GeocodingApi.newRequest(context).latlng(latlng).await();
	     
	     if(results != null && results.length>0) {
	    	 address = results[0].formattedAddress;
	     }
	     
	     return address;
	      
	}
	
	public static LatLng getGeocoding(GeoApiContext context, String address) throws ApiException, InterruptedException, IOException {
		
		LatLng latlng = null;
	
		GeocodingResult[] results = GeocodingApi.geocode(context, address).await();

	     if(results != null && results.length>0) {
	    	 latlng = results[0].geometry.location;
	     }
	     
		return latlng;
	}
	
	public static DirectionsResult getDirections(GeoApiContext context, String origin, String destination) {
//		GeoApiContext context = new GeoApiContext.Builder(new GaeRequestHandler.Builder())
//				.apiKey("AIzaSyB4uaoaPtxAHijM9oFBKrgxAcQBeqXSlKE").build();

		DirectionsResult result = null;
		try {
			result = DirectionsApi.getDirections(context, origin, destination).await();
		} catch (ApiException | InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
