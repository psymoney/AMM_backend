CREATE TABLE `AMM`.`DayCandlePrice`
(
    `code`          varchar(10) NOT NULL ,
    `trade_date`    date NOT NULL ,
    `opening_price` double NOT NULL ,
    `high_price`    double NOT NULL ,
    `low_price`     double NOT NULL ,
    `trade_price`   double NOT NULL ,
    `trade_time`    varchar(10) NOT NULL ,

    PRIMARY KEY (`code`, `trade_date`)
);



INSERT INTO AMM.DayCandlePrice(code, trade_date, opening_price, high_price, low_price, trade_price, trade_time)
VALUES("BTC-KRW", "2021-01-01", 1234.5678, 1234.5678, 1234.5678, 1234.5678, "123456")
    ON DUPLICATE KEY UPDATE opening_price = 1234.5678, high_price = 1234.5678, low_price = 1234.5678, trade_price = 1234.5678,
    trade_time = "123456";

INSERT INTO AMM.DayCandlePrice(code, trade_date, opening_price, high_price, low_price, trade_price, trade_time)
VALUES("BTC-KRW", "2021-01-01", 1234.5678, 1234.5678, 1234.5678, 1234.5678, "123456")
    ON DUPLICATE KEY UPDATE opening_price = 4321.5678, high_price = 4321.5678, low_price = 4321.5678, trade_price = 4321.5678,
                         trade_time = "654321";

INSERT INTO AMM.DayCandlePrice(code, trade_date, opening_price, high_price, low_price, trade_price, trade_time)
VALUES("BTC-KRW", "2021-01-02", 1234.5678, 1234.5678, 1234.5678, 1234.5678, "123456")
    ON DUPLICATE KEY UPDATE opening_price = 1234.5678, high_price = 1234.5678, low_price = 1234.5678, trade_price = 1234.5678,
                         trade_time = "123456";