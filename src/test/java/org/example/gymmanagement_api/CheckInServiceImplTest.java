package org.example.gymmanagement_api;

import org.example.gymmanagement_api.dto.response.CheckInResponseDto;
import org.example.gymmanagement_api.entity.CheckIn;
import org.example.gymmanagement_api.entity.User;
import org.example.gymmanagement_api.exception.BusinessRuleException;
import org.example.gymmanagement_api.repository.CheckInRepository;
import org.example.gymmanagement_api.repository.UserMembershipRepository;
import org.example.gymmanagement_api.service.impl.CheckInServiceImpl;
import org.example.gymmanagement_api.service.interfaces.CheckInService;
import org.example.gymmanagement_api.service.interfaces.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckInServiceImplTest {

    @Mock
    private CheckInRepository checkInRepository;
    @Mock
    private UserMembershipRepository userMembershipRepository;
    @Mock
    private UserService userService;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private CheckInServiceImpl checkInService;
    private User mockUser;

    @BeforeEach
    void setUp() {
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setFirstName("Ali");
        mockUser.setLastName("Yılmaz");
    }

    @Test
    void doCheckIn_Succesfully_WhenUserHasActivePlanAndNotCheckedInToday() {
        when(userService.getCurrenUser()).thenReturn(mockUser);

        when(userMembershipRepository.existsByUserAndIsActiveTrueAndEndDateTimeAfter(eq(mockUser),any(LocalDateTime.class)))
                .thenReturn(true);

        when(checkInRepository.existsByUserAndCheckInTimeBetween(eq(mockUser),any(LocalDateTime.class),any(LocalDateTime.class)))
        .thenReturn(false);

        CheckIn mockCheckIn = new CheckIn();
        when(checkInRepository.save(any(CheckIn.class))).thenReturn(mockCheckIn);

        CheckInResponseDto mockResponse = new CheckInResponseDto();
        mockResponse.setUserFirstName("Ali");
        when(modelMapper.map(mockCheckIn, CheckInResponseDto.class)).thenReturn(mockResponse);

        CheckInResponseDto responseDto = checkInService.doCheckIn();

        assertNotNull(responseDto);
        assertEquals("Ali", responseDto.getUserFirstName());
        verify(checkInRepository, times(1)).save(any(CheckIn.class));

    }
    @Test
    void doCheckIn_ThrowsException_WhenNoActivePlan() {
        when(userService.getCurrenUser()).thenReturn(mockUser);
        when(userMembershipRepository.existsByUserAndIsActiveTrueAndEndDateTimeAfter(eq(mockUser),any(LocalDateTime.class)))
                .thenReturn(false);

        BusinessRuleException exception = assertThrows(BusinessRuleException.class, () -> checkInService.doCheckIn());
        assertEquals("Aktif paketiniz bulunmamaktadır", exception.getMessage());

        verify(checkInRepository, never()).save(any(CheckIn.class));
    }

    @Test
    void doCheckIn_ThrowsException_WhenAlreadyCheckedInToday() {
        when(userService.getCurrenUser()).thenReturn(mockUser);

        when(userMembershipRepository.existsByUserAndIsActiveTrueAndEndDateTimeAfter(eq(mockUser),any(LocalDateTime.class)))
        .thenReturn(true);

        when(checkInRepository.existsByUserAndCheckInTimeBetween(eq(mockUser),any(LocalDateTime.class),any(LocalDateTime.class)))
                .thenReturn(true);

        BusinessRuleException exception = assertThrows(BusinessRuleException.class, () -> checkInService.doCheckIn());
        assertEquals("Hata : Bugün zaten giriş yapıldı", exception.getMessage());
        verify(checkInRepository, never()).save(any(CheckIn.class));
    }
}
