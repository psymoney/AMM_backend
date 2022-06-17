package com.amm.socket.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.util.concurrent.ExecutionException;

@Component
public class UpbitAPIClient extends TextWebSocketHandler {

    private final Logger logger = LoggerFactory.getLogger(UpbitAPIClient.class);
    private WebSocketSession upBitAPISession;

    public void Connect() throws ExecutionException, InterruptedException {
        if(upBitAPISession == null) {
            StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
            this.upBitAPISession = webSocketClient.doHandshake(this, new WebSocketHttpHeaders(),
                    URI.create("wss://api.upbit.com/websocket/v1")).get();


            logger.info("initiate socket connection", this.upBitAPISession);
        }
        if(upBitAPISession.isOpen()) {
            logger.info("session is on");
        } else {
            StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
            this.upBitAPISession = webSocketClient.doHandshake(this, new WebSocketHttpHeaders(),
                    URI.create("wss://api.upbit.com/websocket/v1")).get();
            logger.info("reconnect socket connection", this.upBitAPISession);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info(message.getPayload());
        super.handleTextMessage(session, message);
    }
}
