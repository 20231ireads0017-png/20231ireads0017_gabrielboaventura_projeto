package br.com.ifba.service;

import br.com.ifba.curso.entity.Curso;
import java.util.List;

public interface CursoIService {
    void saveCurso(Curso curso);
    void updateCurso(Curso curso);
    void deleteCurso(Curso curso);
    Curso findById(Long id);
    List<Curso> findByName(String nome);
    List<Curso> getAllCursos();
}
