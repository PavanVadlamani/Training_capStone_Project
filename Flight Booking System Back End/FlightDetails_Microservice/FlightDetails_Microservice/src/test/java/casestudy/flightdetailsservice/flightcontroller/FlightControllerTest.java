package casestudy.flightdetailsservice.flightcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.shared.Application;

import casestudy.flightdetailsservice.controller.FlightController;
import casestudy.flightdetailsservice.models.Flight;
import casestudy.flightdetailsservice.security.config.WebSecurityConfig;
import casestudy.flightdetailsservice.security.config.services.UserDetailsServiceImpl;
import casestudy.flightdetailsservice.service.FlightService;

@WebMvcTest(value=FlightController.class)
@Import(WebSecurityTestConfig.class)
@AutoConfigureMockMvc(addFilters = false)
public class FlightControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private FlightService flightService;
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Test
	public void testgetFlights() throws Exception {
		List<Flight> flightlist = new ArrayList<>();
		flightlist.add(new Flight("1234", "mum", LocalDate.parse("2022-02-04", DateTimeFormatter.ISO_DATE),
				LocalTime.parse("13:13:13"), "hyd", LocalDate.parse("1945-02-02"), LocalTime.parse("13:13:13"),
				"12 hours", 0, 0));
		Mockito.when(flightService.getFlights()).thenReturn(flightlist);
		String url = "/flights/getFlights";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String actualJsonResponse = mvcResult.getResponse().getContentAsString();
		System.out.println("from url" + actualJsonResponse);
		String expectedjsonResponse = objectMapper.writeValueAsString(flightlist);
		System.out.println("from list" + expectedjsonResponse);
		assertEquals(expectedjsonResponse, actualJsonResponse);
	}
	 

	@Test
	public void testaddFlight() throws JsonProcessingException, Exception {
		Flight flight = new Flight("1234", "mum", LocalDate.parse("2022-02-04", DateTimeFormatter.ISO_DATE),
				LocalTime.parse("13:13:13"), "hyd", LocalDate.parse("1945-02-02"), LocalTime.parse("13:13:13"),
				"12 hours", 0, 0);
		
		Mockito.when(flightService.addFlight(flight)).thenReturn(flight);
		String url = "/flights/addFlights";
		MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post(url).contentType("application/json")
				.content(objectMapper.writeValueAsString(flight))).andExpect(MockMvcResultMatchers.status().isOk())
		.andReturn();
		String actualJsonResponse = mvcResult.getResponse().getContentAsString();
		System.out.println("from url" + actualJsonResponse);
		
		String expectedjsonResponse = objectMapper.writeValueAsString(flight);
		System.out.println("from list" + expectedjsonResponse);
		assertEquals(expectedjsonResponse, actualJsonResponse);
		
				
	}

}
