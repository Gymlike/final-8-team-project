package com.team.final8teamproject.amp.info;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

/**
Actuator을 이용한 이 프로젝트에대한 정보를 보여주고 싶을때 작성함
 */
@Component
public class MyInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        
        builder.withDetail("name", "SearchGym")
                .withDetail("version", "1.0.0")
                .withDetail("descriptionEnglish", "The project is designed to help people who are interested in sports facilities, and sports facility officials also have a function to inform the facility and promote it so that many people can solve their questions and reduce time waste.")
        .withDetail("descriptionKorean", "이 사이트는 체육시설에 관심이 있는 사람들을 돕기 위해 마련된 것으로 체육시설 관계자들도 시설을 알리고 홍보하는 기능을 갖춰 많은 사람들이 궁금증을 해소하고 시간 낭비를 줄일 수 있도록 했습니다.");
    }
}
