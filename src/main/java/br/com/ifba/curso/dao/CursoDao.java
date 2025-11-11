package br.com.ifba.curso.dao;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.dao.GenericDao;

public class CursoDao extends GenericDao<Curso> implements CursoIDao {

    public CursoDao() {
        super(Curso.class);
    }
}
