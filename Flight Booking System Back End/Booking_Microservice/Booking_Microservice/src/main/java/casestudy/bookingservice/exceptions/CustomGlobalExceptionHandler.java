package casestudy.bookingservice.exceptions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	
	  // error handle for @Valid
	  
		@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
				HttpHeaders headers, HttpStatus status, WebRequest request) {

			Map<String, Object> body = new LinkedHashMap<>();
			body.put("timestamp", new Date());
			body.put("status", status.value());

			// Get all errors
			List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
					.collect(Collectors.toList());

			body.put("errors", errors);

			return new ResponseEntity<>(body, headers, status);

		}
	 
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> flightNotFound(UserNotFoundException e)
    {
    	return new ResponseEntity<String>("User Not Found with Id "+e.getUser(),HttpStatus.BAD_REQUEST);
    }
    
	/*
	 * @ExceptionHandler(FlightAlreadyExistsException.class) public
	 * ResponseEntity<String> flightAlreadyExist(FlightAlreadyExistsException e) {
	 * return new
	 * ResponseEntity<String>("Flight Already exists",HttpStatus.BAD_REQUEST); }
	 */

}