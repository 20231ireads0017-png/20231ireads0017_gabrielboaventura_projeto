package br.com.ifba.main;

import br.com.ifba.curso.view.CursoView;
import br.com.ifba.turma.view.TurmaView;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        setTitle("Menu Principal");
        setSize(320, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 5, 5));

        JButton btnCursos = new JButton("Gerenciar Cursos");
        JButton btnTurmas = new JButton("Gerenciar Turmas");
        JButton btnSair = new JButton("Sair");

        btnCursos.addActionListener(e -> new CursoView().setVisible(true));
        btnTurmas.addActionListener(e -> new TurmaView().setVisible(true));
        btnSair.addActionListener(e -> System.exit(0));

        add(new JLabel("Escolha um módulo:", SwingConstants.CENTER));
        add(btnCursos);
        add(btnTurmas);

        setVisible(true);
    }

    public static void main(String[] args) {
        // roda na EDT
        SwingUtilities.invokeLater(Main::new);
    }
}

