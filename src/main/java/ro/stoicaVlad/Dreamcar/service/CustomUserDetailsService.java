package ro.stoicaVlad.Dreamcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.stoicaVlad.Dreamcar.dao.UserRepository;
import ro.stoicaVlad.Dreamcar.domain.User;

import java.util.HashSet;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	//get user from the database, via Hibernate
	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(final String username)
		throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		HashSet<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("USER"));

		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), grantedAuthorities);

	}


}
