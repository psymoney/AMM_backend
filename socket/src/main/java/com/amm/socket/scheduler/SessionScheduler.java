package com.amm.socket.scheduler;

import com.amm.socket.client.UpbitAPIClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class SessionScheduler {

    private final Logger logger = LoggerFactory.getLogger(SessionScheduler.class);
    @Autowired
    UpbitAPIClient upbitAPIClient;

    @Scheduled(cron="0 * * * * *")
    public void executeEveryMinute() {
        logger.info("run scheduler");
        try {
           upbitAPIClient.Connect();
        } catch (ExecutionException | InterruptedException e) {
            logger.error(String.valueOf(e.getStackTrace()));
        }
    }
}
