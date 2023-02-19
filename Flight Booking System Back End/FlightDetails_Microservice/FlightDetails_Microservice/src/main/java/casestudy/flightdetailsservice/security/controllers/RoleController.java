package casestudy.flightdetailsservice.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import casestudy.flightdetailsservice.security.models.Role;
import casestudy.flightdetailsservice.security.repository.RoleRepository;

@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	RoleRepository rolerepo;
	
	@PostMapping("/addrole")
	public Role addrole(@RequestBody Role role)
	{
		return rolerepo.save(role);
	}

}
