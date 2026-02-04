package com.vigilant.vigilant_jvm.service;

import com.vigilant.vigilant_jvm.model.SecurityIncident;
import com.vigilant.vigilant_jvm.repository.SecurityIncidentRepository;
import com.vigilant.vigilant_jvm.strategy.ThreatDetector;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SecurityScanner {

    private final SecurityIncidentRepository repository;
    private final List<ThreatDetector> detectors;

    public SecurityScanner(SecurityIncidentRepository repository, List<ThreatDetector> detectors) {
        this.repository = repository;
        this.detectors = detectors;
        System.out.println("🛡️ SYSTEM START: Vigilent loaded with " + detectors.size() + " detectors.");
    }

    @Async
    public void scanLog(String logData) {
        for (ThreatDetector detector : detectors) {
            if (detector.isThreat(logData)) {
                saveIncident(logData, detector.getThreatType());
            }
        }
    }

    private void saveIncident(String logData, String type) {
        SecurityIncident incident = new SecurityIncident();
        incident.setRawLogData(logData);
        incident.setThreatType(type);
        incident.setSeverity("CRITICAL");
        incident.setDetectedAt(LocalDateTime.now());

        repository.save(incident);
        System.out.println("🚨 VIGILANT ALERT: [" + type + "] detected on thread: " + Thread.currentThread().getName());
    }
}
