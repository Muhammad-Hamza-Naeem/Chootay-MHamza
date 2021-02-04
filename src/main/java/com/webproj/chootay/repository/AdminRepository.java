package com.webproj.chootay.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webproj.chootay.Model.Admin;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
	
	public Admin findByEmail(String email);
	public Admin findByUsername(String username);
	public Admin findByid(UUID id);

	
}