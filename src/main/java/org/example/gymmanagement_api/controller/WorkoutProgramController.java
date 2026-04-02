package org.example.gymmanagement_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.gymmanagement_api.dto.request.ExerciseRequestDto;
import org.example.gymmanagement_api.dto.request.WorkoutProgramRequestDto;
import org.example.gymmanagement_api.dto.response.ExerciseResponseDto;
import org.example.gymmanagement_api.dto.response.WorkoutProgramResponseDto;
import org.example.gymmanagement_api.service.interfaces.WorkoutProgramService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workout_program")
@RequiredArgsConstructor
public class WorkoutProgramController {
    private final WorkoutProgramService workoutProgramService;

    @PostMapping("/member/{memberId}")
    public ResponseEntity<WorkoutProgramResponseDto> createProgram(
            @PathVariable Long memberId,
            @Valid @RequestBody WorkoutProgramRequestDto requestDto
    ){
        return ResponseEntity.ok(workoutProgramService.createWorkoutProgram(memberId, requestDto));
    }

    @PostMapping("/{programId}/exercises")
    public ResponseEntity<ExerciseResponseDto> addExerciseToProgram(
            @PathVariable Long programId,
            @Valid @RequestBody ExerciseRequestDto requestDto
    ){
        return ResponseEntity.ok(workoutProgramService.addExerciseToProgram(programId, requestDto));
    }
}
