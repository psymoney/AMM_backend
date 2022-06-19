package com.amm.socket.vo;

import java.math.BigDecimal;

public class BidVO {
    private String code;
    private double bidPrice;
    private BigDecimal bidSize;
    private int priceUnit;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public BigDecimal getBidSize() {
        return bidSize;
    }

    public void setBidSize(BigDecimal bidSize) {
        this.bidSize = bidSize;
    }

    public int getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(int priceUnit) {
        this.priceUnit = priceUnit;
    }
}
