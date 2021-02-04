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

import com.webproj.chootay.Model.SparePart;
import com.webproj.chootay.exception.ResourceNotFoundException;
import com.webproj.chootay.repository.SparePartRepository;

@RestController
public class SparePartController {

	@Autowired
	private SparePartRepository repository;

	@GetMapping("/spareparts")
	public List<SparePart> getAllSpareParts() {

		return repository.findAll();
	}

	@PostMapping("/sparepart")
	public Map createSparePart(@Validated @Valid @RequestBody SparePart sparepart) {

		Map<String, String> response = new HashMap<>();

		if (null != repository.findById(sparepart.getId())) {

			response.put("status", "error");
			response.put("message", "Spare Part already exists");
			return response;

		} else {
			repository.save(sparepart);
			response.put("status", "success");
			response.put("message", "spare part added successful");

			return response;
		}
	}

	@PutMapping("/sparepart/{id}")
	public Map<String, String> updateSparePart(@PathVariable(value = "id") UUID sparepartId,
			@Validated @RequestBody SparePart details) throws ResourceNotFoundException {
		Map<String, String> response = new HashMap<>();
		if (!repository.existsById(sparepartId)) {

			response.put("status", "error");
			response.put("message", "Spare Part not found");
			return response;

		} else {
			SparePart sparepart = repository.findById(sparepartId)
					.orElseThrow(() -> new ResourceNotFoundException("Spare Part not found " + sparepartId));

			sparepart.setPartName(details.getPartName());
			sparepart.setDescription(details.getDescription());
			sparepart.setPartNumber(details.getPartNumber());
			sparepart.setUpdatedBy(details.getUpdatedBy());
			sparepart.setSalePrize(details.getSalePrize());
			sparepart.setPurchasePrize(details.getPurchasePrize());

			final SparePart part = repository.save(sparepart);
			ResponseEntity.ok(part);
			response.put("status", "success");
			response.put("message", "Spare Part updated successful");

		}
		return response;
	}

	@DeleteMapping("/sparepart/{id}")
	public Map<String, String> deleteSparePart(@PathVariable(value = "id") UUID sparepartId) throws Exception {
		Map<String, String> response = new HashMap<>();
		if (!repository.existsById(sparepartId)) {

			response.put("status", "error");
			response.put("message", "Spare Part not found");

		} else {
			SparePart sparepart = repository.findById(sparepartId)
					.orElseThrow(() -> new ResourceNotFoundException("Spare Part not found " + sparepartId));
			repository.delete(sparepart);

			response.put("status", "success");
			response.put("message", "Spare Part deleted successful");

		}
		return response;
	}
}
