package org.example.gymmanagement_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "user_membreship")
public class UserMembership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id",nullable = false)
    private MembershipPlan plan;

    @Column(name = "startDate",nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "endDate",nullable = false)
    private LocalDateTime endDateTime;

    @Column(name = "isActive",nullable = false)
    private boolean isActive;
}
