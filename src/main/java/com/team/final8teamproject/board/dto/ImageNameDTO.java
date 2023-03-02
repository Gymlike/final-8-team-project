package com.team.final8teamproject.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
public class ImageNameDTO {

  private final String imageName;

    public ImageNameDTO(String imageName) {
        this.imageName = imageName;
    }
}
