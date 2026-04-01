package org.example.gymmanagement_api.repository;

import org.example.gymmanagement_api.entity.User;
import org.example.gymmanagement_api.entity.UserMembership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface UserMembershipRepository extends JpaRepository<UserMembership, Long> {
    boolean existsByUserAndIsActiveTrueAndDateTimeAfter(User user, LocalDateTime date);

}
