package casestudy.flightdetailsservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import casestudy.flightdetailsservice.exceptions.FlightAlreadyExistsException;
import casestudy.flightdetailsservice.exceptions.FlightNotFoundException;
import casestudy.flightdetailsservice.models.Flight;
import casestudy.flightdetailsservice.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {
	@Autowired
	FlightRepository flightRepository;

	public List<Flight> getFlights() {
		return flightRepository.findAll();
	}

	public Flight addFlight(Flight flight) {

		Optional<Flight> existingFlight = flightRepository.findById(flight.getFlightNumber());
		if (!existingFlight.isPresent()) {

			return flightRepository.save(flight);

		} else {
			throw new FlightAlreadyExistsException(
					"Flight Already Exists for this id :: " + existingFlight.get().getFlightNumber());

		}

	}

	public Flight getFlight(String flightNumber) throws FlightNotFoundException {
		return flightRepository.findById(flightNumber)
				.orElseThrow(() -> new FlightNotFoundException("Flight not found for this id :: " + flightNumber));
	}

	public Flight updateFlight(String id, Flight f) throws FlightNotFoundException {
		Flight flight = flightRepository.findById(id)
				.orElseThrow(() -> new FlightNotFoundException("Flight not found for this id :: " + id));
		flight = f;
		flight.setFlightNumber(id);
		flightRepository.save(flight);
		return flight;
	}

	public Flight deleteFlight(String id) throws FlightNotFoundException {
		Flight flight = flightRepository.findById(id)
				.orElseThrow(() -> new FlightNotFoundException("Flight not found for this id :: " + id));
		flightRepository.delete(flight);
		return flight;
	}

	
	/*
	 * @Override public List<Flight> searchFlights(String source, String
	 * destination, LocalDate date) { return
	 * flightRepository.findBySourceDestinationArrivalDate(source,destination,date);
	 * 
	 * }
	 */
	 

	@Override
	public List<Flight> getFlightsBySourceAndDestination(String source, String destination, LocalDate arrivalDate) {
		List<Flight> flights =  flightRepository.findBySourceAndDestinationAndDepartDate(source, destination, arrivalDate);
		return flights;
	}

}
