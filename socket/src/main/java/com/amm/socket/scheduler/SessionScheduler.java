package com.amm.socket.scheduler;

import com.amm.socket.client.UpbitSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class SessionScheduler {

    private final Logger logger = LoggerFactory.getLogger(SessionScheduler.class);
    UpbitSocketClient upbitAPIClient;

    @Autowired
    public SessionScheduler(UpbitSocketClient upbitSocketClient) {
        this.upbitAPIClient = upbitSocketClient;
    }

    @Scheduled(fixedRate = 1000)
    public void executeEverySecond() {
        try {
           upbitAPIClient.validateConnection();
        } catch (ExecutionException | InterruptedException e) {
            logger.error(e.getMessage());
        }
    }
}
