package com.mouzu.project.interview.ratelimit;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@Component
public class RateLimiterInterceptor implements HandlerInterceptor {
    /**
     * 不同的方法存放不同的令牌桶
     */
    private final Map<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        PrintWriter out = null;
        try {
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                RateLimiterAnnotation rateLimit = handlerMethod.getMethodAnnotation(RateLimiterAnnotation.class);
                // 判断是否有注解
                if (rateLimit != null) {
                    // 获取请求url
                    String url = request.getRequestURI();
                    RateLimiter rateLimiter;
                    // 判断map集合中是否有创建好的令牌桶
                    if (!rateLimiterMap.containsKey(url)) {
                        // 创建令牌桶,以n r/s往桶中放入令牌
                        rateLimiter = RateLimiter.create(rateLimit.setLimiterValue());
                        rateLimiterMap.put(url, rateLimiter);
                    }
                    rateLimiter = rateLimiterMap.get(url);
                    // 获取令牌
                    boolean acquire = rateLimiter.tryAcquire(rateLimit.timeOut(), rateLimit.timeUnit());
                    if (acquire) {
                        return true;
                    } else {
                        System.out.println("请求被限流,url:"+request.getServletPath());
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("application/json; charset=utf-8");
                        out = response.getWriter();
                        out.println("{\"status\":" + 500 + " ,\"message\" :\"" + rateLimit.msg() + "\"}");
                        return false;

                    }
                }
            }
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("请求url:{}" + request.getServletPath());
            return false;
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}
