package br.com.ifba.curso.dao;

import br.com.ifba.curso.entity.Curso;
import java.util.List;

public interface CursoIDao {
    void save(Curso curso);
    void update(Curso curso);
    void delete(Curso curso);
    Curso findById(Long id);
    List<Curso> findByName(String nome);
    List<Curso> getAll();
}
