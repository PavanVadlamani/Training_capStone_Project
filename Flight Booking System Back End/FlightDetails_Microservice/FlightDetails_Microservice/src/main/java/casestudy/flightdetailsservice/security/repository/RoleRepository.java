package casestudy.flightdetailsservice.security.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import casestudy.flightdetailsservice.security.models.ERole;
import casestudy.flightdetailsservice.security.models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

	
  Optional<Role> findByName(ERole name);
}
