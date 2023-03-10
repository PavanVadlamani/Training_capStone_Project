package ApiGateWay.ApiGateWayService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
public class ApiGateWayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGateWayServiceApplication.class, args);
	}

}
