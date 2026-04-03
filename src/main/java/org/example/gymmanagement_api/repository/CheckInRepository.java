package org.example.gymmanagement_api.repository;

import org.example.gymmanagement_api.entity.CheckIn;
import org.example.gymmanagement_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {
    @Query("SELECT COUNT(c) > 0 FROM CheckIn c WHERE c.user = :user AND c.checkInTime >= :startOfDay And c.checkInTime <= :endOfDay")
    boolean hasUserCheckInToday(
            @Param("user")User user,
            @Param("startOfDay")LocalDateTime startOfDay,
            @Param("endOfDay")LocalDateTime endOfDay
    );
}
