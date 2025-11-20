package br.com.ifba.infrastructure.entity;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceEntity {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("prg03PU");

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public static void close() {
        emf.close();
    }
}
