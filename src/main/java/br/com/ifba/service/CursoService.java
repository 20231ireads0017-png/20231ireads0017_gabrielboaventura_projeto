package br.com.ifba.service;

import br.com.ifba.curso.dao.CursoIDao;
import br.com.ifba.curso.entity.Curso;
import java.util.List;

public class CursoService implements CursoIService {

    private final CursoIDao cursoDao;

    public CursoService(CursoIDao cursoDao) {
        this.cursoDao = cursoDao;
    }

    @Override
    public void saveCurso(Curso curso) {
        cursoDao.save(curso);
    }

    @Override
    public void updateCurso(Curso curso) {
        cursoDao.update(curso);
    }

    @Override
    public void deleteCurso(Curso curso) {
        cursoDao.delete(curso);
    }

    @Override
    public Curso findById(Long id) {
        return cursoDao.findById(id);
    }

    @Override
    public List<Curso> findByName(String nome) {
        return cursoDao.findByName(nome);
    }

    @Override
    public List<Curso> getAllCursos() {
        return cursoDao.getAll();
    }
}
