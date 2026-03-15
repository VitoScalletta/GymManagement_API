package org.example.gymmanagement_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "sets",nullable = false)
    private Long sets;

    @Column(name = "reps",nullable = false)
    private Long reps;

    @Column(name = "workout_program_id",nullable = false)
    private Long workoutProgramId;
}
