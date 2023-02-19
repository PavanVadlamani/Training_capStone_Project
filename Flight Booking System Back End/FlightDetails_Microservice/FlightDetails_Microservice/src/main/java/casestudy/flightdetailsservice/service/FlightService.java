package casestudy.flightdetailsservice.service;

import java.time.LocalDate;
import java.util.List;

import casestudy.flightdetailsservice.exceptions.FlightNotFoundException;
import casestudy.flightdetailsservice.models.Flight;

public interface FlightService {

	public Flight addFlight(Flight flight);

	public List<Flight> getFlights();

	public Flight getFlight(String flightNumber) throws FlightNotFoundException;

	public Flight updateFlight(String id, Flight f) throws FlightNotFoundException;

	public Flight deleteFlight(String id) throws FlightNotFoundException;

	/*
	 * public List<Flight> searchFlights(String source, String destination,
	 * LocalDate date);
	 */

	public List<Flight> getFlightsBySourceAndDestination(String source, String destination, LocalDate arrivalDate);

}
