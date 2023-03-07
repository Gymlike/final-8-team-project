package com.team.final8teamproject.share.aws_s3;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
public class PEupdateDto {

    private final String  filepath;

    private final String objectName;

    public PEupdateDto(String filepath, String objectName) {
        this.filepath = filepath;
        this.objectName = objectName;
    }
}
