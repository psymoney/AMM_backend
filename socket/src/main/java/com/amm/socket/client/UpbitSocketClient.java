package com.amm.socket.client;

import com.amm.socket.handler.OrderbookResponseHandler;
import com.amm.socket.handler.TickerResponseHandler;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

@Component
public class UpbitSocketClient extends TextWebSocketHandler {

    private JSONArray request = new JSONArray();
    private TextMessage requestMessage;
    private URI socketUri;
    private final Logger logger = LoggerFactory.getLogger(UpbitSocketClient.class);
    private final JSONParser jsonParser = new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE);
    private WebSocketSession upBitAPISession;

    private TickerResponseHandler tickerResponseHandler;
    private OrderbookResponseHandler orderbookResponseHandler;

    @Autowired
    public UpbitSocketClient(TickerResponseHandler tickerResponseHandler,
                             OrderbookResponseHandler orderbookResponseHandler) throws URISyntaxException {
        this.tickerResponseHandler = tickerResponseHandler;
        this.orderbookResponseHandler = orderbookResponseHandler;
        socketUri = new URI("wss://api.upbit.com/websocket/v1");
        setRequestMessage();
        setUpBitAPISession();
    }

    public void setRequestMessage() {
        JSONObject ticket = new JSONObject();
        ticket.put("ticket", "test");

        JSONArray codes = new JSONArray();
        codes.add("KRW-BTC");

        JSONObject ticker = new JSONObject();
        ticker.put("type", "ticker");
        ticker.put("codes", codes);

        JSONObject orderbook = new JSONObject();
        orderbook.put("type", "orderbook");
        orderbook.put("codes", codes);

        request.add(ticket);
        request.add(ticker);
        request.add(orderbook);

        requestMessage = new TextMessage(request.toString());
    }

    public void setUpBitAPISession() {
        try {
            StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
            this.upBitAPISession = webSocketClient.doHandshake(this,
                    new WebSocketHttpHeaders(), socketUri).get();

            upBitAPISession.sendMessage(requestMessage);

            logger.info("initiate socket connection: " + this.upBitAPISession);
        } catch (ExecutionException | InterruptedException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void validateConnection() throws ExecutionException, InterruptedException {
        if(requestMessage == null) {
            setRequestMessage();
        }
        if(upBitAPISession == null) {
            setUpBitAPISession();
        }

        if(!upBitAPISession.isOpen()) {
            StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
            this.upBitAPISession = webSocketClient.doHandshake(this, new WebSocketHttpHeaders(),
                    socketUri).get();
            logger.info("reconnect socket connection: " + this.upBitAPISession);
        }
    }


    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        JSONObject jsonMessage = convertToJsonObject(message);

        if (jsonMessage == null) {
            return;
        }

        String type = jsonMessage.get("type").toString();

        switch (type) {
            case "ticker":
                tickerResponseHandler.handleResponse(jsonMessage);
                break;

            case "orderbook":
                orderbookResponseHandler.handleResponse(jsonMessage);
                break;


            default:
                return;
        }


    }

    public JSONObject convertToJsonObject(BinaryMessage message) {
        String msg = StandardCharsets.UTF_8.decode(message.getPayload()).toString();
        Object object = null;
        JSONObject jsonObject = null;
        try {
            object = jsonParser.parse(msg);
            jsonObject = (JSONObject) object;
        } catch (ParseException e) {
            logger.error("parsing binay message failed");
            logger.error(e.getMessage());
        }
        return jsonObject;
    }

    public void handleTickerResponse(JSONObject jsonMessage) {

    }
}
