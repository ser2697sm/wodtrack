package com.wodtrack.training.athlete;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "athletes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Athlete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, updatable = false, insertable = false)
    private OffsetDateTime createdAt;
}
