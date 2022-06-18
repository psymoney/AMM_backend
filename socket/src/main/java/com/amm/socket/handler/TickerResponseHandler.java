package com.amm.socket.handler;

import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class TickerResponseHandler {

    private final Logger logger = LoggerFactory.getLogger(TickerResponseHandler.class);

    public void handleResponse(JSONObject jsonMessage) {
        String code = jsonMessage.get("code").toString();
        double openingPrice = (double) jsonMessage.get("opening_price");
        double highPrice = (double) jsonMessage.get("high_price");
        double lowPrice = (double) jsonMessage.get("low_price");
        double tradePrice = (double) jsonMessage.get("trade_price");
        String date = jsonMessage.get("trade_date").toString();
        Date tradeDate = Date.valueOf(date.substring(0,4) + "-" + date.substring(4,6) + "-" + date.substring(6,8));
        String tradeTime = jsonMessage.get("trade_time").toString();




        logger.info(jsonMessage.toString());
        logger.info(code + openingPrice + highPrice + lowPrice + tradePrice + tradeDate + tradeTime);

        logger.info(tradeDate.toString());
    }
}
