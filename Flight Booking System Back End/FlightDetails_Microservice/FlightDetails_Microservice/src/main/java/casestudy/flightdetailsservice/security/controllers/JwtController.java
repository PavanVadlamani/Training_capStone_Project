package casestudy.flightdetailsservice.security.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import casestudy.flightdetailsservice.security.response.JwtResponse;
import casestudy.flightdetailsservice.security.services.JwtService;

@RestController
@RequestMapping("/jwt")
public class JwtController {
	
	@Autowired
	JwtService service;
	
	@PostMapping("/addtoken")
	public JwtResponse addToken(@RequestBody JwtResponse response)
	{
		return service.addToken(response);
		
	}
	
	@PutMapping("/update")
	public JwtResponse updatetoken(@RequestBody JwtResponse response)
	{
		return service.updatetoken(response);
		
	}
	
	@GetMapping("/jwttoken/id")
	public Optional<JwtResponse> getId(String Id) {
		
		return service.getId(Id);
		
	}
	
	@GetMapping("/jwttoken")
	public List<JwtResponse> getToken() {
		return service.getToken();
		
	}
	

}
