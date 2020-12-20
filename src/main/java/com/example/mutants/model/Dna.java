package com.example.mutants.model;

import com.google.gson.Gson;

import javax.persistence.*;

@Entity
@Table(name = "dna_table", schema = "public")
public class Dna {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "dna_text")
    private String genes;

    @Column(name = "isMutant")
    private Boolean isMutant;

    public String getGenes() {
        return genes;
    }

    public void setGenes(String genes) {
        this.genes = genes;
    }

    public Boolean getMutant() {
        return isMutant;
    }

    public void setMutant(Boolean mutant) {
        isMutant = mutant;
    }

    private Dna(DnaBuilder builder) {
        setGenes(builder.dnaText);
        setMutant(builder.isMutant);
    }

    public static class DnaBuilder
    {
        private String dnaText;
        private Boolean isMutant;

        public DnaBuilder withDna(String[] dna) {
            this.dnaText = new Gson().toJson(dna);
            return this;
        }
        public DnaBuilder withIsMutant(boolean isMutant) {
            this.isMutant = isMutant;
            return this;
        }

        public Dna build() {
            return new Dna(this);
        }
    }

}
