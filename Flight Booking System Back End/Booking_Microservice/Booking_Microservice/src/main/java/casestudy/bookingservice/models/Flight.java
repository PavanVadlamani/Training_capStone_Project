package casestudy.bookingservice.models;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "FlightModel")
public class Flight {
	@Id
	@Indexed(unique = true)
	@NotNull(message = "Please provide a flightnumber")
	private String flightNumber;
	@NotNull(message = "Please provide source")
	private String source;
	@NotNull(message = "Please provide departdate")
	private LocalDate departDate;
	@NotNull
	private LocalTime departTime;
	@NotNull
	private String destination;
	@NotNull
	private LocalDate arrivalDate;
	@NotNull
	private LocalTime arrivalTime;
	@NotNull
	private String travelTime;
	@NotNull
	private double fare;
	@NotNull
	private int seatsRemaining;
	public Flight() {
		super();
	}
	public Flight(@NotNull(message = "Please provide a flightnumber") String flightNumber,
			@NotNull(message = "Please provide source") String source,
			@NotNull(message = "Please provide departdate") LocalDate departDate, LocalTime departTime,
			String destination, LocalDate arrivalDate, LocalTime arrivalTime, String travelTime, double fare,
			int seatsRemaining) {
		super();
		this.flightNumber = flightNumber;
		this.source = source;
		this.departDate = departDate;
		this.departTime = departTime;
		this.destination = destination;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		this.travelTime = travelTime;
		this.fare = fare;
		this.seatsRemaining = seatsRemaining;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public LocalDate getDepartDate() {
		return departDate;
	}
	public void setDepartDate(LocalDate departDate) {
		this.departDate = departDate;
	}
	public LocalTime getDepartTime() {
		return departTime;
	}
	public void setDepartTime(LocalTime departTime) {
		this.departTime = departTime;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public LocalDate getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public LocalTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getTravelTime() {
		return travelTime;
	}
	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public int getSeatsRemaining() {
		return seatsRemaining;
	}
	public void setSeatsRemaining(int seatsRemaining) {
		this.seatsRemaining = seatsRemaining;
	}
	@Override
	public String toString() {
		return "Flight [flightNumber=" + flightNumber + ", source=" + source + ", departDate=" + departDate
				+ ", departTime=" + departTime + ", destination=" + destination + ", arrivalDate=" + arrivalDate
				+ ", arrivalTime=" + arrivalTime + ", travelTime=" + travelTime + ", fare=" + fare + ", seatsRemaining="
				+ seatsRemaining + "]";
	}   
	
	
}