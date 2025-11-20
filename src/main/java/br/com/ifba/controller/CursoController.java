package br.com.ifba.controller;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.service.CursoIService;

import java.util.List;

public class CursoController implements CursoIController {

    private final CursoIService cursoService;

    public CursoController(CursoIService cursoService) {
        this.cursoService = cursoService;
    }

    @Override
    public void criarCurso(String nome) {
        cursoService.saveCurso(new Curso(nome));
    }

    @Override
    public void atualizarCurso(Curso curso, String novoNome) {
        curso.setNome(novoNome);
        cursoService.updateCurso(curso);
    }

    @Override
    public void deletarCurso(Curso curso) {
        cursoService.deleteCurso(curso);
    }

    @Override
    public List<Curso> listarCursos() {
        return cursoService.getAllCursos();
    }
}
