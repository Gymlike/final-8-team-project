package com.team.final8teamproject.owner.service;

import com.team.final8teamproject.owner.dto.TrainerRequestDto;
import com.team.final8teamproject.owner.dto.TrainerResponseDto;
import com.team.final8teamproject.owner.entity.Owner;
import com.team.final8teamproject.owner.entity.Trainer;
import com.team.final8teamproject.owner.repository.TrainerRepository;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import com.team.final8teamproject.user.dto.MessageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainerService {
    private final TrainerRepository trainerRepository;
    private final OwnerService ownerService;

    //1. 트레이너 등록
    @Transactional
    public MessageResponseDto createTrainer(Long id, TrainerRequestDto trainerRequestDto) {
        Owner owner = ownerService.getOwnerById(id);
        Trainer trainer = Trainer.builder()
                .trainername(trainerRequestDto.getTrainername())
                .storeName(trainerRequestDto.getStoreName())
                .contents(trainerRequestDto.getContents())
                .image(trainerRequestDto.getImage())
                .build();
        trainer.setOwner(owner);

        trainerRepository.save(trainer);
        return new MessageResponseDto("등록 완료");
    }

    //2. 트레이너 수정
    @Transactional
    public TrainerResponseDto updateTrainer(Long id, TrainerRequestDto trainerRequestDto, UserDetailsImpl userDetails) {
        Trainer trainer = getTrainerByIdAndOwnerId(id, userDetails.getBase().getId());
        trainer.updateTrainer(trainerRequestDto);
        return TrainerResponseDto.of(trainer);
    }


    //3. 트레이너 삭제
    @Transactional
    public void deleteTrainer(Long id, UserDetailsImpl userDetails) {
        Trainer trainer = getTrainerByIdAndOwnerId(id, userDetails.getBase().getId());
        trainerRepository.delete(trainer);
    }

    //4. 트레이너 조회
    @Transactional(readOnly = true)
    public Page<TrainerResponseDto> getTrainersByStoreName(String storeName, Pageable pageable) {
        Page<Trainer> trainers = trainerRepository.findAllByStoreName(storeName, pageable);
        return trainers.map(TrainerResponseDto::of);
    }

    @Transactional(readOnly = true)
    public Trainer getTrainerByIdAndOwnerId(Long id, Long ownerId) {
        Optional<Trainer> optionalTrainer = trainerRepository.findByIdAndOwnerId(id, ownerId);
        if (optionalTrainer.isEmpty()) {
            throw new IllegalArgumentException("사용자가 존재하지 않습니다.");
        }
        return optionalTrainer.get();
    }


}
