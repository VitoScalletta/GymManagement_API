package org.example.gymmanagement_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.gymmanagement_api.dto.request.ExerciseRequestDto;
import org.example.gymmanagement_api.dto.request.WorkoutProgramRequestDto;
import org.example.gymmanagement_api.dto.response.ExerciseResponseDto;
import org.example.gymmanagement_api.dto.response.WorkoutProgramResponseDto;
import org.example.gymmanagement_api.entity.Exercise;
import org.example.gymmanagement_api.entity.Role;
import org.example.gymmanagement_api.entity.User;
import org.example.gymmanagement_api.entity.WorkoutProgram;
import org.example.gymmanagement_api.exception.BusinessRuleException;
import org.example.gymmanagement_api.exception.ResourceNotFoundException;
import org.example.gymmanagement_api.repository.ExerciseRepository;
import org.example.gymmanagement_api.repository.UserRepository;
import org.example.gymmanagement_api.repository.WorkoutProgramRepository;
import org.example.gymmanagement_api.service.interfaces.UserService;
import org.example.gymmanagement_api.service.interfaces.WorkoutProgramService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkoutProgramServiceImpl implements WorkoutProgramService {
    private final WorkoutProgramRepository workoutProgramRepository;
    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public WorkoutProgramResponseDto createWorkoutProgram(Long memberId, WorkoutProgramRequestDto requestDto) {
        User currentUser = userService.getCurrenUser();
        if (currentUser.getRole() != Role.TRAINER) {
            throw new BusinessRuleException("Antreman Programı oluşturamazsın");
        }

        User member = userRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Aradığınız üye bulunamadı"));
        WorkoutProgram workoutProgram = modelMapper.map(requestDto, WorkoutProgram.class);
        workoutProgram.setUser(member);
        WorkoutProgram savedProgram = workoutProgramRepository.save(workoutProgram);
        return modelMapper.map(workoutProgram , WorkoutProgramResponseDto.class);
    }

    @Override
    public ExerciseResponseDto addExerciseToProgram(Long prorgramId, ExerciseRequestDto requestDto) {
        User CurrentUSer = userService.getCurrenUser();
        if (CurrentUSer.getRole() != Role.TRAINER) {
            throw new BusinessRuleException("Programlara egzeriz ekleyemessin");
        }
        WorkoutProgram program = workoutProgramRepository.findById(prorgramId)
                .orElseThrow(()-> new ResourceNotFoundException("Program bulunamadı"));
        Exercise exercise = modelMapper.map(requestDto, Exercise.class);
        exercise.setWorkoutProgram(program);
        Exercise savedExercise = exerciseRepository.save(exercise);
        return modelMapper.map(savedExercise, ExerciseResponseDto.class);

    }
}
