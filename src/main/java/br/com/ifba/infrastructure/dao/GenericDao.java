package br.com.ifba.infrastructure.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public abstract class GenericDao<T, ID> implements GenericIDao<T, ID> {

    protected EntityManager em;
    private Class<T> entityClass;

    public GenericDao(Class<T> entityClass) {
        this.entityClass = entityClass;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("prg03PU");
        this.em = emf.createEntityManager();
    }

    @Override
    public void save(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public void update(T entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    @Override
    public void delete(T entity) {
        em.getTransaction().begin();
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        em.getTransaction().commit();
    }

    @Override
    public T findById(ID id) {
        return em.find(entityClass, id);
    }

    @Override
    public List<T> getAll() {
        return em.createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
    }
}
