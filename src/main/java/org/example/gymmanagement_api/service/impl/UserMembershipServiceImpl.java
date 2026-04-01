package org.example.gymmanagement_api.service.impl;

import lombok.AllArgsConstructor;
import org.example.gymmanagement_api.dto.response.UserMembershipResponseDto;
import org.example.gymmanagement_api.entity.MembershipPlan;
import org.example.gymmanagement_api.entity.User;
import org.example.gymmanagement_api.entity.UserMembership;
import org.example.gymmanagement_api.exception.ResourceNotFoundException;
import org.example.gymmanagement_api.repository.MembershipPlanRepository;
import org.example.gymmanagement_api.repository.UserMembershipRepository;
import org.example.gymmanagement_api.service.interfaces.UserMembershipService;
import org.example.gymmanagement_api.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserMembershipServiceImpl implements UserMembershipService {
    private final UserMembershipRepository userMembershipRepository;
    private final MembershipPlanRepository membershipPlanRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    @Override
    public UserMembershipResponseDto buyMembership(Long planId) {
        User currentUser = userService.getCurrenUser();
        MembershipPlan plan = membershipPlanRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Paket bulunamadı"));
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusMonths(plan.getDurationInMonths());

        UserMembership userMembership = new UserMembership();
        userMembership.setPlan(plan);
        userMembership.setUser(currentUser);
        userMembership.setStartDateTime(startTime);
        userMembership.setEndDateTime(endTime);
        userMembership.setActive(true);
        UserMembership savedMembership = userMembershipRepository.save(userMembership);
        return modelMapper.map(savedMembership , UserMembershipResponseDto.class);
    }
}
