package com.webproj.chootay.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproj.chootay.Model.Repair;

public interface RepairRepository extends JpaRepository<Repair, UUID> {

}
