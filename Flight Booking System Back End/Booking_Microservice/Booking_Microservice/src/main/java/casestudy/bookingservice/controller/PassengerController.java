package casestudy.bookingservice.controller;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import casestudy.bookingservice.models.Flight;
import casestudy.bookingservice.models.PassengerList;
import casestudy.bookingservice.service.PassengerService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/booking")
public class PassengerController {
	
	@Autowired
	private PassengerService passengerService;
	
	@PostMapping("/{flightId}/addpassengers")
	public PassengerList addpassenger(@PathVariable(value="flightId") String flightId, @RequestBody @Valid PassengerList passengerList,String token)
	{
		return passengerService.addPassenger(flightId,passengerList,token);
	}
	
	@GetMapping("/getpassengers/{username}")
	public List<PassengerList> getPassengerByUserName(@PathVariable(value="username") String username)
	{
		return passengerService.getPassengerByUserName(username);
	}
	
	@GetMapping("/getpassengers")
	public List<PassengerList> getPassenger()
	{
		return passengerService.getPassenger();
	}
	
	@DeleteMapping("/cancelticket/{flightId}/{bookingId}")
	public Map<String, PassengerList> cancelTicket(@PathVariable(value = "flightId") String flightId, @PathVariable(value = "bookingId") String bookingId,String token) {
		return passengerService.cancelTicket(bookingId,flightId,token);
	}
	
	@PutMapping("/cancelticket/{flightId}/{userId}/{passengerId}")
	public Map<String, PassengerList> cancelTicketByPassengerId(@PathVariable(value = "flightId") String flightId
			, @PathVariable(value = "userId") String userId,@PathVariable(value = "passengerId") long passengerId,String token) {
		return passengerService.cancelTicketbyPassengerId(userId,flightId,passengerId,token);
	}
	 
	@GetMapping("/allflight")
	public ResponseEntity<Flight[]> signin(@RequestHeader("Authorization") String token)
	{
		return passengerService.signin(token);
		
	}
	
}