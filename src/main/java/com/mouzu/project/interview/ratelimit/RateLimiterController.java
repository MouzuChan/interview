package com.mouzu.project.interview.ratelimit;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class RateLimiterController {

    @RateLimiterAnnotation(setLimiterValue = 0.1, timeOut = 200, timeUnit = TimeUnit.MILLISECONDS, msg = "服务器繁忙,请稍后再试")
    @PostMapping("/RateLimiter/test")
    public String test() {
        return "";
    }
}
