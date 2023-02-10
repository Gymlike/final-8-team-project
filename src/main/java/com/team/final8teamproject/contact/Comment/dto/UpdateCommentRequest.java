package com.team.final8teamproject.contact.Comment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class UpdateCommentRequest {

    private final String comments;

}
