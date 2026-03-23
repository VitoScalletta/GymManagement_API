package org.example.gymmanagement_api.service.interfaces;

import org.example.gymmanagement_api.dto.request.MembershipPlanRequestDto;
import org.example.gymmanagement_api.dto.response.MembershipPlanResponseDto;
import org.example.gymmanagement_api.entity.MembershipPlan;

import java.util.List;

public interface MembershipPlanService {

    MembershipPlanResponseDto createMembershipPlan(MembershipPlanRequestDto requestDto);
    List<MembershipPlanResponseDto> getAllMembershipPlan();
    MembershipPlanResponseDto getMembershipPlanById(Long Id);

    MembershipPlanResponseDto updateMembershipPlan(Long id,MembershipPlanRequestDto requestDto);

    void deleteMembershipPlan(Long id);


}
