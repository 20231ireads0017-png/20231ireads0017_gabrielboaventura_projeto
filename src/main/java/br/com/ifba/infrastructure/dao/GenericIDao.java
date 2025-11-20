package br.com.ifba.infrastructure.dao;

import java.util.List;

public interface GenericIDao<T, ID> {
    void save(T entity);
    void update(T entity);
    void delete(T entity);
    T findById(ID id);
    List<T> getAll();
}
