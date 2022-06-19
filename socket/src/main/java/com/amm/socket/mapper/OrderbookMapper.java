package com.amm.socket.mapper;

import com.amm.socket.vo.AskVO;
import com.amm.socket.vo.OrderbookVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface OrderbookMapper {

    void upsertOrderbook(OrderbookVO orderbookVO);
    void upsertAsk(Map<String, AskVO[]> askVO);
}
