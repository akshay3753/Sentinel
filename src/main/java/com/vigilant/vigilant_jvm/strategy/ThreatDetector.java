package com.vigilant.vigilant_jvm.strategy;

public interface ThreatDetector {
    boolean isThreat(String logData);
    String getThreatType();
}
