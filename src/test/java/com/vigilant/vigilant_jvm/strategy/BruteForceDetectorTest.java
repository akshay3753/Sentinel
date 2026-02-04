package com.vigilant.vigilant_jvm.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BruteForceDetectorTest {

    private BruteForceDetector detector;

    @BeforeEach
    void setUp() {
        detector = new BruteForceDetector();
    }

    @Test
    void shouldDetectUnauthorizedRootAccess() {
        assertTrue(detector.isThreat("ALERT: Unauthorized root access attempt"));
    }

    @Test
    void shouldIgnoreSafeLogs() {
        assertFalse(detector.isThreat("User logged in successfully"));
    }
}
