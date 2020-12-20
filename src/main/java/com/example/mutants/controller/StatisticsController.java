package com.example.mutants.controller;

import com.example.mutants.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StatisticsController {

    @Autowired
    private StatisticsService service;

    public StatisticsController(StatisticsService service) {
        this.service = service;
    }

    @GetMapping(value = "/stats")
    @ResponseBody
    public ResponseEntity<String> statistics() {
        return new ResponseEntity<>(service.getStatistics(), HttpStatus.OK);
    }
}
