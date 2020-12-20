package com.example.mutants.repository;

import com.example.mutants.model.Dna;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DnaRepository extends CrudRepository<Dna, Long> {

    @Query("SELECT COUNT (d) From Dna d WHERE d.isMutant=true")
    public Long mutantsQuantity();

    @Query("SELECT COUNT (d) From Dna d WHERE d.isMutant=false")
    public Long humansQuantity();

}
