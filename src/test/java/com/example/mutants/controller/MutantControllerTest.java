package com.example.mutants.controller;

import com.example.mutants.model.dto.GenDTO;
import com.example.mutants.service.MutantAnalyzeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;

@SpringJUnitConfig
class MutantControllerTest {

    @Mock
    private MutantAnalyzeService mutantService;

    @InjectMocks
    private MutantController controller;
    private String[] code;
    private GenDTO gen;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new MutantController(mutantService);
        code = new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        gen = new GenDTO();
        gen.setDna(code);
    }

    @Test
    void controllerWithMutantDnaReturnOk() {
        when(mutantService.isMutant(any())).thenReturn(true);
        ResponseEntity<String> response = controller.mutant(gen);
        Assertions.assertSame(OK, response.getStatusCode());
    }

    @Test
    void controllerWithHumanDnaOrInvalidDnaReturnForbidden() {
        when(mutantService.isMutant(any())).thenReturn(false);
        ResponseEntity<String> response = controller.mutant(gen);
        Assertions.assertSame(FORBIDDEN, response.getStatusCode());
    }

    @Test
    void controllerWithNullDnaReturnForbidden() {
        ResponseEntity<String> response = controller.mutant(null);
        Assertions.assertSame(FORBIDDEN, response.getStatusCode());
    }

}
