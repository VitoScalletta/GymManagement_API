package org.example.gymmanagement_api.repository;

import org.example.gymmanagement_api.entity.CheckIn;
import org.example.gymmanagement_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {
    boolean existsByUserAndCheckInTimeBeetween(User user, LocalDateTime checkInTime);
}
