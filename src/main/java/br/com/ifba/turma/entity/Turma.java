package br.com.ifba.turma.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import br.com.ifba.curso.entity.Curso;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Turma extends PersistenceEntity {

    private String nome;
    private int ano;

    @ManyToOne
    private Curso curso;

    public Turma() {}

    public Turma(String nome, int ano, Curso curso) {
        this.nome = nome;
        this.ano = ano;
        this.curso = curso;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }
}
