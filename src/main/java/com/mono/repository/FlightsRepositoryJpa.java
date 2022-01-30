package com.mono.repository;

import com.mono.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightsRepositoryJpa extends JpaRepository<Flight,Long> {
}
