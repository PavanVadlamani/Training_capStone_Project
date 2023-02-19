package casestudy.flightdetailsservice.security.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import casestudy.flightdetailsservice.security.config.jwt.JwtUtils;
import casestudy.flightdetailsservice.security.config.services.UserDetailsImpl;
import casestudy.flightdetailsservice.security.models.ERole;
import casestudy.flightdetailsservice.security.models.Role;
import casestudy.flightdetailsservice.security.models.User;
import casestudy.flightdetailsservice.security.repository.RoleRepository;
import casestudy.flightdetailsservice.security.repository.UserRepository;
import casestudy.flightdetailsservice.security.requests.LoginRequest;
import casestudy.flightdetailsservice.security.requests.SignupRequest;
import casestudy.flightdetailsservice.security.response.JwtResponse;
import casestudy.flightdetailsservice.security.response.MessageResponse;
import casestudy.flightdetailsservice.security.services.JwtService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	JwtService service;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		JwtResponse jwtToken = new JwtResponse();
		jwtToken.setAccessToken(jwt);
		jwtToken.setId("UserID");
		jwtToken.setEmail(userDetails.getEmail());
		jwtToken.setTokenType("Bearer");
		jwtToken.setUsername(userDetails.getUsername());
		jwtToken.setRoles(roles);
		Optional<JwtResponse> updateResponse = service.getId(jwtToken.getId());
		if (!updateResponse.isPresent()) {
			service.addToken(jwtToken);
		}
		else
		{
			JwtResponse updatedToken = updateResponse.get();
			updatedToken.setId(jwtToken.getId());
			updatedToken.setAccessToken(jwtToken.getAccessToken());
			updatedToken.setEmail(jwtToken.getEmail());
			updatedToken.setRoles(jwtToken.getRoles());
			updatedToken.setUsername(jwtToken.getUsername());
			updatedToken.setTokenType(jwtToken.getTokenType());
			service.updatetoken(updatedToken);
		}
		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@GetMapping("/find")
	public List<Role> find() {
		return roleRepository.findAll();
	}

}
