package br.com.ifba.infrastructure.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public abstract class GenericDao<T> implements GenericIDao<T> {

    protected static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("prg03PU");

    protected EntityManager em = emf.createEntityManager();

    private final Class<T> classe;

    public GenericDao(Class<T> classe) {
        this.classe = classe;
    }

    @Override
    public T save(T obj) {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
        return obj;
    }

    @Override
    public T update(T obj) {
        em.getTransaction().begin();
        obj = em.merge(obj);
        em.getTransaction().commit();
        return obj;
    }

    @Override
    public void delete(T obj) {
        em.getTransaction().begin();
        em.remove(em.contains(obj) ? obj : em.merge(obj));
        em.getTransaction().commit();
    }

    @Override
    public List<T> findAll() {
        return em.createQuery("FROM " + classe.getName(), classe)
                .getResultList();
    }

    @Override
    public T findById(Long id) {
        return em.find(classe, id);
    }
}
