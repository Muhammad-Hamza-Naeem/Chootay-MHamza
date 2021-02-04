package com.webproj.chootay.controller;

import java.util.Map;
import java.util.Objects;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webproj.chootay.Model.Admin;
import com.webproj.chootay.Model.AdminLogin;
import com.webproj.chootay.configuration.JwtTokenUtil;
import com.webproj.chootay.repository.AdminRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class LoginController {
	@Autowired
	private AdminRepository adminUserRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping("/Adminlogin")
	public Map LoginUser(@Validated @RequestBody AdminLogin user) throws Exception {

		Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

		Admin dbUser;

		JSONObject response = new JSONObject();

		dbUser = adminUserRepository.findByUsername(user.getUsername());

		if (null == dbUser) {

			response.put("status", "error");
			response.put("message", "username not found");
			return response;

		} else if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {

			authenticate(user.getUsername(), user.getPassword());

			final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(user.getUsername());

			final String token = jwtTokenUtil.generateToken(userDetails);
			response.put("status", "success");
			response.put("message", "user login successful");
			response.put("data", dbUser.UserJson());
			response.put("token", token);


			return response;
		} else {

			response.put("status", "error");
			response.put("message", "incorrect password");

			return response;

		}
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
