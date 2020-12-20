package com.example.mutants.controller;

import com.example.mutants.model.dto.StatisticsDTO;
import com.example.mutants.service.StatisticsService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
class StatisticsControllerTest {

    @Mock
    private StatisticsService service;

    @InjectMocks
    private StatisticsController controller;
    private String response;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new StatisticsController(service);
    }

    @Test
    void controllerCallToStatsReturnData() {
        response = getResponseWithStatisticsDTO();
        when(service.getStatistics()).thenReturn(response);
        ResponseEntity<String> results = controller.statistics();
        verify(service, VerificationModeFactory.atLeast(1)).getStatistics();
        Assertions.assertSame(HttpStatus.OK, results.getStatusCode());
        Assertions.assertNotNull(results.getBody());
        Assertions.assertEquals(response, results.getBody());
    }

    private String getResponseWithStatisticsDTO() {
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        statisticsDTO.setHumans(75L);
        statisticsDTO.setMutants(25L);
        statisticsDTO.setRatio(0.33D);
        return new Gson().toJson(statisticsDTO);
    }
}
