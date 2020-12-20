package com.example.mutants.model;

import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DnaTest {

    private Dna dna;

    @Test
    void dnaBuilderEmptyReturnTheCorrectObject() {
        dna = new Dna.DnaBuilder().build();
        Assertions.assertNull(dna.getGenes());
        Assertions.assertNull(dna.getMutant());
    }

    @Test
    void dnaBuilderWithIsMutantTrueReturnTheCorrectObject() {
        dna = new Dna.DnaBuilder().withIsMutant(true).build();
        Assertions.assertNull(dna.getGenes());
        Assertions.assertNotNull(dna.getMutant());
        Assertions.assertTrue(dna.getMutant());
    }

    @Test
    void dnaBuilderWithIsMutantFalseReturnTheCorrectObject() {
        dna = new Dna.DnaBuilder().withIsMutant(false).build();
        Assertions.assertNull(dna.getGenes());
        Assertions.assertNotNull(dna.getMutant());
        Assertions.assertFalse(dna.getMutant());
    }

    @Test
    void dnaBuilderWithDnaReturnTheCorrectObject() {
        String[] dnaGen = new String[] {"ATTAGC", "ATTAGC"};
        dna = new Dna.DnaBuilder().withDna(dnaGen).build();
        Assertions.assertNull(dna.getMutant());
        Assertions.assertNotNull(dna.getGenes());
        Assertions.assertEquals(new Gson().toJson(dnaGen), dna.getGenes());
    }

    @Test
    void dnaBuilderWithAllDataReturnTheCorrectObject() {
        String[] dnaGen = new String[] {"ATTAGC", "ATTAGC"};
        dna = new Dna.DnaBuilder().withDna(dnaGen).withIsMutant(false).build();
        Assertions.assertNotNull(dna.getMutant());
        Assertions.assertFalse(dna.getMutant());
        Assertions.assertNotNull(dna.getGenes());
        Assertions.assertEquals(new Gson().toJson(dnaGen), dna.getGenes());
    }

}
