package br.com.ifba.turma.dao;

import br.com.ifba.turma.entity.Turma;
import br.com.ifba.infrastructure.dao.GenericDao;

public class TurmaDao extends GenericDao<Turma> implements TurmaIDao {

    public TurmaDao() {
        super(Turma.class);
    }
}
