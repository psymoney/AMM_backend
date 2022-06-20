package com.amm.rest.mapper;

import com.amm.rest.vo.DayCandlePriceVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.text.ParseException;

@Mapper
@Repository
public interface DayCandlePriceMapper {
    DayCandlePriceVO getCandlePrice(String date);
    DayCandlePriceVO[] getCandlePricesDuringPeriod(String start, String end) throws ParseException;
}
