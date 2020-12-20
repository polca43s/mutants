package com.example.mutants.controller;

import com.example.mutants.model.dto.GenDTO;
import com.example.mutants.service.MutantAnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MutantController {

    @Autowired
    private MutantAnalyzeService mutantService;

    public MutantController(MutantAnalyzeService mutantService) {
        this.mutantService = mutantService;
    }

    @PostMapping(value = "/mutant", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> mutant(@RequestBody GenDTO dna) {
        if(dna != null && mutantService.isMutant(dna.getDna())){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
