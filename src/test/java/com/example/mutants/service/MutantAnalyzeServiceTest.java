package com.example.mutants.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static com.example.mutants.model.ExamResultEnum.HUMAN;
import static com.example.mutants.model.ExamResultEnum.MUTANT;
import static org.mockito.Mockito.verify;

@SpringJUnitConfig
class MutantAnalyzeServiceTest {

    @Mock
    private StatisticsService statisticsService;

    @InjectMocks
    private MutantAnalyzeService service;

    private final String[] dnaEmpty = new String[] {};
    // @formatter:off
    private final String[] dnaHuman = new String[] {
            "ATCG",
            "CATT",
            "TGCC",
            "TAGG"};
    private final String[] dnaMutant = new String[] {
            "TACC",
            "TGCA",
            "TACG",
            "TGCA"};
    private final String[] dnaInvalid = new String[] {
            "TADZ",
            "TA3D",
            "TODD",
            "TADD"};
    private final String[] dnaNotSquareMinus = new String[] {
            "ATCG",
            "CATT",
            "TGC",
            "TAG"};
    private final String[] dnaNotSquarePlus = new String[] {
            "ATCG",
            "CATT",
            "TGCTC",
            "TAGCC"};
    private final String[] dnaNotSquareOneRow = new String[] {"ATCG" };
    private final String[] dnaMutantHorizontal = new String[] {
            "TTTTACCCC",
            "AACCGGTTA",
            "CCTTAGAGG",
            "GGCCCTTAA",
            "AAAGGGCCC",
            "TTTCAATGG",
            "AAGGGCCCT",
            "TCTTAAGGG",
            "GGTAACCGG"};
    private final String[] dnaMutantLeftToRight = new String[] {
            "TTTT",
            "ATCA",
            "TATG",
            "TGCT"};
    private final String[] dnaMutantRightToLeft = new String[] {
            "AAAA",
            "ATAA",
            "TAGG",
            "AGCT"};
    private final String[] dnaMutantDiagonal = new String[] {
            "TTGTACACC",
            "ATCCGGTTG",
            "CCTTAGAGC",
            "GGCTCTTAC",
            "AAAGAGCCC",
            "TTTCACTGG",
            "AAGGGCCCT",
            "TCTTAAGCG",
            "GGTAACCGC"};
    // @formatter:on

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        service = new MutantAnalyzeService(statisticsService);
    }

    @Test
    void serviceCallToIsMutantWithDnaInvalidReturnError() {
        Assertions.assertFalse(service.isMutant(dnaInvalid));
    }

    @Test
    void serviceCallToIsMutantWithDnaNotSquarePlusReturnError() {
        Assertions.assertFalse(service.isMutant(dnaNotSquarePlus));
    }

    @Test
    void serviceCallToIsMutantWithHumanDnaReturnHuman() {
        Assertions.assertFalse(service.isMutant(dnaHuman));
        verify(statisticsService, VerificationModeFactory.atLeast(1)).saveDna(dnaHuman, HUMAN);
    }

    @Test
    void serviceCallToIsMutantWithMutantDnaReturnMutant() {
        Assertions.assertTrue(service.isMutant(dnaMutant));
        verify(statisticsService, VerificationModeFactory.atLeast(1)).saveDna(dnaMutant, MUTANT);
    }

    @Test
    void serviceCallValidateDnaWithValidDnaReturnTrue() {
        Assertions.assertTrue(service.validateDna(dnaHuman));
    }

    @Test
    void serviceCallValidateDnaWithValidMutantDnaReturnTrue() {
        Assertions.assertTrue(service.validateDna(dnaMutant));
    }

    @Test
    void serviceCallValidateDnaWithInvalidDnaReturnFalse() {
        Assertions.assertFalse(service.validateDna(dnaInvalid));
    }

    @Test
    void serviceCallValidateDnaWithEmptyDnaReturnTrue() {
        Assertions.assertFalse(service.validateDna(dnaEmpty));
    }

    @Test
    void serviceCallValidateDnaSquareWithValidDnaReturnTrue() {
        Assertions.assertTrue(service.validateDnaSquare(dnaHuman));
    }

    @Test
    void serviceCallValidateDnaSquareWithInvalidDnaMinusReturnTrue() {
        Assertions.assertFalse(service.validateDnaSquare(dnaNotSquareMinus));
        Assertions.assertFalse(service.validateDnaSquare(dnaNotSquarePlus));
    }

    @Test
    void serviceCallValidateDnaSquareWithInvalidDnaOneRowReturnTrue() {
        Assertions.assertFalse(service.validateDnaSquare(dnaNotSquareOneRow));
    }

    @Test
    void serviceCallToIsMutantWithDnaMutantHorizontalReturnTrue() {
        Assertions.assertTrue(service.isMutant(dnaMutantHorizontal));
        verify(statisticsService, VerificationModeFactory.atLeast(1)).saveDna(dnaMutantHorizontal, MUTANT);
    }

    @Test
    void serviceCallToIsMutantWithDnaMutantRightToLeftReturnTrue() {
        Assertions.assertTrue(service.isMutant(dnaMutantRightToLeft));
        verify(statisticsService, VerificationModeFactory.atLeast(1)).saveDna(dnaMutantRightToLeft, MUTANT);
    }

    @Test
    void serviceCallToIsMutantWithDnaMutantLeftToRightReturnTrue() {
        Assertions.assertTrue(service.isMutant(dnaMutantLeftToRight));
        verify(statisticsService, VerificationModeFactory.atLeast(1)).saveDna(dnaMutantLeftToRight, MUTANT);
    }

    @Test
    void serviceCallToIsMutantWithDnaMutantDiagonalNinePositionsReturnTrue() {
        Assertions.assertTrue(service.isMutant(dnaMutantDiagonal));
        verify(statisticsService, VerificationModeFactory.atLeast(1)).saveDna(dnaMutantDiagonal, MUTANT);
    }

}
