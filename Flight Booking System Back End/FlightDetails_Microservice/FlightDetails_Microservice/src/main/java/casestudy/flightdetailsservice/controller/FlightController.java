package casestudy.flightdetailsservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import casestudy.flightdetailsservice.exceptions.FlightNotFoundException;
import casestudy.flightdetailsservice.logger.GlobalLogger;
import casestudy.flightdetailsservice.models.Flight;
import casestudy.flightdetailsservice.service.FlightService;

/// this is rest controller class where all the operations that can be done on managing flights as the end points 
/// are declared here
/// the actual implementation and business logic of this controller class can be found in FlightServiceImpl class
/// that can be found in the package called casestudy.flightdetailsservice.service
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/flights")
public class FlightController {
	
	///Autowiring this field in order to use the required implementations of corresponding end points
	@Autowired
	FlightService FlightService;

	// logs all the levels(Trace,error,warn,Info) that are configured for this class in to  file called applog.log 
	/// located at /FlightDetails_Microservice/applog.log
	private Logger logger = GlobalLogger.getLogger(FlightController.class);
	
	/// gets all the flights that are available.
	///Accessible end point //localhost:8080/flights/getFlights
	@GetMapping("/getFlights")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public List<Flight> getFlights() {
		logger.trace("GetFlights method Traced");
		logger.info("GetFlights method Accessed");
		return FlightService.getFlights();
	}

	/// Admin can add Flights using the end point 
	/// Note: check out the preferred requestbody while adding flights.
	/// The model of the request body is located at pakage casestudy.flightdetailsservice.models filename:Flight.java
	///Accessible end point //localhost:8080/flights/getFlights
	@PostMapping("/addFlights")
	@PreAuthorize("hasRole('ADMIN')")
	public Flight addFlight(@Valid @RequestBody Flight flight) {
		logger.trace("AddFlights method Traced");
		logger.info("AddFlights method Accessed");
		return FlightService.addFlight(flight);
	}
	
	
	@GetMapping("/getFlightById/{flightNumber}")
	@PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
	public ResponseEntity<Flight> getFlightById(@PathVariable(value = "flightNumber") String flightNumber)
			throws FlightNotFoundException {
		logger.trace("GetFlightsById method Traced");
		logger.info("GetFlightsById method Accessed");
		Flight flight = FlightService.getFlight(flightNumber);
		return ResponseEntity.ok().body(flight);
	}

	@PutMapping("/updateFlight/{id}")
	@PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
	public ResponseEntity<Flight> updateFlightById(@PathVariable(value = "id") String flightId,
			@RequestBody Flight flight) throws FlightNotFoundException {
		logger.trace("updateFlightById method Traced");
		logger.info("updateFlightById method Accessed");
		Flight updatedFlight = FlightService.updateFlight(flightId, flight);
		return ResponseEntity.ok().body(updatedFlight);
	}

	@DeleteMapping("/deleteFlights/{id}")
	@PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
	public Map<String, Boolean> deleteFlight(@PathVariable(value = "id") String flightId)
			throws FlightNotFoundException {
		logger.trace("deleteFlight method Traced");
		logger.info("deleteFlight method Accessed");
		FlightService.deleteFlight(flightId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
