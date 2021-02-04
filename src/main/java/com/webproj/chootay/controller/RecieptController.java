package com.webproj.chootay.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webproj.chootay.Model.Reciept;
import com.webproj.chootay.Model.SparePart;
import com.webproj.chootay.exception.ResourceNotFoundException;
import com.webproj.chootay.repository.RecieptRepository;
import com.webproj.chootay.repository.SparePartRepository;

@RestController
public class RecieptController {

	@Autowired
	private RecieptRepository repository;

	@GetMapping("/reciept")
	public List<Reciept> getAllReciepts() {

		return repository.findAll();
	}

	@PostMapping("/reciept")
	public Map createReciept(@Validated @Valid @RequestBody Reciept reciept) {

		Map<String, String> response = new HashMap<>();

		if (null != repository.findById(reciept.getRec_id())) {

			response.put("status", "error");
			response.put("message", "Reciept already exists");
			return response;

		} else {
			repository.save(reciept);
			response.put("status", "success");
			response.put("message", "Reciept added successful");

			return response;
		}
	}

	@PutMapping("/reciept/{id}")
	public Map<String, String> updateReciept(@PathVariable(value = "id") UUID recieptId,
			@Validated @RequestBody Reciept details) throws ResourceNotFoundException {
		Map<String, String> response = new HashMap<>();
		if (!repository.existsById(recieptId)) {

			response.put("status", "error");
			response.put("message", "Reciept not found");
			return response;

		} else {
			Reciept reciept = repository.findById(recieptId)
					.orElseThrow(() -> new ResourceNotFoundException("Reciept not found " + recieptId));

			reciept.setDescription(details.getDescription());
			reciept.setPrice(details.getPrice());
			reciept.setQuantity(details.getQuantity());

			final Reciept part = repository.save(reciept);
			ResponseEntity.ok(part);
			response.put("status", "success");
			response.put("message", "Reciept updated successful");

		}
		return response;
	}

	@DeleteMapping("/reciept/{id}")
	public Map<String, String> deleteReciept(@PathVariable(value = "id") UUID recieptId) throws Exception {
		Map<String, String> response = new HashMap<>();
		if (!repository.existsById(recieptId)) {

			response.put("status", "error");
			response.put("message", "Reciept not found");

		} else {
			Reciept reciept = repository.findById(recieptId)
					.orElseThrow(() -> new ResourceNotFoundException("Reciept not found " + recieptId));
			repository.delete(reciept);

			response.put("status", "success");
			response.put("message", "Reciept deleted successful");

		}
		return response;
	}
}
