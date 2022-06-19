package com.amm.socket.handler;

import com.amm.socket.vo.DayCandlePriceVO;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class TickerResponseHandler {

    private final Logger logger = LoggerFactory.getLogger(TickerResponseHandler.class);

    public void handleResponse(JSONObject jsonMessage) {
        DayCandlePriceVO dayCandlePriceVO = new DayCandlePriceVO();
        dayCandlePriceVO.setCode(jsonMessage.get("code").toString());
        dayCandlePriceVO.setOpeningPrice((double) jsonMessage.get("opening_price"));
        dayCandlePriceVO.setHighPrice((double) jsonMessage.get("high_price"));
        dayCandlePriceVO.setLowPrice((double) jsonMessage.get("low_price"));
        dayCandlePriceVO.setTradePrice((double) jsonMessage.get("trade_price"));
        String date = jsonMessage.get("trade_date").toString();
        dayCandlePriceVO.setTradeDate(Date.valueOf(date.substring(0,4) + "-" + date.substring(4,6)
                + "-" + date.substring(6,8)));
        dayCandlePriceVO.setTradeTime(jsonMessage.get("trade_time").toString());

        logger.info(jsonMessage.toString());
        logger.info(dayCandlePriceVO.getCode());
        logger.info(dayCandlePriceVO.getTradeTime());
    }
}
