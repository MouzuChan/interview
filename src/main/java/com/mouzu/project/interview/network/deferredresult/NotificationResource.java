package com.mouzu.project.interview.network.deferredresult;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/notifications")
public class NotificationResource implements ReleaseMessageListener{
    private static final Logger logger = LoggerFactory.getLogger(NotificationResource.class);

    private final Multimap<String, DeferredResultWrapper> deferredResults =
            Multimaps.synchronizedSetMultimap(HashMultimap.create());
    // 线程池
    private final ExecutorService largeNotificationBatchExecutorService;


    public NotificationResource() {
        largeNotificationBatchExecutorService = Executors.newFixedThreadPool(20, ApolloThreadFactory.create
                ("NotificationResource", true));
    }

    /**
     * 接收所有实例链接
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public DeferredResult<ResponseEntity<Boolean>> pollNotification(@RequestParam(value = "instanceId") String instanceId) {
        DeferredResultWrapper deferredResultWrapper = new DeferredResultWrapper(instanceId);
        deferredResultWrapper.onTimeout(() -> logger.debug("notifications timeout"));
        deferredResultWrapper.onCompletion(() -> deferredResults.remove("server", deferredResultWrapper));
        this.deferredResults.put("server", deferredResultWrapper);

        return deferredResultWrapper.getResult();
    }

    /**
     * 通知所有实例
     */
    @Override
    public void handleMessage() {
        logger.info("gray map modified");
        for (DeferredResultWrapper wrapper : (List<DeferredResultWrapper>) new ArrayList(deferredResults.get("server"))) {
            largeNotificationBatchExecutorService.submit(() -> wrapper.setResult(true));
        }

    }

}
