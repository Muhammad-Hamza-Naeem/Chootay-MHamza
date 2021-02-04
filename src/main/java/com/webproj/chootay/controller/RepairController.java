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

import com.webproj.chootay.Model.Repair;
import com.webproj.chootay.exception.ResourceNotFoundException;
import com.webproj.chootay.repository.RepairRepository;

@RestController
public class RepairController {

	@Autowired
	private RepairRepository repository;

	@GetMapping("/repairs")
	public List<Repair> getAllRepair() {

		return repository.findAll();
	}

	@PostMapping("/repair")
	public Map createRepair(@Validated @Valid @RequestBody Repair repair) {

		Map<String, String> response = new HashMap<>();

		if (null != repository.findById(repair.getRs_id())) {

			response.put("status", "error");
			response.put("message", "repair already exists");
			return response;

		} else {
			repository.save(repair);
			response.put("status", "success");
			response.put("message", "Repair added successful");

			return response;
		}
	}

	@PutMapping("/repair/{id}")
	public Map<String, String> updateRepair(@PathVariable(value = "id") UUID rs_Id, @Validated @RequestBody Repair details)
			throws ResourceNotFoundException {
		Map<String, String> response = new HashMap<>();
		if (!repository.existsById(rs_Id)) {

			response.put("status", "error");
			response.put("message", "Repair not found");
			return response;

		} else {
			Repair editrepair = repository.findById(rs_Id)
					.orElseThrow(() -> new ResourceNotFoundException("Repair not found " + rs_Id));

			editrepair.setBill(details.getBill());
			editrepair.setItem(details.getItem());
			editrepair.setUpdatedBy(details.getUpdatedBy());
			
			final Repair repair = repository.save(editrepair);
			ResponseEntity.ok(repair);
			response.put("status", "success");
			response.put("message", "Repair updated successful");

		}
		return response;
	}

	@DeleteMapping("/repair/{id}")
	public Map<String, String> deleteRepair(@PathVariable(value = "id") UUID rs_Id) throws Exception {
		Map<String, String> response = new HashMap<>();
		if (!repository.existsById(rs_Id)) {

			response.put("status", "error");
			response.put("message", "Repair not found");

		} else {
			Repair repair = repository.findById(rs_Id)
					.orElseThrow(() -> new ResourceNotFoundException("Repair not found " + rs_Id));
			repository.delete(repair);

			response.put("status", "success");
			response.put("message", "Repair deleted successful");

		}
		return response;
	}
}
