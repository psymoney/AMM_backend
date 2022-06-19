CREATE TABLE `Ask`
(
    `code`       varchar(10) NOT NULL ,
    `ask_price`  double NOT NULL ,
    `ask_size`   double NOT NULL ,
    `price_unit` int NOT NULL ,

    PRIMARY KEY (`code`),
    KEY `FK_54` (`code`),
    CONSTRAINT `FK_52` FOREIGN KEY `FK_54` (`code`) REFERENCES `Orderbook` (`code`)
);