package com.amm.rest.controller;

import com.amm.rest.service.DayCandlePriceService;
import com.amm.rest.vo.DayCandlePriceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/day/candle")
public class DayCandlePriceController {
    @Autowired
    DayCandlePriceService dayCandlePriceService;

    @PostMapping(value = "/day", produces = "application/json; charset=utf-8")
    public ResponseEntity<DayCandlePriceVO> getCandlePrice(@RequestBody Map<String, String> param) {
        DayCandlePriceVO result = dayCandlePriceService.getCandlePrice(param);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/period", produces = "application/json; charset=utf-8")
    public ResponseEntity<DayCandlePriceVO[]> getCandlePricesDuringPeriod(@RequestBody Map<String, String> params) {
        DayCandlePriceVO[] result = dayCandlePriceService.getCandlePricesDuringPeriod(params);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
}
