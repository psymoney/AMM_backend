package com.amm.rest.serviceImpl;

import com.amm.rest.mapper.DayCandlePriceMapper;
import com.amm.rest.service.DayCandlePriceService;
import com.amm.rest.util.Validator;
import com.amm.rest.vo.DayCandlePriceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Map;

@Service
public class DayCandlePriceServiceImpl implements DayCandlePriceService {
    private DayCandlePriceMapper dayCandlePriceMapper;
    @Autowired
    Validator validator;

    @Autowired
    public DayCandlePriceServiceImpl(DayCandlePriceMapper dayCandlePriceMapper) {
        this.dayCandlePriceMapper = dayCandlePriceMapper;
    }
    @Override
    public DayCandlePriceVO getCandlePrice(Map<String, String> param) {
        if (!validator.isDateValid(param.get("date"))) {
            return null;
        }
        return dayCandlePriceMapper.getCandlePrice(param.get("date"));
    }

    @Override
    public DayCandlePriceVO[] getCandlePricesDuringPeriod(Map<String, String> params) throws ParseException {
        if (!validator.isPeriodValid(params.get("start"), params.get("end"))) {
            return null;
        }
        return dayCandlePriceMapper.getCandlePricesDuringPeriod(params.get("start"), params.get("end"));
    }
}
