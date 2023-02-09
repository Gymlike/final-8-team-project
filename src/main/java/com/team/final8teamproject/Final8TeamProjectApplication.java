package com.team.final8teamproject;

import com.team.final8teamproject.manager.entity.GeneralManager;
import com.team.final8teamproject.manager.entity.ManagerRoleEnum;
import com.team.final8teamproject.manager.repository.GeneralManagerRepository;
import com.team.final8teamproject.manager.service.GeneralManagerService;
import com.team.final8teamproject.manager.service.GeneralManagerServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@SpringBootApplication
@EnableJpaAuditing
public class Final8TeamProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Final8TeamProjectApplication.class, args);

//        ManagerRoleEnum roll = ManagerRoleEnum.GeneralManager;
//        GeneralManager generalManager = GeneralManager.builder()
//                .generalName("Test1").password("Testpassword1").nickname("TestGName")
//                .roll(roll).build();
//        GeneralManagerService.save(generalManager);
    }

}
