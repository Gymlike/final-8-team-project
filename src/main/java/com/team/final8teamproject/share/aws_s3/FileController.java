package com.team.final8teamproject.share.aws_s3;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;


import java.util.Map;

@RestController
@RequestMapping("/api/v1/files")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class FileController {
    private static final String FILE_NAME = "fileName";

    private  final PresignedUrlService presignedUrlService;



//    @GetMapping
//    public ResponseEntity<Object> findByName(@RequestBody(required = false) Map<String, String> params) {
//        final String path = request.getServletPath();
//        if (params.containsKey(FILE_NAME))
//            return new ResponseEntity<>(presignedUrlService.findByName(params.get(FILE_NAME));
//
//        return null;
//    }

    @PostMapping
    public ResponseEntity<Object> saveFile(@RequestParam("image") String image) {
        String path ="test";
        return new ResponseEntity<>(presignedUrlService.getPreSignedUrl(path,image), HttpStatus.OK);
    }

}
