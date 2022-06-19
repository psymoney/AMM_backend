package com.amm.socket.handler;

import com.amm.socket.mapper.OrderbookMapper;
import com.amm.socket.vo.AskVO;
import com.amm.socket.vo.BidVO;
import com.amm.socket.vo.OrderbookVO;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class OrderbookResponseHandler {

    private final Logger logger = LoggerFactory.getLogger(OrderbookResponseHandler.class);
    @Autowired
    OrderbookMapper orderbookMapper;
    public void handleResponse(JSONObject jsonMessage) {
        OrderbookVO orderbookVO = convertToVO(jsonMessage);
        orderbookMapper.upsertOrderbook(orderbookVO);
//        Map<String, AskVO[]> askVOs = new HashMap<>();
//        askVOs.put("arr", orderbookVO.getAskVO());
//        orderbookMapper.upsertAsk(askVOs);
    }

    private OrderbookVO convertToVO(JSONObject jsonMessage) {
        OrderbookVO orderbookVO = new OrderbookVO();
        String code = jsonMessage.get("code").toString();
        orderbookVO.setCode(code);
        orderbookVO.setTimestamp((long) jsonMessage.get("timestamp"));
        orderbookVO.setTotalAskSize(new BigDecimal(jsonMessage.get("total_ask_size").toString()));
        orderbookVO.setTotalBidSize(new BigDecimal(jsonMessage.get("total_bid_size").toString()));

        ArrayList<AskVO> askVOS = new ArrayList<AskVO>();
        ArrayList<BidVO> bidVOS = new ArrayList<BidVO>();
        JSONArray orderbookUnits = (JSONArray)jsonMessage.get("orderbook_units");

        for (int i = 0 ; i < orderbookUnits.size() ; i++) {
            JSONObject unit = (JSONObject) orderbookUnits.get(i);

            AskVO askVO = new AskVO();
            BidVO bidVO = new BidVO();

            askVO.setCode(code);
            askVO.setAskPrice((double) unit.get("ask_price"));
            askVO.setAskSize(new BigDecimal(unit.get("ask_size").toString()));
            askVO.setPriceUnit(i + 1);

            bidVO.setCode(code);
            bidVO.setBidPrice((double) unit.get("ask_price"));
            bidVO.setBidSize(new BigDecimal(unit.get("ask_size").toString()));
            bidVO.setPriceUnit(i + 1);

            askVOS.add(askVO);
            bidVOS.add(bidVO);
        }

        return orderbookVO;
    }
}
