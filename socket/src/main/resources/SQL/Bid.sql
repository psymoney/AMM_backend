CREATE TABLE `Bid`
(
    `code`       varchar(10) NOT NULL ,
    `bid_price`  double NOT NULL ,
    `bid_size`   double NOT NULL ,
    `price_unit` int NOT NULL ,

    PRIMARY KEY (`code`),
    KEY `FK_142` (`code`),
    CONSTRAINT `FK_140` FOREIGN KEY `FK_142` (`code`) REFERENCES `Orderbook` (`code`)
);