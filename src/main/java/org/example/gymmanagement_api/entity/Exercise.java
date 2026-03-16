package org.example.gymmanagement_api.entity;

import jakarta.persistence.*;
import org.hibernate.jdbc.Work;

@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "sets",nullable = false)
    private int sets;

    @Column(name = "reps",nullable = false)
    private int reps;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_program_id",nullable = false)
    private WorkoutProgram workoutProgram;
}
