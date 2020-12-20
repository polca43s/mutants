package com.example.mutants.service;

import com.example.mutants.dao.MutantDao;
import com.example.mutants.model.Dna;
import com.example.mutants.model.ExamResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {
    @Autowired
    private MutantDao<Dna> mutantDao;

    public StatisticsService(MutantDao<Dna> mutantDao) {
        this.mutantDao = mutantDao;
    }

    public String getStatistics() {
        return mutantDao.getStatistics();
    }

    public void saveDna(String[] code, ExamResultEnum result) {
        mutantDao.save(new Dna.DnaBuilder().withDna(code).withIsMutant(result== ExamResultEnum.MUTANT).build());
    }
}
