package com.team.final8teamproject.board.dto;


import com.team.final8teamproject.board.service.T_exerciseServiceImple;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class ResultDTO implements Serializable {


  private final T_exerciseServiceImple.Result result;


    public ResultDTO(T_exerciseServiceImple.Result result) {
        this.result = result;
    }
}
