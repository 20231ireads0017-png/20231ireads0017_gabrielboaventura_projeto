package br.com.ifba.curso.dao;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.dao.GenericDao;
import java.util.List;

public class CursoDao extends GenericDao<Curso, Long> implements CursoIDao {

    public CursoDao() {
        super(Curso.class);
    }

    @Override
    public List<Curso> findByName(String nome) {
        return em.createQuery("FROM Curso c WHERE c.nome LIKE :nome", Curso.class)
                 .setParameter("nome", "%" + nome + "%")
                 .getResultList();
    }
}
