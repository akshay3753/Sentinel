package com.vigilant.vigilant_jvm;

import com.vigilant.vigilant_jvm.repository.SecurityIncidentRepository;
import com.vigilant.vigilant_jvm.service.SecurityScanner;
import com.vigilant.vigilant_jvm.strategy.SqlInjectionDetector;
import com.vigilant.vigilant_jvm.strategy.ThreatDetector;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// Notice: NO @SpringBootTest annotation here. We want this to be fast and DB-free.
class VigilantJvmApplicationTests {

    @Test
    void testSqlInjectionDetection() {
        // 1. Create a "Fake" repository
        SecurityIncidentRepository mockRepo = mock(SecurityIncidentRepository.class);
        
        // 2. Setup the detectors
        List<ThreatDetector> detectors = List.of(new SqlInjectionDetector());
        
        // 3. Initialize the scanner manually
        SecurityScanner scanner = new SecurityScanner(mockRepo, detectors);

        // 4. Act: Simulate a threat
        scanner.scanLog("DROP TABLE users");

        // 5. Assert: Verify the mock was called
        verify(mockRepo, times(1)).save(any());
        System.out.println("✅ TEST PASSED: Vigilant correctly handled the threat without needing a DB connection.");
    }
}
