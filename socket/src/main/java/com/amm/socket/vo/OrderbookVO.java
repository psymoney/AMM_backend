package com.amm.socket.vo;

import java.math.BigDecimal;

public class OrderbookVO {
    private String code;
    private long timestamp;
    private BigDecimal totalBidSize;
    private BigDecimal totalAskSize;
    private BidVO[] bidVO;
    private AskVO[] askVO;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getTotalBidSize() {
        return totalBidSize;
    }

    public void setTotalBidSize(BigDecimal totalBidSize) {
        this.totalBidSize = totalBidSize;
    }

    public BigDecimal getTotalAskSize() {
        return totalAskSize;
    }

    public void setTotalAskSize(BigDecimal totalAskSize) {
        this.totalAskSize = totalAskSize;
    }

    public BidVO[] getBidVO() {
        return bidVO;
    }

    public void setBidVO(BidVO[] bidVO) {
        this.bidVO = bidVO;
    }

    public AskVO[] getAskVO() {
        return askVO;
    }

    public void setAskVO(AskVO[] askVO) {
        this.askVO = askVO;
    }
}
