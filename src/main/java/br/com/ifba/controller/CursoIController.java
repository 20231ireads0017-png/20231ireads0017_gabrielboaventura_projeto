package br.com.ifba.controller;

import br.com.ifba.curso.entity.Curso;

import java.util.List;

public interface CursoIController {
    void criarCurso(String nome);
    void atualizarCurso(Curso curso, String novoNome);
    void deletarCurso(Curso curso);
    List<Curso> listarCursos();
}
