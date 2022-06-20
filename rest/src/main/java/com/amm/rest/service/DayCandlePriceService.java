package com.amm.rest.service;

import com.amm.rest.vo.DayCandlePriceVO;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Map;

@Service
public interface DayCandlePriceService {
    DayCandlePriceVO getCandlePrice(Map<String, String> param);
    DayCandlePriceVO[] getCandlePricesDuringPeriod(Map<String, String> params) throws ParseException;
}
