CREATE TABLE `Orderbook`
(
    `code`           varchar(10) NOT NULL ,
    `time_stamp`      bigint NOT NULL ,
    `total_bid_size` double NOT NULL ,
    `total_ask_size` double NOT NULL ,

    PRIMARY KEY (`code`)
);