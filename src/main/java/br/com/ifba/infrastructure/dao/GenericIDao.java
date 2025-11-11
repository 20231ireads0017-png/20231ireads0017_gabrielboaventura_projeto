package br.com.ifba.infrastructure.dao;

import java.util.List;

public interface GenericIDao<T> {

    T save(T obj);

    T update(T obj);

    void delete(T obj);

    List<T> findAll();

    T findById(Long id);
}
