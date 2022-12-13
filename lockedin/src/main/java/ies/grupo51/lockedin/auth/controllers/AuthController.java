package ies.grupo51.lockedin.auth.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ies.grupo51.lockedin.auth.payload.request.LoginRequest;
import ies.grupo51.lockedin.auth.payload.response.JwtResponse;
import ies.grupo51.lockedin.auth.security.jwt.JwtUtils;
import ies.grupo51.lockedin.auth.security.services.UserDetailsImpl;
import ies.grupo51.lockedin.models.Guard;
import ies.grupo51.lockedin.repositories.GuardRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	GuardRepository repository;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		// try {
			Guard test = repository.findByEmail(loginRequest.getEmail());
			System.out.println("\n"+ test.toString()+ "\n");
			System.out.println("\n"+ loginRequest.getEmail() + "\n" + loginRequest.getPassword());
			// authenticationManager.authenticate(
            //         new UsernamePasswordAuthenticationToken( loginRequest.getEmail(), loginRequest.getPassword()));
					
			
           
			// UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
			
			// System.out.println("\n"+ credentials.getCredentials().toString() + "\n" );
			Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
			System.out.println("\nAUTENTICADO\n");
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);
			
			System.out.println(jwt);
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());

			return ResponseEntity.ok(new JwtResponse(jwt, 
													userDetails.getId(), 
													userDetails.getEmail(), 
													roles));
		// } catch (Exception e) {
        //     System.out.println(e.getMessage());
        //     System.out.println(e.getCause());
        //     System.out.println(e.getLocalizedMessage());
        //     // throw new BadCredentialsException("Invalid email/password supplied!");
        // }
	}

	// @PostMapping("/signup")
	// public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

	// 	if (guardRepository.existsByEmail(signUpRequest.getEmail())) {
	// 		return ResponseEntity
	// 				.badRequest()
	// 				.body(new MessageResponse("Error: Email is already in use!"));
	// 	}

	// 	// Create new user's account
	// 	Guard user = new Guard(signUpRequest.getEmail(),
	// 						 encoder.encode(signUpRequest.getPassword()));

	// 	Set<String> strRoles = signUpRequest.getRoles();
	// 	Set<Role> roles = new HashSet<>();

	// 	if (strRoles == null) {
	// 		Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	// 				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	// 		roles.add(userRole);
	// 	} else {
	// 		strRoles.forEach(role -> {
	// 			switch (role) {
	// 			case "admin":
	// 				Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
	// 						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	// 				roles.add(adminRole);

	// 				break;
	// 			default:
	// 				Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	// 						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	// 				roles.add(userRole);
	// 			}
	// 		});
	// 	}

	// 	user.setRoles(roles);
	// 	guardRepository.save(user);

	// 	return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	// }
}
