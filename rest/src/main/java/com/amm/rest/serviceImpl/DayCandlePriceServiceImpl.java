package com.amm.rest.serviceImpl;

import com.amm.rest.mapper.DayCandlePriceMapper;
import com.amm.rest.service.DayCandlePriceService;
import com.amm.rest.util.Validator;
import com.amm.rest.vo.DayCandlePriceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DayCandlePriceServiceImpl implements DayCandlePriceService {
    @Autowired
    DayCandlePriceMapper dayCandlePriceMapper;
    @Autowired
    Validator validator;

    @Override
    public DayCandlePriceVO getCandlePrice(Map<String, String> param) {
        if (!validator.isDateValid(param.get("date"))) {
            return null;
        }
        return dayCandlePriceMapper.getCandlePrice(param.get("date"));
    }

    @Override
    public DayCandlePriceVO[] getCandlePricesDuringPeriod(Map<String, String> params) {
        if (!validator.isPeriodValid(params.get("start"), params.get("end"))) {
            return null;
        }
        return dayCandlePriceMapper.getCandlePricesDuringPeriod(params.get("start"), params.get("end"));
    }
}
