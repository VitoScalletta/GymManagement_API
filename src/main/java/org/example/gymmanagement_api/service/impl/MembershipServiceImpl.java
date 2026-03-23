package org.example.gymmanagement_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.gymmanagement_api.dto.request.MembershipPlanRequestDto;
import org.example.gymmanagement_api.dto.response.MembershipPlanResponseDto;
import org.example.gymmanagement_api.entity.MembershipPlan;
import org.example.gymmanagement_api.exception.BusinessRuleException;
import org.example.gymmanagement_api.exception.ResourceNotFoundException;
import org.example.gymmanagement_api.repository.MembershipPlanRepository;
import org.example.gymmanagement_api.service.interfaces.MembershipPlanService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipPlanService {
    private final MembershipPlanRepository membershipPlanRepository;
    private final ModelMapper modelMapper;

    @Override
    public MembershipPlanResponseDto createMembershipPlan(MembershipPlanRequestDto requestDto) {
        if (membershipPlanRepository.existsByName(requestDto.getName())){
            throw new BusinessRuleException("Hata : "+requestDto.getName()+" Adında bir paket zaten var");
        }
        MembershipPlan createMembershipPlan = modelMapper.map(requestDto, MembershipPlan.class);
        MembershipPlan savedMembershipPlan = membershipPlanRepository.save(createMembershipPlan);
        return modelMapper.map(savedMembershipPlan, MembershipPlanResponseDto.class);
    }

    @Override
    public MembershipPlanResponseDto getMembershipPlanById(Long Id) {
        MembershipPlan plan = membershipPlanRepository.findById(Id)
                .orElseThrow(()-> new ResourceNotFoundException("Hata : "+Id+" numaralı paket bulunamadı"));
        return modelMapper.map(plan, MembershipPlanResponseDto.class);
    }

    @Override
    public List<MembershipPlanResponseDto> getAllMembershipPlan() {
        List<MembershipPlan> membershipPlans = membershipPlanRepository.findAll();
        return membershipPlans.stream()
                .map(MembershipPlan -> modelMapper.map(MembershipPlan, MembershipPlanResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MembershipPlanResponseDto updateMembershipPlan(Long id, MembershipPlanRequestDto requestDto) {
        MembershipPlan existingPlan = membershipPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hata : Paket bulunamadı"));

        if (!existingPlan.getName().equals(requestDto.getName()) && membershipPlanRepository.existsByName(requestDto.getName())) {
            throw new BusinessRuleException("Hata : Böyle bir paket mevcut!!");
        }

        modelMapper.map(requestDto, existingPlan);
        MembershipPlan updatedMembershipPlan = modelMapper.map(requestDto, MembershipPlan.class);
        return modelMapper.map(updatedMembershipPlan , MembershipPlanResponseDto.class);
    }


    @Override
    public void deleteMembershipPlan(Long id) {
        if (!membershipPlanRepository.existsById(id)){
            throw new ResourceNotFoundException("Hata : Silmek istenen "+id+" numaralı paket bulunamadı");
        }

        membershipPlanRepository.deleteById(id);
    }
}
