package com.example.mutants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.mutants.model.ExamResultEnum.HUMAN;
import static com.example.mutants.model.ExamResultEnum.MUTANT;

@Service
public class MutantAnalyzeService {

    @Autowired
    private StatisticsService statisticsService;

    private static final String GENES = "[ATCG]+";
    private static final Pattern pattern = Pattern.compile("AAAA|CCCC|TTTT|GGGG");
    private static final int IS_MUTANT_HAS_MORE_THAN = 1;
    private static final int MUTANT_QUANTITY_REQUIRED = 3;

    public MutantAnalyzeService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    public boolean isMutant(String[] dna) {
        if (validateDna(dna) && validateDnaSquare(dna)){
            return checkHumanOrMutantDna(dna);
        }
        return false;
    }

    private boolean checkHumanOrMutantDna(String[] dna) {
        if (checkIsMutant(dna)){
            statisticsService.saveDna(dna, MUTANT);
            return true;
        }
        statisticsService.saveDna(dna, HUMAN);
        return false;
    }

    private boolean checkIsMutant(String[] dna) {

        int horizontals = countMutantGenesInHorizontal(dna);
        int verticals = countMutantGenesInVertical(dna);
        int diagonalsFromRightToLeft = countMutantGenesInDiagonalFromRightToLeft(dna);
        int diagonalsFromLeftToRight = countMutantGenesInDiagonalFromLeftToRight(dna);

        return (horizontals + verticals + diagonalsFromRightToLeft + diagonalsFromLeftToRight) > IS_MUTANT_HAS_MORE_THAN;
    }

    private int countMutantGenesInDiagonalFromRightToLeft(String[] dna) {
        AtomicInteger diagonals = new AtomicInteger();
        for (int i = 0; i < dna.length - MUTANT_QUANTITY_REQUIRED; i++) {
            for (int j = dna.length; j > MUTANT_QUANTITY_REQUIRED; j--) {
                StringBuilder diagonalGen = new StringBuilder();
                diagonalGen.append(dna[i].charAt(j-1));
                diagonalGen.append(dna[i + 1].charAt(j - 2));
                diagonalGen.append(dna[i + 2].charAt(j - 3));
                diagonalGen.append(dna[i + 3].charAt(j - 4));

                diagonals.addAndGet(calculateMutantGenesQuantity(diagonalGen.toString()));
            }
        }
        return diagonals.get();
    }

    private int countMutantGenesInDiagonalFromLeftToRight(String[] dna) {
        AtomicInteger diagonals = new AtomicInteger();
        for (int i = 0; i < dna.length - MUTANT_QUANTITY_REQUIRED; i++) {
            for (int j = 0; j < dna.length - MUTANT_QUANTITY_REQUIRED; j++) {
                StringBuilder diagonalGen = new StringBuilder();
                diagonalGen.append(dna[i].charAt(j));
                diagonalGen.append(dna[i + 1].charAt(j + 1));
                diagonalGen.append(dna[i + 2].charAt(j + 2));
                diagonalGen.append(dna[i + 3].charAt(j + 3));

                diagonals.addAndGet(calculateMutantGenesQuantity(diagonalGen.toString()));
            }
        }
        return diagonals.get();
    }

    private int countMutantGenesInVertical(String[] dna) {
        AtomicInteger verticals = new AtomicInteger();
        for (int i = 0; i < dna.length; i++) {
            StringBuilder verticalGen = new StringBuilder();
            for (int j = 0; j < dna.length; j++) {
                verticalGen.append(dna[j].charAt(i));
            }
            verticals.addAndGet(calculateMutantGenesQuantity(verticalGen.toString()));
        }
        return verticals.get();
    }

    private int countMutantGenesInHorizontal(String[] dna) {
        AtomicInteger horizontals = new AtomicInteger();
        for (String genes : dna) {
            horizontals.addAndGet(calculateMutantGenesQuantity(genes));
        }
        return horizontals.get();
    }

    private int calculateMutantGenesQuantity(String genes) {
        AtomicInteger total = new AtomicInteger();
        Matcher matcher = pattern.matcher(genes);
        while (matcher.find()) {
            total.getAndIncrement();
        }
        return total.get();
    }

    boolean validateDna(String[] dna) {
        if(dna != null && dna.length > 0) {
            for (String item : dna) {
                if (!item.matches(GENES)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    boolean validateDnaSquare(String[] dna) {
        int dnaLength = dna.length;
        for (String gen : dna) {
            if (dnaLength!=gen.length()) {
                return false;
            }
        }
        return true;
    }

}
