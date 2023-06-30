package com.gbyzzz.bar_web_app.bar_backend.security.services;

import com.gbyzzz.bar_web_app.bar_backend.entity.User;
import com.gbyzzz.bar_web_app.bar_backend.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserService userService;

	public UserDetailsServiceImpl(UserService userService) {
		this.userService = userService;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user;
		if(username.contains("@")){
			user = userService.findByEmail(username);
		} else {
			user = userService.findByUsername(username);
		}
		return UserDetailsImpl.build(user);
	}

}
