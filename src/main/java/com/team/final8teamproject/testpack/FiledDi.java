package com.team.final8teamproject.testpack;

import com.team.final8teamproject.owner.service.OwnerService;
import com.team.final8teamproject.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FiledDi {
    @Autowired
    private UserService userService;

    @Autowired
    private OwnerService ownerService;
}
