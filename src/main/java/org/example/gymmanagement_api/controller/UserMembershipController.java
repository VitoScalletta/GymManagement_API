package org.example.gymmanagement_api.controller;

import lombok.RequiredArgsConstructor;
import org.example.gymmanagement_api.dto.response.UserMembershipResponseDto;
import org.example.gymmanagement_api.service.interfaces.UserMembershipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-membership")
@RequiredArgsConstructor
public class UserMembershipController {
    private final UserMembershipService userMembershipService;

    @PostMapping("/{planId}")
    public ResponseEntity<UserMembershipResponseDto> buyMembership(@PathVariable Long  planId) {
        return ResponseEntity.ok(userMembershipService.buyMembership(planId));
    }

}
