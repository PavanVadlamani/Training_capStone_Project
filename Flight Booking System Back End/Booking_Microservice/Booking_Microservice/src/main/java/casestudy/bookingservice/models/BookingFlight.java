package casestudy.bookingservice.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingFlight {
	String id;
	String source;
	String destination;
	LocalDate dateOfJourney;
	LocalDate departuredate;
	LocalTime arrivalTime;
	LocalTime departureTime;
	
	public BookingFlight() {
		super();
	}

	public BookingFlight(String id, String source, String destination, LocalDate dateOfJourney, LocalDate departuredate,
			LocalTime arrivalTime, LocalTime departureTime) {
		super();
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.dateOfJourney = dateOfJourney;
		this.departuredate = departuredate;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDate getDateOfJourney() {
		return dateOfJourney;
	}

	public void setDateOfJourney(LocalDate dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}

	public LocalDate getDeparturedate() {
		return departuredate;
	}

	public void setDeparturedate(LocalDate departuredate) {
		this.departuredate = departuredate;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}
	
}
