package org.example.gymmanagement_api.service.interfaces;

import org.example.gymmanagement_api.dto.request.ExerciseRequestDto;
import org.example.gymmanagement_api.dto.request.WorkoutProgramRequestDto;
import org.example.gymmanagement_api.dto.response.ExerciseResponseDto;
import org.example.gymmanagement_api.dto.response.WorkoutProgramResponseDto;

public interface WorkoutProgramService {
    WorkoutProgramResponseDto createWorkoutProgram(Long memberId,WorkoutProgramRequestDto requestDto);
    ExerciseResponseDto addExerciseToProgram(Long prorgramId, ExerciseRequestDto requestDto);
}
