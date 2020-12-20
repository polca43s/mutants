package com.example.mutants.dao;

public interface MutantDao<T> {
    public void save(T entity);

    public String getStatistics();
}
