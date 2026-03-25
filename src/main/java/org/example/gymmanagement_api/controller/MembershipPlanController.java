    package org.example.gymmanagement_api.controller;

    import jakarta.validation.Valid;
    import lombok.RequiredArgsConstructor;
    import org.example.gymmanagement_api.dto.request.MembershipPlanRequestDto;
    import org.example.gymmanagement_api.dto.response.MembershipPlanResponseDto;
    import org.example.gymmanagement_api.entity.MembershipPlan;
    import org.example.gymmanagement_api.service.interfaces.MembershipPlanService;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

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

        @GetMapping
        public ResponseEntity<List<MembershipPlanResponseDto>> getAllMembershipPlans() {
            List<MembershipPlanResponseDto> response = membershipPlanService.getAllMembershipPlan();
            return ResponseEntity.ok(response);
        }

        @GetMapping("/{id}")
        public ResponseEntity<MembershipPlanResponseDto> getMembershipPlanById(@PathVariable Long id) {
            MembershipPlanResponseDto responseDto = membershipPlanService.getMembershipPlanById(id);
            return ResponseEntity.ok(responseDto);
        }

        @PutMapping("/{id}")
        public ResponseEntity<MembershipPlanResponseDto> updateMembershipPlanById(
                @PathVariable Long id,
                @Valid @RequestBody MembershipPlanRequestDto requestDto)
        {
            MembershipPlanResponseDto updated = membershipPlanService.updateMembershipPlan(id,requestDto);
            return ResponseEntity.ok(updated );
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<MembershipPlanResponseDto> deleteMembershipPlanById(@PathVariable Long id) {
            membershipPlanService.deleteMembershipPlan(id);
            return ResponseEntity.noContent().build();
        }
    }
