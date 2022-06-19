CREATE TABLE `Orderbook`
(
    `code`           varchar(10) NOT NULL ,
    `timestamp`      int NOT NULL ,
    `total_bid_dize` double NOT NULL ,
    `total_ask_size` double NOT NULL ,

    PRIMARY KEY (`code`)
);