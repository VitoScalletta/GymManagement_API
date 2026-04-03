package org.example.gymmanagement_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.gymmanagement_api.dto.request.UserRequestDto;
import org.example.gymmanagement_api.dto.response.CheckInResponseDto;
import org.example.gymmanagement_api.service.interfaces.CheckInService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/check-ins")
@RequiredArgsConstructor
public class CheckInController {
    private final CheckInService checkInService;

    @PostMapping
    public ResponseEntity<CheckInResponseDto> doCheckIn() {
        return ResponseEntity.ok(checkInService.doCheckIn());
    }
}
