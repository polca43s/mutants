package com.example.mutants.model.dto;

import com.google.gson.annotations.SerializedName;

public class StatisticsDTO {
    @SerializedName("count_mutant_dna")
    private Long countMutantDna;
    @SerializedName("count_human_dna")
    private Long countHumanDna;
    private Double ratio;

    public Long getMutants() {
        return countMutantDna;
    }

    public void setMutants(Long mutants) {
        this.countMutantDna = mutants;
    }

    public Long getHumans() {
        return countHumanDna;
    }

    public void setHumans(Long humans) {
        this.countHumanDna = humans;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }
}
