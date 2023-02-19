package casestudy.flightdetailsservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import casestudy.flightdetailsservice.models.Flight;
import casestudy.flightdetailsservice.service.FlightService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/search")
public class FlightSearchcontroller {
	
	@Autowired
	FlightService flightService;
	
	@PostMapping("/flights")
	public List<Flight> display(@RequestBody Flight flight){
			System.out.println(flight.getSource());
			return flightService.getFlightsBySourceAndDestination(flight.getSource(), flight.getDestination(), flight.getArrivalDate());
	}

}
