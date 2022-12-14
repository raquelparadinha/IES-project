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
	private GuardRepository guardRepository;

	@Autowired
	private WardenRepository wardenRepository;

	@Override
	@Transactional
	public UserDetailsImpl loadUserByUsername(String email) { 
		Guard guard = guardRepository.findByEmail(email);
		if (guard == null) {
			Warden warden = wardenRepository.findByEmail(email);
			if (warden == null) {
				throw(new UsernameNotFoundException("User Not Found with email: " + email));
			}
			return UserDetailsImpl.build(warden);
		}
		System.out.println(guard.toString());
		return UserDetailsImpl.build(guard);
	}
	
}
