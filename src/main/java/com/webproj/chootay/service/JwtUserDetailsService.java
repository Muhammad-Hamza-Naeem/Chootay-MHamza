package com.webproj.chootay.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.webproj.chootay.repository.AdminRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private AdminRepository adminUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.webproj.chootay.Model.Admin AdminUser;
		AdminUser = adminUserRepository.findByUsername(username);

		if (AdminUser != null) {
			return new User(username, AdminUser.getPassword(), new ArrayList<>());
		} else {

			throw new UsernameNotFoundException("User not found with username: " + username);
		}

	}
}
