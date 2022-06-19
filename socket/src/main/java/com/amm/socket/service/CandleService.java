package com.amm.socket.service;

import com.amm.socket.vo.DayCandlePriceVO;

public interface CandleService {
    void upsertDayCandlePrice(DayCandlePriceVO params);
}
