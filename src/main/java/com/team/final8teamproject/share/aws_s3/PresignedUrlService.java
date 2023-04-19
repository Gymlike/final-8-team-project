package com.team.final8teamproject.share.aws_s3;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class PresignedUrlService {
    private final AmazonS3 amazonS3;

    private String useOnlyOneFileName;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String location;


    public String getPreSignedUrl(String prefix, String fileName) {

        String onlyOneFileName = onlyOneFileName(fileName);

        useOnlyOneFileName = onlyOneFileName;

        if (!prefix.equals("")) {
            onlyOneFileName = prefix + "/" + onlyOneFileName;
        }
        GeneratePresignedUrlRequest generatePresignedUrlRequest = getGeneratePreSignedUrlRequest(bucket, onlyOneFileName);

        return amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString();
    }

    public String updatePreSignedurl(String prefix, String fileName) {

        String newFilename;
        String fixpath =   "8projectFront/";
        if (!prefix.equals("")) {
           newFilename =fixpath+ prefix + "/" + fileName;
        }else {
            newFilename=fixpath+fileName;
        }

        GeneratePresignedUrlRequest generatePresignedUrlRequest = getGeneratePreSignedUrlRequest(bucket, newFilename);

        return amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString();
    }
    private GeneratePresignedUrlRequest getGeneratePreSignedUrlRequest(String bucket, String fileName) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucket, fileName)//버킷에 들어가는 객체의 이름
                        .withMethod(HttpMethod.PUT)
                        .withExpiration(getPreSignedUrlExpiration());
        generatePresignedUrlRequest.addRequestParameter(
                Headers.S3_CANNED_ACL,
                CannedAccessControlList.PublicRead.toString());
        return generatePresignedUrlRequest;
    }

    //getPreSignedUrl 응답 시간 제한 현재 2분
    private Date getPreSignedUrlExpiration() {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 2;
        expiration.setTime(expTimeMillis);
        log.info(expiration.toString());
        return expiration;
    }

    private String onlyOneFileName(String filename){
        return UUID.randomUUID().toString()+filename;
    }

    public String findByName(String path) {
//        if (!amazonS3.doesObjectExist(bucket,editPath+ useOnlyOneFileName))
//            return "File does not exist";
        log.info("Generating signed URL for file name {}", useOnlyOneFileName);
//        return  amazonS3.getUrl(bucket,editPath+useOnlyOneFileName).toString();
        return "https://"+bucket+".s3."+location+".amazonaws.com/"+path+"/"+useOnlyOneFileName;
    }

}

