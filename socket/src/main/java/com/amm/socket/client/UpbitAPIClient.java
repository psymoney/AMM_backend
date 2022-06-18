package com.amm.socket.client;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;

@Component
public class UpbitAPIClient extends TextWebSocketHandler {

    private JSONArray request = new JSONArray();

    private final Logger logger = LoggerFactory.getLogger(UpbitAPIClient.class);
    private WebSocketSession upBitAPISession;

    public UpbitAPIClient() throws ExecutionException, InterruptedException, IOException {
        StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
        this.upBitAPISession = webSocketClient.doHandshake(this, new WebSocketHttpHeaders(),
                URI.create("wss://api.upbit.com/websocket/v1")).get();
        JSONObject ticket = new JSONObject();
        ticket.put("ticket", "test");

        JSONArray ticker = new JSONArray();
        ticker.add("KRW-BTC");
        JSONObject type = new JSONObject();
        type.put("type", "ticker");
        type.put("codes", ticker);

        request.add(ticket);
        request.add(type);

        TextMessage message = new TextMessage(request.toString());
        upBitAPISession.sendMessage(message);

        logger.info(request.toString());
        logger.info("initiate socket connection", this.upBitAPISession);
    }

    public void Connect() throws ExecutionException, InterruptedException {
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
