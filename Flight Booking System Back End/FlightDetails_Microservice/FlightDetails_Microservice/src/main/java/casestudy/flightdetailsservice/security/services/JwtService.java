package casestudy.flightdetailsservice.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import casestudy.flightdetailsservice.security.repository.JwtSignInResponse;
import casestudy.flightdetailsservice.security.response.JwtResponse;

@Service
public class JwtService {
	
	@Autowired
	JwtSignInResponse repo;
	
	
	public JwtResponse addToken(JwtResponse response) {
		
		return repo.save(response);
	}

	public JwtResponse updatetoken(JwtResponse response) {
		
		return repo.save(response);
	}
	
	public Optional<JwtResponse> getId(String id) {
		return repo.findById(id);
	}

	public List<JwtResponse> getToken() {
		
		return repo.findAll();
	}

	
	

}
