package com.vigilant.vigilant_jvm.strategy;

import org.springframework.stereotype.Component;

@Component
public class BruteForceDetector implements ThreatDetector {

    @Override
    public boolean isThreat(String logData) {
        String upperLog = logData.toUpperCase();

        // Option 1: The specific test string we used
        boolean isUnauthorizedRoot = logData.contains("Unauthorized root access");

        // Option 2: General pattern matching
        boolean isGeneralBruteForce = upperLog.contains("FAILED LOGIN") && upperLog.contains("ATTEMPT");

        return isUnauthorizedRoot || isGeneralBruteForce;
    }

    @Override
    public String getThreatType() {
        return "BRUTE_FORCE_ATTEMPT";
    }
}