package com.example.mutants.dao;

import com.example.mutants.dao.impl.MutantDaoImpl;
import com.example.mutants.model.Dna;
import com.example.mutants.repository.DnaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
class MutantDaoTest {

    @Mock
    private DnaRepository dnaRepository;

    @InjectMocks
    private MutantDaoImpl mutantDao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mutantDao = new MutantDaoImpl(dnaRepository);
    }

    @Test
    void mutantDaoGetStatisticsReturnTheCorrectData() {
        testWithParameters(10L, 5L, "{\"count_mutant_dna\":5,\"count_human_dna\":10,\"ratio\":0.5}");
    }

    @Test
    void mutantDaoGetStatisticsPeriodicRatioReturnTheCorrectData() {
        testWithParameters(75L, 25L, "{\"count_mutant_dna\":25,\"count_human_dna\":75,\"ratio\":0.33}");
    }

    @Test
    void mutantDaoGetStatisticsNotHaveHumansCase() {
        testWithParameters(0L, 5L, "{\"count_mutant_dna\":5,\"count_human_dna\":0,\"ratio\":1.0}");
    }

    @Test
    void mutantDaoGetStatisticsNotHaveHumansAndNotHaveMutantsCase() {
        testWithParameters(0L, 0L, "{\"count_mutant_dna\":0,\"count_human_dna\":0,\"ratio\":0.0}");
    }

    @Test
    void mutantDaoCallToSaveMethod() {
        Dna dna = new Dna.DnaBuilder().build();
        mutantDao.save(dna);
        Mockito.verify(dnaRepository, VerificationModeFactory.atLeast(1)).save(dna);
    }

    private void testWithParameters(long humanQuantity, long mutantQuantity, String expectedResponse) {
        when(dnaRepository.humansQuantity()).thenReturn(humanQuantity);
        when(dnaRepository.mutantsQuantity()).thenReturn(mutantQuantity);
        String result = mutantDao.getStatistics();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedResponse, result);
    }
}
