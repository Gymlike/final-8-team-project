package com.team.final8teamproject.gymboard.service;

import com.team.final8teamproject.gymboard.dto.CreatePostGymRequestDto;
import com.team.final8teamproject.gymboard.entity.Amenities;
import com.team.final8teamproject.gymboard.entity.GymBoard;
import com.team.final8teamproject.gymboard.repository.GymAmenitiesRepository;
import com.team.final8teamproject.gymboard.repository.GymBoardRepository;
import com.team.final8teamproject.gymboardreview.repository.GymReviewRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.DisplayName;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GymPostServiceImplTest {

    @InjectMocks
    private GymPostServiceImpl gymPostServiceImpl;
    @Mock
    private GymBoardRepository gymBoardRepository;
    @Mock
    private GymReviewRepository gymBoardReviewRepository;
    @Mock
    private GymAmenitiesRepository amenitiesRepository;
    private static ValidatorFactory factory;
    private static Validator validator;

    private static Amenities amenities;
    @BeforeEach
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        amenities = Amenities.builder()
                .parkingLot(true)
                .build();
        amenitiesRepository.save(amenities);
    }
    @Test
    @DisplayName("게시글 등록 성공 테스트")
    void createGymPostSuccess() {
        // given
        CreatePostGymRequestDto gymPostRequestDto =
                new CreatePostGymRequestDto("제목",
                        "내용", "112",
                        "체육관 이름", "지역",
                        "1",amenities,"3","3");

        GymBoard gymBoard = GymBoard.CreateGymBoard()
                .requestDto(gymPostRequestDto)
                .username("owner1")
                .imgUrl("test1")
                .build();

        when(gymBoardRepository.save(any(GymBoard.class))).thenReturn(gymBoard);
        // when
        gymPostServiceImpl.createGymPost(gymPostRequestDto,
                "없음","member1");
        // then
        verify(gymBoardRepository, times(1)).save(any(GymBoard.class));
    }
//    @Test
//    @DisplayName("게시글 등록 실패 테스트 - 필수값 미입력(공백)")
//    void createGymPostFailBlack() {
//        // given
//        CreatePostGymRequestDto gymPostRequestDto =
//                new CreatePostGymRequestDto("", "내용", "작성자 아이디",
//                        "체육관 이름", "지역",
//                        "시설1", amenities, "시설3", "시설4");
//        // when
//        Set<ConstraintViolation<CreatePostGymRequestDto>> violation =
//                validator.validate(gymPostRequestDto);
//        //then
//        assertThat(violation).isNotEmpty();
//        violation
//                .forEach(error -> {
//                    assertThat(error.getMessage()).isEqualTo("공백일 수 없습니다");
//                });
//    }
//    @Test
//    @DisplayName("게시글 등록 실패 테스트 - 필수값 미입력(null)")
//    void createGymPostFailNull() {
//        // given
//        CreatePostGymRequestDto gymPostRequestDto =
//                new CreatePostGymRequestDto(null, "내용", "작성자 아이디",
//                        "체육관 이름", "지역",
//                        "시설1", amenities, "시설3", "시설4");
//        // when
//        Set<ConstraintViolation<CreatePostGymRequestDto>> violation =
//                validator.validate(gymPostRequestDto);
//        //then
//        assertThat(violation).isNotEmpty();
//        violation
//                .forEach(error -> {
//                    assertThat(error.getMessage()).isEqualTo("널이어서는 안됩니다");
//                });
//    }


}
