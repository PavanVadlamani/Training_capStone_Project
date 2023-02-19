package casestudy.bookingservice.models;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="PassengerList")
public class PassengerList {
	@Id
	String bookingId;
	@NotNull(message="UserId cannot be null")
	String username;
	BookingFlight bookingFlight;
	@NotNull(message="properties of Passengers cannot be null")
	List<Passenger> passengerlist;
	
	
	public PassengerList() {
		super();
	}

	
	
	
	public PassengerList(String bookingId, @NotNull(message = "UserId cannot be null") String username,
			BookingFlight bookingFlight,
			@NotNull(message = "properties of Passengers cannot be null") List<Passenger> passengerlist) {
		super();
		this.bookingId = bookingId;
		this.username = username;
		this.bookingFlight = bookingFlight;
		this.passengerlist = passengerlist;
	}


	public String getBookingId() {
		return bookingId;
	}


	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public BookingFlight getBookingFlight() {
		return bookingFlight;
	}


	public void setBookingFlight(BookingFlight bookingFlight) {
		this.bookingFlight = bookingFlight;
	}


	public List<Passenger> getPassengerlist() {
		return passengerlist;
	}


	public void setPassengerlist(List<Passenger> passengerlist) {
		this.passengerlist = passengerlist;
	}

	
}