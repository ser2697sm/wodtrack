package com.wodtrack.training.workout;

import com.wodtrack.training.athlete.Athlete;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workouts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "athlete_id")
    private Athlete athlete;

    @Column(name = "workout_date",nullable = false)
    private LocalDate date;

    private String notes;

    @OneToMany(mappedBy = "workout",cascade = CascadeType.ALL,orphanRemoval = true)
    @Builder.Default
    private List<WorkoutEntry> entries = new ArrayList<>();

    public void addEntry(WorkoutEntry entry) {
        entries.add(entry);
        entry.setWorkout(this);
    }



}
