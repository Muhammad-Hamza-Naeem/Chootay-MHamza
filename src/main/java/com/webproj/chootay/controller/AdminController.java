package com.webproj.chootay.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webproj.chootay.Model.Admin;
import com.webproj.chootay.exception.ResourceNotFoundException;
import com.webproj.chootay.repository.AdminRepository;

@RestController
public class AdminController {

	@Autowired
	private AdminRepository adminUserRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Get all users list.
	 *
	 * @return the list
	 */

	@GetMapping("/admin")
	public List<Admin> getAllUsers() {

		return adminUserRepository.findAll();
	}

	/**
	 * Create user user.
	 *
	 * @param user the user
	 * @return the user
	 */

	@PostMapping("/createAdmin")
	public Map createUser(@Validated @Valid @RequestBody Admin user) {

		Map<String, String> response = new HashMap<>();

		if (null != adminUserRepository.findByEmail(user.getEmail())) {

			response.put("status", "error");
			response.put("message", "email already exists");
			return response;

		} else if (null != adminUserRepository.findByUsername(user.getUsername())) {

			response.put("status", "error");
			response.put("message", "username already exists");
			return response;

		}

		else {

			user.setPassword(passwordEncoder.encode(user.getPassword()));

			adminUserRepository.save(user);
			response.put("status", "success");
			response.put("message", "user added successful");

			return response;
		}
	}

	/**
	 * Update user response entity.
	 *
	 * @param userId      the user id
	 * @param userDetails the user details
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 * 
	 * 
	 */
	@PutMapping("/admin/{id}")
	public Map<String, String> updateUser(@PathVariable(value = "id") UUID userId,
			@Validated @RequestBody Admin userDetails) throws ResourceNotFoundException {
		Map<String, String> response = new HashMap<>();
		if (!adminUserRepository.existsById(userId)) {

			response.put("status", "error");
			response.put("message", "user not found");
			return response;

		} else {
			Admin user = adminUserRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User not found " + userId));

			user.setUsername(userDetails.getUsername());
			user.setUserType(userDetails.getUserType());
			user.setUpdatedAt(new Date());
			user.setUserLevel(userDetails.getUserLevel());
			user.setEmail(userDetails.getEmail());
			user.setPassword(userDetails.getPassword());
			user.setUpdatedBy(userDetails.getUpdatedBy());

			final Admin updatedUser = adminUserRepository.save(user);
			ResponseEntity.ok(updatedUser);
			response.put("status", "success");
			response.put("message", "user updated successful");

		}
		return response;
	}

	/**
	 * Delete user map.
	 *
	 * @param userId the user id
	 * @return the map
	 * @throws Exception the exception
	 * 
	 * 
	 */

	@DeleteMapping("/admin/{id}")
	public Map<String, String> deleteUser(@PathVariable(value = "id") UUID userId) throws Exception {
		Map<String, String> response = new HashMap<>();
		if (!adminUserRepository.existsById(userId)) {

			response.put("status", "error");
			response.put("message", "user not found");

		} else {
			Admin user = adminUserRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User not found " + userId));
			adminUserRepository.delete(user);

			response.put("status", "success");
			response.put("message", "user deleted successful");

		}
		return response;
	}
}
