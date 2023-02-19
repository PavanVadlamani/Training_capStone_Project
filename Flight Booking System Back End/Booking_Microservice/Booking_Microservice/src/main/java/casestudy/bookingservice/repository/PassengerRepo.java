package casestudy.bookingservice.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import casestudy.bookingservice.models.PassengerList;


public interface PassengerRepo extends MongoRepository<PassengerList, String> {

	List<PassengerList> findByUsername(String username);

}