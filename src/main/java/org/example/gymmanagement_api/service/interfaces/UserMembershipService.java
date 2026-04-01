package org.example.gymmanagement_api.service.interfaces;

import org.example.gymmanagement_api.dto.response.UserMembershipResponseDto;

public interface UserMembershipService{
    UserMembershipResponseDto buyMembership(Long planId);
}
