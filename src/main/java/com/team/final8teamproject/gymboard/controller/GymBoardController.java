package com.team.final8teamproject.gymboard.controller;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.base.service.BaseService;
import com.team.final8teamproject.board.dto.ImageNameDTO;
import com.team.final8teamproject.gymboard.dto.*;
import com.team.final8teamproject.gymboard.service.GymPostServiceImpl;
import com.team.final8teamproject.gymboard.service.GymPostServiceImpl.Result;
import com.team.final8teamproject.gymboardreview.dto.GymBoardviewResponseDto;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import com.team.final8teamproject.share.aws_s3.PresignedUrlService;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/gym")
@RequiredArgsConstructor
public class GymBoardController {

    private final GymPostServiceImpl gymPostService;

    private final BaseService baseService;

    private final PresignedUrlService presignedUrlService;

    private String path;
    //1.운동시설 글 작성
    @PostMapping("/owner/write-post")
    public String createGymPost(@RequestBody CreatePostGymRequestDto createPostGymRequestDto,
                                @AuthenticationPrincipal UserDetailsImpl userDetails)  throws NullPointerException, IOException {
        boolean checkUser = baseService.checkUser(userDetails.getUsername());
        if(checkUser){
            String imageUrl = presignedUrlService.findByName(path);
            gymPostService.createGymPost(createPostGymRequestDto, imageUrl,userDetails.getUsername());
            return "작성 성공";
        }else {
            throw new CustomException(ExceptionStatus.WRONG_USERNAME);
        }
    }
 
    //2.유저가하는 작성된 운동시설 조회
    @GetMapping("/all")
    @Cacheable("PostAll")
    public List<GymPostResponseDto> getGymPosts() {
        return gymPostService.getGymPostAll();
    }
    //3. 검색하여 운동시설 페이징 처리 조회
    @GetMapping//
    public Result<List<GymPostResponseDto>> getGymSearchPosts(
            @RequestParam(value = "page",required = false,defaultValue ="1") Integer page,
            @RequestParam(value = "size",required = false,defaultValue = "6") Integer size,//나중에 10
            @RequestParam(value = "isAsc",required = false,defaultValue = "false")Boolean isAsc,
            @RequestParam(value = "sortBy",required = false,defaultValue = "createdDate")String sortBy,
            @RequestParam(value = "search",required = false,defaultValue = "") String search
    ) {
        Pageable pageRequest = getPageable(page, size, isAsc, sortBy);
        return gymPostService.getGymPost(pageRequest,search,size,page);
    }
    //3.유저가하는 작성된 운동시설 하나 조회
    @Cacheable(value = "PostAll", key = "#id")
    @GetMapping("/{id}")//
    public GymPostResponseDetailDto getGymPostDetail(@PathVariable Long id) {
        return gymPostService.getGymPostDetail(id);
    }
    //4.사업자가하는 자기가 작성한 게시글 전체조회
    @GetMapping("/owner/myposts")
    public List<GymBoardviewResponseDto> getAllPosts(@PageableDefault Pageable pageable, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return gymPostService.getAllGymPost(pageable.getPageNumber(), userDetails.getUsername());
    }

    //5.운동시설 글 수정
    @PutMapping("/owner/{id}/putpost")
    public String updateGymPost(@PathVariable Long id, @RequestBody GymUpdateRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        boolean checkUser = baseService.checkUser(userDetails.getUsername());

        if(checkUser){
            String imageUrl ="";
            if(path != null) {
                imageUrl = presignedUrlService.findByName(path);
            }else{
                imageUrl = null;
            }
            gymPostService.updateGymPost(requestDto, imageUrl, userDetails.getUsername(), id);
            return "작성 성공";
        }else {
            throw new CustomException(ExceptionStatus.WRONG_USERNAME);
        }
    }

    //6.운동시설 글 삭제
    @DeleteMapping("/owner/{id}/delete-post")
    public String deleteGymPost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return gymPostService.deleteGymPost(id, userDetails.getUsername());
    }
    //7. 할인 적용을 위한 컨트롤러
    @PutMapping("/owner/{id}/discount")
    public String discountUpdate(@PathVariable Long id,
                                 @RequestBody  GymOneStringRequestDto requestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails){
        return gymPostService.discountUpdate(id, requestDto.getOneString() ,userDetails.getUsername());
    }
    //8.가격 변경을 위한 컨트롤러
    @PutMapping("/owner/{id}/update-price")
    public String priceUpdate(@PathVariable Long id,
                              @RequestBody  GymOneStringRequestDto requestDto,
                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        return gymPostService.priceUpdate(id, requestDto.getOneString(), userDetails.getUsername());
    }
    //9.가격 변경을 위한 컨트롤러
    @PutMapping("/owner/{id}/delete-discount")
    public String discountDelete(@PathVariable Long id,
                              @RequestBody  GymOneStringRequestDto requestDto,
                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        return gymPostService.priceUpdate(id, requestDto.getOneString(), userDetails.getUsername());
    }
    //10. 이미지 변경을 위한 컨트롤러
    @PutMapping("/owner/{id}/update-image")
    public String imageUpdate(@PathVariable Long id,
                              @RequestBody  GymOneStringRequestDto requestDto,
                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        return gymPostService.imageUpdate(id, requestDto.getOneString(), userDetails.getUsername());

    }

    // 페이징 처리를 위한 메소드 나중에 통합해야함
    private static Pageable getPageable(Integer page, Integer size, Boolean isAsc, String sortBy) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC:Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        if (page<0){
            page=1;
        }
        return PageRequest.of(page-1, size,sort);
    }

    //S3 pre-sing url 사용을 위한 메소드
    @PostMapping("/pre-signed")
    public String creatPresigned(@RequestBody ImageNameDTO imageNameDTO,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {
        BaseEntity base = userDetails.getBase();
        boolean checkUser = baseService.checkUser(base.getUsername());

        if(checkUser){
            path ="facilities";
            String imageName = imageNameDTO.getImageName();
            return presignedUrlService.getPreSignedUrl(path, imageName);
        }else {
            throw new CustomException(ExceptionStatus.WRONG_USERNAME);
        }
    }
}
