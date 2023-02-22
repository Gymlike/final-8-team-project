package com.team.final8teamproject.share.aws_s3;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;




@RequiredArgsConstructor
@Component
public class S3Uploader {
    private final AmazonS3 amazonS3Client;

    //@Value("${cloud.aws.s3.bucket}")
    @Value("${cloud.aws.s3.bucket}")
    public String bucket;


    public  String uploadOne(MultipartFile multipartFile,String dir) throws IOException{
        String imgPath;

        String fileName = creatFileName()+multipartFile.getName();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        try(InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3Client.putObject(
                    new PutObjectRequest(bucket+dir,fileName,inputStream,objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead));
            imgPath = amazonS3Client.getUrl(bucket+dir,fileName).toString();
        }catch (IOException e){
            throw new IOException();
        }
        return imgPath;
    }

    public void deleteFile(String imagePathLong){
        try {
            String imagePath = imagePathLong.substring(54);
            amazonS3Client.deleteObject(
                    new DeleteObjectRequest(bucket,imagePath));
        }catch (
                AmazonS3Exception e){
            e.printStackTrace();
        }
    }
    public String creatFileName() throws IOException{
        return UUID.randomUUID().toString();//.concat(getFileExtension(fileName));
    }

    public String getFileExtension(String fileName) throws  IOException{
        if (fileName.length() == 0) {
            throw new IOException();
        }

        ArrayList<String> fileExtensionValidate = new ArrayList<>();
        fileExtensionValidate.add(".jpg");
        fileExtensionValidate.add(".jpeg");
        fileExtensionValidate.add(".png");
        fileExtensionValidate.add(".JPG");
        fileExtensionValidate.add(".JPEG");
        fileExtensionValidate.add(".PNG");

        String fileExtension = fileName.substring(fileName.lastIndexOf("."));

        if(!fileExtensionValidate.contains(fileExtension)){
            throw new CustomException(ExceptionStatus.IS_NOT_CORRECT_FORMAT);
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }

}