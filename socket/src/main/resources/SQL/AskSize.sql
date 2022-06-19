CREATE TABLE `AskSize`
(
    `code` varchar(10) NOT NULL ,
    `1`    double NULL ,
    `2`    double NULL ,
    `3`    double NULL ,
    `4`    double NULL ,
    `5`    double NULL ,
    `6`    double NULL ,
    `7`    double NULL ,
    `8`    double NULL ,
    `9`    double NULL ,
    `10`   double NULL ,
    `11`   double NULL ,
    `12`   double NULL ,
    `13`   double NULL ,
    `14`   double NULL ,
    `15`   double NULL ,

    PRIMARY KEY (`code`),
    KEY `FK_100` (`code`),
    CONSTRAINT `FK_98` FOREIGN KEY `FK_100` (`code`) REFERENCES `Orderbook` (`code`)
);