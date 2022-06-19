package com.amm.socket.serviceImpl;

import com.amm.socket.mapper.DayCandlePrice;
import com.amm.socket.service.CandleService;
import com.amm.socket.vo.DayCandlePriceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CandleServiceImpl implements CandleService {
    @Autowired
    DayCandlePrice dayCandlePrice;

    @Transactional
    public void upsertDayCandlePrice(DayCandlePriceVO params) {
        dayCandlePrice.upsertCandlePrice(params);
    }
}
