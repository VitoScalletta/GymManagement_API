package org.example.gymmanagement_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.gymmanagement_api.dto.response.CheckInResponseDto;
import org.example.gymmanagement_api.entity.User;
import org.example.gymmanagement_api.entity.CheckIn;
import org.example.gymmanagement_api.exception.BusinessRuleException;
import org.example.gymmanagement_api.repository.CheckInRepository;
import org.example.gymmanagement_api.repository.UserMembershipRepository;
import org.example.gymmanagement_api.service.interfaces.CheckInService;
import org.example.gymmanagement_api.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class CheckInServiceImpl implements CheckInService {

    private final CheckInRepository checkInRepository;
    private final UserMembershipRepository userMembershipRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public CheckInResponseDto doCheckIn() {
        User currentUser = userService.getCurrenUser();
        boolean hasActivePlan =  userMembershipRepository.existsByUserAndIsActiveTrueAndDateTimeAfter(currentUser , LocalDateTime.now());

        if(!hasActivePlan){
            throw new BusinessRuleException("Aktif paketiniz bulunmamaktadır");
        }

        LocalDateTime startOfDay = LocalDateTime.now().with(LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.now().with(LocalDateTime.MAX);

        boolean alreadyCheckIn = checkInRepository.existsByUserAndCheckInTimeBeetween(currentUser, startOfDay, endOfDay);

        if(alreadyCheckIn){
            throw new BusinessRuleException("Hata : Bugün zaten giriş yapıldı");
        }

        CheckIn checkIn = new CheckIn();
        checkIn.setUser(currentUser);
        checkIn.setCheckInTime(LocalDateTime.now());
        checkIn.setCheckOutTime(LocalDateTime.now().plusHours(2));

        CheckIn savedCheckIn = checkInRepository.save(checkIn);
        return modelMapper.map(checkIn,CheckInResponseDto.class);

    }

}
