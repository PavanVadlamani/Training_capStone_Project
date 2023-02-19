package casestudy.bookingservice.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import casestudy.bookingservice.exceptions.UserNotFoundException;
import casestudy.bookingservice.models.BookingFlight;
import casestudy.bookingservice.models.Flight;
import casestudy.bookingservice.models.JwtResponse;
import casestudy.bookingservice.models.Passenger;
import casestudy.bookingservice.models.PassengerList;
import casestudy.bookingservice.repository.PassengerRepo;

@Service
public class PassengerService {
	@Autowired
	private PassengerRepo passengerRepo;

	@Autowired
	public RestTemplate rest;

	public PassengerList addPassenger(String Id, PassengerList passengerList, String token) {
		
		JwtResponse[] jwt = rest.getForObject("http://FlightDetailsMicroservice/jwt/jwttoken", JwtResponse[].class);
		token = "Bearer " + jwt[0].getAccessToken();
		HttpHeaders headersget = new HttpHeaders();
		headersget.set("Authorization", token);
		Flight flightobj = new Flight();
		HttpEntity<Flight> entity = new HttpEntity<Flight>(flightobj, headersget);
		
		ResponseEntity<Flight> flight = rest.exchange("http://FlightDetailsMicroservice/flights/getFlightById/" + Id,
				HttpMethod.GET, entity, Flight.class);
		
		BookingFlight bookingFlight = new BookingFlight(flight.getBody().getFlightNumber(),
				flight.getBody().getSource(), flight.getBody().getDestination(), flight.getBody().getArrivalDate(),
				flight.getBody().getDepartDate(), flight.getBody().getArrivalTime(), flight.getBody().getDepartTime());
		passengerList.setBookingFlight(bookingFlight);
		int noOfPassengers = passengerList.getPassengerlist().size();

		ResponseEntity<Flight> updatedFlight = rest.exchange(
				"http://FlightDetailsMicroservice/flights/getFlightById/" + Id, HttpMethod.GET, entity, Flight.class);
		updatedFlight.getBody().setSeatsRemaining(updatedFlight.getBody().getSeatsRemaining() - noOfPassengers);

		HttpHeaders headersput = new HttpHeaders();
		headersput.set("Authorization", token);
		headersput.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Flight> entity1 = new HttpEntity<Flight>(updatedFlight.getBody(), headersput);
		rest.exchange("http://FlightDetailsMicroservice/flights/updateFlight/" + Id, HttpMethod.PUT, entity1,
				Flight.class);
		return passengerRepo.save(passengerList);
	}

	public List<PassengerList> getPassenger() {
		return passengerRepo.findAll();
	}

	public Map<String, PassengerList> cancelTicket(String bookingId, String Id,String token) {
		Optional<PassengerList> passenger = passengerRepo.findById(bookingId);
		if (!passenger.isPresent()) {

			throw new UserNotFoundException("BookingId not found with Id" + bookingId);
		} else {
			int noOfPassengers = passenger.get().getPassengerlist().size();
			
			JwtResponse[] jwt = rest.getForObject("http://FlightDetailsMicroservice/jwt/jwttoken", JwtResponse[].class);
			token = "Bearer " + jwt[0].getAccessToken();
			HttpHeaders headersget = new HttpHeaders();
			headersget.set("Authorization", token);
			Flight flightobj = new Flight();
			HttpEntity<Flight> entity = new HttpEntity<Flight>(flightobj, headersget);
			
			ResponseEntity<Flight> updatedFlight = rest.exchange("http://FlightDetailsMicroservice/flights/getFlightById/" + Id,
					HttpMethod.GET, entity, Flight.class);
			updatedFlight.getBody().setSeatsRemaining(updatedFlight.getBody().getSeatsRemaining() + noOfPassengers);
			
			HttpHeaders headersput = new HttpHeaders();
			headersput.set("Authorization", token);
			headersput.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<Flight> entity1 = new HttpEntity<Flight>(updatedFlight.getBody(), headersput);
			
			rest.exchange("http://FlightDetailsMicroservice/flights/updateFlight/" + Id, HttpMethod.PUT, entity1,
					Flight.class);
			passengerRepo.deleteById(bookingId);
		}
		Map<String, PassengerList> response = new HashMap<>();
		response.put("Deleted", passenger.get());
		return response;
	}

	public Map<String, PassengerList> cancelTicketbyPassengerId(String userId, String Id, long passengerId,String token) {
		Optional<PassengerList> passenger = passengerRepo.findById(userId);
		if (!passenger.isPresent()) {

			throw new UserNotFoundException(userId);
		} else {
			List<Passenger> passengers = passenger.get().getPassengerlist();
			Iterator itr = passengers.iterator();
			while (itr.hasNext()) {
				Passenger passengerobject = (Passenger) itr.next();
				long passengerId1 = passengerobject.getId();
				if (passengerId == passengerId1) {
					itr.remove();
				}
			}
			int noOfPassengers = passengers.size();
			
			JwtResponse[] jwt = rest.getForObject("http://FlightDetailsMicroservice/jwt/jwttoken", JwtResponse[].class);
			token = "Bearer " + jwt[0].getAccessToken();
			HttpHeaders headersget = new HttpHeaders();
			headersget.set("Authorization", token);
			Flight flightobj = new Flight();
			HttpEntity<Flight> entity = new HttpEntity<Flight>(flightobj, headersget);
			
			ResponseEntity<Flight> updatedFlight = rest.exchange("http://FlightDetailsMicroservice/flights/getFlightById/" + Id,
					HttpMethod.GET, entity, Flight.class);
			
			updatedFlight.getBody().setSeatsRemaining(updatedFlight.getBody().getSeatsRemaining() + noOfPassengers);
			

			HttpHeaders headersput = new HttpHeaders();
			headersput.set("Authorization", token);
			headersput.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<Flight> entity1 = new HttpEntity<Flight>(updatedFlight.getBody(), headersput);
			
			rest.exchange("http://FlightDetailsMicroservice/flights/updateFlight/" + Id, HttpMethod.PUT, entity1,
					Flight.class);
			
			passenger.get().setPassengerlist(passengers);
			passengerRepo.save(passenger.get());
		}
		Map<String, PassengerList> response = new HashMap<>();
		response.put("Deleted", passenger.get());
		return response;
	}

	public ResponseEntity<Flight[]> signin(String token) {

		JwtResponse[] jwt = rest.getForObject("http://FlightDetailsMicroservice/jwt/jwttoken", JwtResponse[].class);
		token = "Bearer " + jwt[0].getAccessToken();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		Flight flight = new Flight();
		HttpEntity<Flight> entity = new HttpEntity<Flight>(flight, headers);
		return rest.exchange("http://FlightDetailsMicroservice/flights/getFlights", HttpMethod.GET, entity,
				Flight[].class);
	}

	public List<PassengerList> getPassengerByUserName(String username) {
		
		return passengerRepo.findByUsername(username);
	}

}