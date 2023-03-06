package com.team.final8teamproject.share.aws_s3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/presiged")
public class PresignedUrlController {


    private final PresignedUrlService presignedUrlService;
    //html파일 업데이트하기

    @PostMapping
    public String updateHTML(@RequestBody PEupdateDto pEupdateDto){

        String filepath = pEupdateDto.getFilepath();
        String objectName = pEupdateDto.getObjectName();

     return presignedUrlService.getPreSignedUrl(filepath,objectName);
    }






}
