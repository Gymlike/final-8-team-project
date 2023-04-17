package com.team.final8teamproject.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequiredArgsConstructor
public class dslcheckController {
    private final DslService dslService;
    @PostMapping("/api/dslrating")
    public void dslRatingRevice(){
        dslService.dslRatingReview();
    }

    @PostMapping("/api/rating")
    public void ratingRevice(){
        dslService.ratingReview();
    }
}
