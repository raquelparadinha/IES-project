package ies.grupo51.lockedin.auth.security.services;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ies.grupo51.lockedin.models.Guard;
import ies.grupo51.lockedin.models.Warden;
import ies.grupo51.lockedin.repositories.GuardRepository;
import ies.grupo51.lockedin.repositories.WardenRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	GuardRepository guardRepository;

	@Autowired
	WardenRepository wardenRepository;

	@Override
	@Transactional
	public UserDetailsImpl loadUserByUsername(String email) { //throws UsernameNotFoundException {
		Guard guard = guardRepository.findByEmail(email);
		if (guard == null) {
			Warden warden = wardenRepository.findByEmail(email);
			if (warden == null) {
				// System.out.println("\n\n PYLANCEEEEEEEEEEE \n\n");
				throw(new UsernameNotFoundException("User Not Found with email: " + email));
			}
			return UserDetailsImpl.build(warden);
		}
		System.out.println(guard.toString());
		return UserDetailsImpl.build(guard);
	}
	
}

		// try {
		// 	Guard user = guardRepository.findByEmail(email).orElse(null);
		// 	return UserDetailsImpl.build(user);
		// } catch (Exception e) {
		// 	Warden user = wardenRepository.findByEmail(email)
		// 			.orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));;
		// 	return UserDetailsImpl.build(user);
		// } 
