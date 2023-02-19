package casestudy.flightdetailsservice.security.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import casestudy.flightdetailsservice.security.response.JwtResponse;

public interface JwtSignInResponse extends MongoRepository<JwtResponse, String> {

	Optional<JwtResponse> findByusername(String username);

	Optional<JwtResponse> findByType(String type);


}
