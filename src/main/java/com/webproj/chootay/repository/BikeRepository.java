package com.webproj.chootay.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproj.chootay.Model.Bike;

public interface BikeRepository extends JpaRepository<Bike, UUID> {

}
