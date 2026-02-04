package com.vigilant.vigilant_jvm.repository;

import com.vigilant.vigilant_jvm.model.SecurityIncident; // THIS LINE WAS MISSING
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityIncidentRepository extends JpaRepository<SecurityIncident, Long> {
}