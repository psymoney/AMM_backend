package com.amm.rest.controller;

import com.amm.rest.service.DayCandlePriceService;
import com.amm.rest.vo.DayCandlePriceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Map;

@Controller
@RestController
@RequestMapping("/day/candle")
public class DayCandlePriceController {
    private final Logger logger = LoggerFactory.getLogger(DayCandlePriceController.class);
    private DayCandlePriceService dayCandlePriceService;

    @Autowired
    public DayCandlePriceController(DayCandlePriceService dayCandlePriceService) {
        this.dayCandlePriceService = dayCandlePriceService;
    }

    @PostMapping(value = "/day", produces = "application/json; charset=utf-8")
    public ResponseEntity getCandlePrice(@RequestBody Map<String, String> param) {
        logger.info("RequestBody param: " + param.toString());
        DayCandlePriceVO result = null;
        try{
            result = dayCandlePriceService.getCandlePrice(param);
        } catch(Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (result == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else if (result.getClass() != DayCandlePriceVO.class) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/period", produces = "application/json; charset=utf-8")
    public ResponseEntity getCandlePricesDuringPeriod(@RequestBody Map<String, String> params)  {
        logger.info("RequestBody params: " + params.toString());
        DayCandlePriceVO[] result = null;
        try{
            result = dayCandlePriceService.getCandlePricesDuringPeriod(params);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        if (result == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (result.length == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
}
