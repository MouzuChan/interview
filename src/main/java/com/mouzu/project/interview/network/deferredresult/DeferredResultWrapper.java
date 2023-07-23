package com.mouzu.project.interview.network.deferredresult;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 长连接包装类
 */
public class DeferredResultWrapper {
    private static final long TIMEOUT = 60 * 1000;//60 seconds
    // 默认返回304
    private static final ResponseEntity<Boolean>
            NOT_MODIFIED_RESPONSE = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    private String instanceId;
    private DeferredResult<ResponseEntity<Boolean>> result;

    /**
     * 构造函数
     */
    public DeferredResultWrapper(String instanceId) {
        this.instanceId = instanceId;
        result = new DeferredResult<>(TIMEOUT, NOT_MODIFIED_RESPONSE);
        /**
         * Spring 的 DeferredResult 是一个异步请求,可以用来实现长连接
         */
    }

    // 超时
    public void onTimeout(Runnable timeoutCallback) {
        result.onTimeout(timeoutCallback);
    }

    public void onCompletion(Runnable completionCallback) {
        result.onCompletion(completionCallback);
    }

    /**
     * 设置返回结果
     * @param notification
     */
    public void setResult(boolean notification) {
        result.setResult(new ResponseEntity<>(notification, HttpStatus.OK));
    }

    public DeferredResult<ResponseEntity<Boolean>> getResult() {
        return result;
    }

}
