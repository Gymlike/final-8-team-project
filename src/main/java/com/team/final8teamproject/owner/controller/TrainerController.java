package com.team.final8teamproject.owner.controller;

import com.team.final8teamproject.owner.dto.TrainerRequestDto;
import com.team.final8teamproject.owner.dto.TrainerResponseDto;
//import com.team.final8teamproject.owner.service.TrainerService;
import com.team.final8teamproject.owner.service.TrainerService;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import com.team.final8teamproject.user.dto.MessageResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainers")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    //1.트레이너 등록
    @PostMapping("")
    public MessageResponseDto createTrainer(@RequestBody @Valid TrainerRequestDto trainerRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return trainerService.createTrainer(userDetails.getBase().getId(), trainerRequestDto);
    }

    //2.트레이너 수정
    @PutMapping("/{id}")
    public TrainerResponseDto updateTrainer(@PathVariable Long id, @RequestBody TrainerRequestDto trainerRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return trainerService.updateTrainer(id, trainerRequestDto, userDetails);
        //3.트레이너 삭제

        //4.트레이너 조회
        //5.트레이너 상세조회
    }

    //3. 트레이너 삭제
    @DeleteMapping("/{id}")
    public void deleteTrainer(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        trainerService.deleteTrainer(id, userDetails);
    }

    //4.트레이너 조회
    @GetMapping("")
    public Page<TrainerResponseDto> getTrainersByStoreName(@RequestParam(name = "storeName") String storeName,
                                                @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                @RequestParam(value = "size", required = false, defaultValue = "2") Integer size,//나중에 10
                                                @RequestParam(value = "isAsc", required = false, defaultValue = "false") Boolean isAsc,
                                                @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                                @RequestParam(value = "search", required = false, defaultValue = "") String search
    ) {
        Pageable pageRequest = getPageable(page, size, isAsc, sortBy);
        return trainerService.getTrainersByStoreName(storeName, pageRequest);
    }

    private static Pageable getPageable(Integer page, Integer size, Boolean isAsc, String sortBy) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        if (page < 0) {
            page = 1;
        }
        return PageRequest.of(page - 1, size, sort);
    }
}
