package com.example.mutants.service;

import com.example.mutants.dao.MutantDao;
import com.example.mutants.model.ExamResultEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class StatisticsServiceTest {

    @Mock
    private MutantDao mutantDao;

    @InjectMocks
    private StatisticsService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        service = new StatisticsService(mutantDao);
    }

    @Test
    void serviceGetStatisticsCallOneTimeToMutantDao() {
        service.getStatistics();
        verify(mutantDao, VerificationModeFactory.atLeast(1)).getStatistics();
    }

    @Test
    void serviceGetSaveHumanCallToSaveOfMutantDao() {
        String[] code = new String[]{};
        service.saveDna(code, ExamResultEnum.HUMAN);
        verify(mutantDao, VerificationModeFactory.atLeast(1)).save(any());
    }

}
