package com.team.final8teamproject;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

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
