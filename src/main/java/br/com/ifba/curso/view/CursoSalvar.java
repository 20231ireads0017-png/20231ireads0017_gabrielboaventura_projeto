package br.com.ifba.curso.view;

import br.com.ifba.curso.dao.CursoDao;
import br.com.ifba.curso.entity.Curso;

import javax.swing.*;
import java.awt.*;

public class CursoSalvar extends JFrame {

    private final CursoDao cursoDao = new CursoDao();

    private JTextField txtNome;
    private JTextField txtDescricao;
    private JButton btnSalvar;

    public CursoSalvar() {
        setTitle("Cadastrar Curso");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setLayout(new GridLayout(3, 2, 10, 10));

        txtNome = new JTextField();
        txtDescricao = new JTextField();
        btnSalvar = new JButton("Salvar");

        add(new JLabel("Nome:"));
        add(txtNome);

        add(new JLabel("Descrição:"));
        add(txtDescricao);

        add(new JLabel());
        add(btnSalvar);

        btnSalvar.addActionListener(e -> salvarCurso());
    }

    private void salvarCurso() {
        String nome = txtNome.getText();
        String descricao = txtDescricao.getText();

        if (nome.isEmpty() || descricao.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Curso curso = new Curso();
        curso.setNome(nome);
        curso.setDescricao(descricao);

        cursoDao.save(curso);  // salva no banco

        JOptionPane.showMessageDialog(this, "Curso salvo com sucesso!");
        txtNome.setText("");
        txtDescricao.setText("");
    }
}
