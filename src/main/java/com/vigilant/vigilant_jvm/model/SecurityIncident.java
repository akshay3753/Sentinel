package com.vigilant.vigilant_jvm.model;
import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

@Entity @Table(name = "security_incidents")

@Data @NoArgsConstructor @AllArgsConstructor @Builder

public class SecurityIncident {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String threatType;

    private String severity;

    @Column(columnDefinition = "TEXT")

    private String rawLogData;

    private LocalDateTime detectedAt;

}