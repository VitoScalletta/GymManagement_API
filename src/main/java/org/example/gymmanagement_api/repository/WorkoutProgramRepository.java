package org.example.gymmanagement_api.repository;

import org.example.gymmanagement_api.entity.WorkoutProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutProgramRepository extends JpaRepository {
    List<WorkoutProgram> findByUserId(Long userId);


}
