package org.example.gymmanagement_api.repository;

import org.example.gymmanagement_api.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByWorkoutProgramId(Long workoutProgramId);
}
