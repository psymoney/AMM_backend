package com.amm.rest.service;

import com.amm.rest.vo.DayCandlePriceVO;

import java.util.Map;

public interface DayCandlePriceService {
    DayCandlePriceVO getCandlePrice(Map<String, String> param);
    DayCandlePriceVO[] getCandlePricesDuringPeriod(Map<String, String> params);
}
