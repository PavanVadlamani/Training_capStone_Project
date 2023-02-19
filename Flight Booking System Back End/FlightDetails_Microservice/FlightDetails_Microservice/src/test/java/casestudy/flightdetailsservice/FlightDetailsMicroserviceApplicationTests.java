package casestudy.flightdetailsservice;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import casestudy.flightdetailsservice.controller.FlightController;
import casestudy.flightdetailsservice.exceptions.FlightAlreadyExistsException;
import casestudy.flightdetailsservice.exceptions.FlightNotFoundException;
import casestudy.flightdetailsservice.models.Flight;
import casestudy.flightdetailsservice.service.FlightService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
class FlightDetailsMicroserviceApplicationTests {

	@Mock
	FlightService flightservice;
	
	@InjectMocks
	FlightController flightController;
	
	@Test
	@Order(1)
	public void getAllFlights()
	{
		List<Flight> flightlist = new ArrayList<>();
		flightlist.add(new Flight("1234", "mum", LocalDate.parse("2022-02-04", DateTimeFormatter.ISO_DATE),
				LocalTime.parse("13:13:13"), "hyd", LocalDate.parse("1945-02-02"), LocalTime.parse("13:13:13"),
				"12 hours", 0, 0));
		when(flightservice.getFlights()).thenReturn(flightlist);
		List<Flight>  response = flightservice.getFlights();
		assertEquals(1,response.size());
	}
	
	@Test
	@Order(2)
	public void getFlightById() throws FlightNotFoundException
	{
		Flight flight = 
		new Flight("1234", "mum", LocalDate.parse("2022-02-04", DateTimeFormatter.ISO_DATE),
				LocalTime.parse("13:13:13"), "hyd", LocalDate.parse("1945-02-02"), LocalTime.parse("13:13:13"),
				"12 hours", 0, 0);
		String Id = "1234";
		when(flightservice.getFlight(Id)).thenReturn(flight);
		Flight  response = flightservice.getFlight(Id);
		assertEquals(flight,response);
	}
	
	@Test
	@Order(3)
	public void addFlight() throws FlightAlreadyExistsException
	{
		Flight flight = 
		new Flight("1234", "mum", LocalDate.parse("2022-02-04", DateTimeFormatter.ISO_DATE),
				LocalTime.parse("13:13:13"), "hyd", LocalDate.parse("1945-02-02"), LocalTime.parse("13:13:13"),
				"12 hours", 0, 0);
		when(flightservice.addFlight(flight)).thenReturn(flight);
		Flight  response = flightservice.addFlight(flight);
		assertEquals(flight,response);
	}
	
	@Test
	@Order(4)
	public void updateFlight() throws FlightNotFoundException
	{
		Flight flight = 
		new Flight("1234", "mum", LocalDate.parse("2022-02-04", DateTimeFormatter.ISO_DATE),
				LocalTime.parse("13:13:13"), "hyd", LocalDate.parse("1945-02-02"), LocalTime.parse("13:13:13"),
				"12 hours", 0, 0);
		String Id="1234";
		
		when(flightservice.getFlight(Id)).thenReturn(flight);
		
		when(flightservice.updateFlight(Id, flight)).thenReturn(flight);
		Flight  response = flightservice.updateFlight(Id, flight);
		assertEquals(flight,response);
	}
	
	
	
}
