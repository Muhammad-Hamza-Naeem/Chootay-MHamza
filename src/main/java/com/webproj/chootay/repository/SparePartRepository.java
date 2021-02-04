package com.webproj.chootay.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproj.chootay.Model.SparePart;

public interface SparePartRepository extends JpaRepository<SparePart, UUID>{

}
