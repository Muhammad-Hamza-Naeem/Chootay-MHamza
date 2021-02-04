package com.webproj.chootay.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.webproj.chootay.Model.Customers;
import com.webproj.chootay.exception.ResourceNotFoundException;
import com.webproj.chootay.repository.CustomerRepository;

@RestController
public class CustomerController {
	@Autowired
	private CustomerRepository repository;

	@GetMapping("/customers")
	public List<Customers> getAllCustomers() {

		return repository.findAll();
	}

	@PostMapping("/customer")
	public Map createCustomers(@Validated @Valid @RequestBody Customers customer) {

		Map<String, String> response = new HashMap<>();

		if (null != repository.findById(customer.getCus_id())) {

			response.put("status", "error");
			response.put("message", "Customer already exists");
			return response;

		} else {
			repository.save(customer);
			response.put("status", "success");
			response.put("message", "Customer added successful");

			return response;
		}
	}

	@PutMapping("/customer/{id}")
	public Map<String, String> updateCustomers(@PathVariable(value = "id") UUID Id,
			@Validated @RequestBody Customers details) throws ResourceNotFoundException {
		Map<String, String> response = new HashMap<>();
		if (!repository.existsById(Id)) {

			response.put("status", "error");
			response.put("message", "Customer not found");
			return response;

		} else {
			Customers customer = repository.findById(Id)
					.orElseThrow(() -> new ResourceNotFoundException("Customer not found " + Id));

			customer.setName(details.getName());
			customer.setSalePrize(details.getSalePrize());
			customer.setUpdatedBy(details.getUpdatedBy());

			final Customers updatedCustomer = repository.save(customer);
			ResponseEntity.ok(updatedCustomer);
			response.put("status", "success");
			response.put("message", "Customer updated successful");

		}
		return response;
	}

	@DeleteMapping("/customer/{id}")
	public Map<String, String> deleteCustomers(@PathVariable(value = "id") UUID Id) throws Exception {
		Map<String, String> response = new HashMap<>();
		if (!repository.existsById(Id)) {

			response.put("status", "error");
			response.put("message", "Customer not found");

		} else {
			Customers customer = repository.findById(Id)
					.orElseThrow(() -> new ResourceNotFoundException("Customer not found " + Id));
			repository.delete(customer);

			response.put("status", "success");
			response.put("message", "Customer deleted successful");

		}
		return response;
	}
}
