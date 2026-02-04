package com.vigilant.vigilant_jvm.controller;

import com.vigilant.vigilant_jvm.model.SecurityIncident;
import com.vigilant.vigilant_jvm.repository.SecurityIncidentRepository;
import com.vigilant.vigilant_jvm.service.SecurityScanner;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sentinel")
public class SecurityController {

    private final SecurityScanner securityScanner;
    private final SecurityIncidentRepository repository; // Add this

    public SecurityController(SecurityScanner securityScanner, SecurityIncidentRepository repository) {
        this.securityScanner = securityScanner;
        this.repository = repository;
    }

    @PostMapping("/scan")
    public String analyzeLog(@RequestBody String logData) {
        securityScanner.scanLog(logData);
        return "Log received. Vigilant is processing the stream...";
    }

    @GetMapping("/dashboard")
    public List<SecurityIncident> getIncidents() {
        return repository.findAll(); // Fetches all rows from the DB
    }

    // This allows the "Resolve" button on your dashboard to work
    @DeleteMapping("/dashboard/{id}")
    public void resolveIncident(@PathVariable Long id) {
        repository.deleteById(id);
    }
}