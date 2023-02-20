package com.team.final8teamproject.base.service;


import com.team.final8teamproject.base.repository.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BaseServiceImple implements BaseService {


    private final BaseRepository baseRepository;

    @Override
    public boolean checkUser(String userName){
        return baseRepository.existsByUsername(userName);
    }
}
