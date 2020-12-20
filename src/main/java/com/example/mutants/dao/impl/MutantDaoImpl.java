package com.example.mutants.dao.impl;

import com.example.mutants.dao.MutantDao;
import com.example.mutants.model.Dna;
import com.example.mutants.model.dto.StatisticsDTO;
import com.example.mutants.repository.DnaRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class MutantDaoImpl implements MutantDao<Dna> {

    @Autowired
    private DnaRepository dnaRepository;

    public MutantDaoImpl(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    @Override
    public void save(Dna entity) {
        dnaRepository.save(entity);
    }

    public String getStatistics() {
        Double ratio = 0D;
        StatisticsDTO stats = new StatisticsDTO();
        stats.setMutants(dnaRepository.mutantsQuantity());
        stats.setHumans(dnaRepository.humansQuantity());
        if (stats.getMutants() > 0 && stats.getHumans() > 0) {
            ratio = stats.getMutants().doubleValue() / stats.getHumans();
        } else if (stats.getMutants() > 0) {
            ratio = 1D;
        }
        setFormattedPercentage(ratio, stats);
        return new Gson().toJson(stats);
    }

    private void setFormattedPercentage(Double ratio, StatisticsDTO stats) {
        String ratioString = new DecimalFormat("#.##").format(ratio).replace(",", ".");
        stats.setRatio(Double.parseDouble(ratioString));
    }
}
