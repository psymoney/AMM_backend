package com.amm.socket.mapper;

import com.amm.socket.vo.DayCandlePriceVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DayCandlePriceMapper {
    void upsertCandlePrice(DayCandlePriceVO dayCandlePriceVO);
}
