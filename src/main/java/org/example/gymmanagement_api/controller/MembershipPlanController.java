    package org.example.gymmanagement_api.controller;

    import jakarta.validation.Valid;
    import lombok.RequiredArgsConstructor;
    import org.example.gymmanagement_api.dto.request.MembershipPlanRequestDto;
    import org.example.gymmanagement_api.dto.response.MembershipPlanResponseDto;
    import org.example.gymmanagement_api.service.interfaces.MembershipPlanService;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/membershipPlan")
    @RequiredArgsConstructor
    public class MembershipPlanController {

        private final MembershipPlanService membershipPlanService;

        @PostMapping
        public ResponseEntity<MembershipPlanResponseDto> createPlan(@Valid @RequestBody MembershipPlanRequestDto requestDto) {
            MembershipPlanResponseDto responseDto = membershipPlanService.createMembershipPlan(requestDto);

            return ResponseEntity.ok(responseDto);
        }

        @GetMapping()
    }
