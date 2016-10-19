package com.jack.security.webapp.asyncDemo;

import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by wajiangk on 10/13/2016.
 */
@Service
public class LongTimeAsyncCallService {
    private final int CorePoolSize = 4;
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(CorePoolSize);
    public void makeRemoteCallAndUnknownWhenFinish(final LongTimeTaskCallback callback){
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                callback.callback("长时间异步调用完成.");
            }
        },1 , TimeUnit.SECONDS);
    }
}
