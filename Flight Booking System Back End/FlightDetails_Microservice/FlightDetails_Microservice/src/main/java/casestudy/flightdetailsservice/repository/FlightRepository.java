package casestudy.flightdetailsservice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import casestudy.flightdetailsservice.models.Flight;

public interface FlightRepository extends MongoRepository<Flight,String> {

	
	/*
	 * @Query(value="{'source' : ?0, 'destination':?1,'arrivalDate':?2}")
	 * List<Flight> findBySourceDestinationArrivalDate(String source, String
	 * destination, LocalDate date);
	 */
	 

	List<Flight> findBySourceAndDestinationAndDepartDate(String source, String destination, LocalDate arrivalDate);
	
}
