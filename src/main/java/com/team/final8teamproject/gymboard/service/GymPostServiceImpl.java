package com.team.final8teamproject.gymboard.service;

import com.team.final8teamproject.gymboard.dto.*;
import com.team.final8teamproject.gymboard.entity.Amenities;
import com.team.final8teamproject.gymboard.entity.GymBoard;
import com.team.final8teamproject.gymboard.repository.GymAmenitiesRepository;
import com.team.final8teamproject.gymboard.repository.GymBoardRepository;
import com.team.final8teamproject.gymboardreview.dto.GymBoardReviewResponseDto;
import com.team.final8teamproject.gymboardreview.dto.GymBoardviewResponseDto;
import com.team.final8teamproject.gymboardreview.repository.GymReviewRepository;
import com.team.final8teamproject.gymboardreview.service.GymBoardReviewServiceImpl;
import com.team.final8teamproject.redis.cache.CacheNames;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GymPostServiceImpl implements GymPostService {
    private final GymBoardRepository gymBoardRepository;
    private final GymBoardReviewServiceImpl gymBoardReviewServiceImpl;
    private final GymReviewRepository gymReviewRepository;
    private final GymAmenitiesRepository gymAmenitiesRepository;

    //1.헬스장 게시글 작성
    @CacheEvict(cacheNames = CacheNames.ALLEXCERCIES, key = "'SimpleKey [Page request [number: 0, size 6, sort: createdDate: DESC], 헬스장,6,1]'")
    @Override
    public String createGymPost(CreatePostGymRequestDto requestDto,
                                String imgUrl,
                                String username){
        GymBoard gymBoard = GymBoard.CreateGymBoard()
                .requestDto(requestDto)
                .imgUrl(imgUrl)
                .username(username)
                .build();
        gymBoardRepository.save(gymBoard);
        return "등록 완료";
    }

    public void createAmenities(Amenities amenities){
        gymAmenitiesRepository.save(amenities);
    }

    //2. 메인 페이지 보여주기
    @Override
    public List<GymPostResponseDto> getGymPostAll() {
        List<GymBoard> gymBoards = gymBoardRepository.findAll();
        List<GymPostResponseDto> gymPostResponseDetailDtosList = new ArrayList<>();
        for (GymBoard gymBoard : gymBoards) {
            GymPostResponseDto gymPostResponseDetailDto = new GymPostResponseDto(gymBoard);
            gymPostResponseDetailDtosList.add(gymPostResponseDetailDto);
        }
        return gymPostResponseDetailDtosList;
    }

    //3. 작성된 운동시설 보여주기
    @Override
    @Cacheable(cacheNames = CacheNames.ALLEXCERCIES)
    public gymResult<List<GymPostResponseDto>> getGymPost(Pageable pageRequest,
                                                          String search,
                                                          Integer size,
                                                          Integer page) {
        Page<GymBoard> gymBoards = gymBoardRepository.findByTitleContainingIgnoreCaseAndInLiveTrue(search, pageRequest);
        int totalCount = (int) gymBoards.getTotalElements();
        Long countList = size.longValue();
        int countPage = 5;//리펙토링때 10으로변경합세!
        int totalPage = (int) (totalCount / countList);

        if (totalCount % countList > 0) {
            totalPage++;
        }

        if (totalPage < page) {
            page = totalPage;
        }

        List<GymPostResponseDto> gymPostResponseDetailDtosList = new ArrayList<>();
        for (GymBoard gymBoard : gymBoards) {
            GymPostResponseDto gymPostResponseDetailDto = new GymPostResponseDto(gymBoard);
            gymPostResponseDetailDtosList.add(gymPostResponseDetailDto);
        }
        return new gymResult<List<GymPostResponseDto>>(page, totalCount, countPage, totalPage, gymPostResponseDetailDtosList);
    }
    //4. 작성한 운동시설 선택시 세부사항
    /*
        해야되는거
        현재 GymBoard랑 GymReview 만 되어있는데
        QueryDsl이나 jquery 이용해서 Amenities랑 Trainer를 추가해서
        N+1문제없이 조회되게 만들면 됩니다.

        그러니까 지금현재는 전체 운동페이지에서 클릭시 상세페이지로 이동하게 되는데
        거기에서 gymboardRepository테이블의 정보와 gymreviewRepository테이블의정보만을
        읽어와서 유저에게 전송해줍니다.
        한번 읽어보면서 정리하시길.

        그것에서 이제 AmenitiesRepository와 TrainerRepository(트레이너테이블)의 정보들도
        읽어와서
        이제 2개가 아닌 4개의 테이블을 읽어오는데 그냥 읽어오면
        하나씩 하나씩 join타고 들어가면서 N+1문제가 발생하니 한번에 읽어오게
        QueryDSL을 이용하여 N+1문제 해결과 QueryDsl 사용에대하여 습득해보시라는 내용입니다.

     */
    @Override
    @Cacheable(cacheNames = CacheNames.SELECTGYMBOARD, key = "#id", unless = "#result == null")
    @Transactional(readOnly = true)
    public GymPostResponseDetailDto getGymPostDetail(Long id) {
        GymBoard gymBoards = gymBoardRepository.findByIdWithAmenities(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 삭제되어 찾을수 없습니다.")
        );
        List<GymBoardReviewResponseDto> review = gymBoardReviewServiceImpl.getReview(gymBoards.getId());
        return new GymPostResponseDetailDto(gymBoards, review);
    }
    //5. 자기가 작성한 운동시설 전부 조회
    @Override
    public List<GymBoardviewResponseDto> getAllGymPosts(int pageChoice, String username){
        Page<GymBoard> gymBoards = gymBoardRepository.findByUsername(pageableGymPostSetting(pageChoice),username);
        return gymBoards.stream().map(GymBoardviewResponseDto::new).collect(Collectors.toList());
    }
    //5-1. 페이징 처리 메소드
    private Pageable pageableGymPostSetting(int pageChoice){
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = Sort.by(direction,"id");
        return PageRequest.of(pageChoice -1,9,sort);
    }
    //6. 헬스장 게시글 수정
    @Override
    @CacheEvict(cacheNames = CacheNames.SELECTGYMBOARD, key = "#id")
    public void updateGymPost(GymUpdateRequestDto requestDto,
                              String imageUrl,
                              String username,
                              Long id) {
        GymBoard gymBoard = gymBoardRepository.findByIdAndUsername(id, username).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        gymBoard.update(requestDto, imageUrl);
    }

    //7. 헬스장 게시글 삭제
    @Override
    @CacheEvict(cacheNames = CacheNames.SELECTGYMBOARD, key = "#id")
    @Transactional
    public String deleteGymPost(Long id, String username) {
        GymBoard gymBoard = gymBoardRepository.findByIdAndUsername(id, username).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        if(gymBoard.isInLive()){
            gymBoard.changeLive(false);
        }
//        gymBoardRepository.deleteById(gymBoard.getId());
//        gymReviewRepository.deleteByGymId(gymBoard.getId());
        return "삭제완료";
    }

    //8.가격 할인 업데이트
    @Override
    public String discountUpdate(Long id, String discount, String username){
        GymBoard gymBoard = gymBoardRepository.findByIdAndUsername(id, username).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        gymBoard.discountUpdate(discount);
        return "할인적용";
    }
    //8.가격 할인 업데이트
    @Override
    public String deleteUpdate(Long id, String discount, String username){
        GymBoard gymBoard = gymBoardRepository.findByIdAndUsername(id, username).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        gymBoard.discountDelete(discount);
        return "할인적용";
    }
    //10. 가격 변경
    public String priceUpdate(Long id, String price, String username){
        GymBoard gymBoard = gymBoardRepository.findByIdAndUsername(id, username).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        gymBoard.updatePrice(price);
        return "변경완료";
    }
    public String imageUpdate(Long id, String imageUrl, String username){
        GymBoard gymBoard = gymBoardRepository.findByIdAndUsername(id, username).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        gymBoard.updatePrice(imageUrl);
        return "변경완료";
    }

    /*
        A요청 ->    A에대한대답 ->B에대한 대답 ->A에게 전달
                    ↑   ↓
                   B요청들옴
     */
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class gymResult<T> implements Serializable {
        private int page;
        private int totalCount;
        private int countPage;
        private int totalPage;
        private T data;

        public gymResult(int page, int totalCount, int countPage, int totalPage, T data) {
            this.page = page;
            this.totalCount = totalCount;
            this.countPage = countPage;
            this.totalPage = totalPage;
            this.data = data;
        }
    }
}