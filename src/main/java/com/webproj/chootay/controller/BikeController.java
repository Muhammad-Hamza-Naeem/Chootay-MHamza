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

import com.webproj.chootay.Model.Admin;
import com.webproj.chootay.Model.Bike;
import com.webproj.chootay.exception.ResourceNotFoundException;
import com.webproj.chootay.repository.AdminRepository;
import com.webproj.chootay.repository.BikeRepository;

@RestController
public class BikeController {

	@Autowired
	private BikeRepository repository;

	@GetMapping("/bikes")
	public List<Bike> getAllBikes() {

		return repository.findAll();
	}

	@PostMapping("/bike")
	public Map createBike(@Validated @Valid @RequestBody Bike bike) {

		Map<String, String> response = new HashMap<>();

		if (null != repository.findById(bike.getBike_id())) {

			response.put("status", "error");
			response.put("message", "Bike already exists");
			return response;

		} else {
			repository.save(bike);
			response.put("status", "success");
			response.put("message", "bike added successful");

			return response;
		}
	}

	@PutMapping("/bike/{id}")
	public Map<String, String> updateBike(@PathVariable(value = "id") UUID bikeId, @Validated @RequestBody Bike details)
			throws ResourceNotFoundException {
		Map<String, String> response = new HashMap<>();
		if (!repository.existsById(bikeId)) {

			response.put("status", "error");
			response.put("message", "user not found");
			return response;

		} else {
			Bike bike = repository.findById(bikeId)
					.orElseThrow(() -> new ResourceNotFoundException("Bike not found " + bikeId));

			bike.setName(details.getName());
			bike.setModel(details.getModel());
			bike.setYear(details.getYear());
			bike.setUpdatedBy(details.getUpdatedBy());
			bike.setSalePrize(details.getSalePrize());
			bike.setPurchasePrize(details.getPurchasePrize());

			final Bike updatedBike = repository.save(bike);
			ResponseEntity.ok(updatedBike);
			response.put("status", "success");
			response.put("message", "user updated successful");

		}
		return response;
	}

	@DeleteMapping("/bike/{id}")
	public Map<String, String> deleteBike(@PathVariable(value = "id") UUID bikeId) throws Exception {
		Map<String, String> response = new HashMap<>();
		if (!repository.existsById(bikeId)) {

			response.put("status", "error");
			response.put("message", "bike not found");

		} else {
			Bike bike = repository.findById(bikeId)
					.orElseThrow(() -> new ResourceNotFoundException("Bike not found " + bikeId));
			repository.delete(bike);

			response.put("status", "success");
			response.put("message", "Bike deleted successful");

		}
		return response;
	}
}
