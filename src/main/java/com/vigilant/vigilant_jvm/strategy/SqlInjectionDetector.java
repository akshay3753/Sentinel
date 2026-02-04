package com.vigilant.vigilant_jvm.strategy;

import org.springframework.stereotype.Component;

@Component
public class SqlInjectionDetector implements ThreatDetector {
    @Override
    public boolean isThreat(String logData) {
        String upperLog = logData.toUpperCase();
        return upperLog.contains("DROP") || upperLog.contains("SELECT *") || upperLog.contains("' OR 1=1");
    }

    @Override
    public String getThreatType() {
        return "SQL_INJECTION";
    }
}
