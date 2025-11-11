package br.com.ifba.curso.view;

import br.com.ifba.curso.dao.CursoDao;
import br.com.ifba.curso.entity.Curso;

import javax.swing.*;
import java.awt.*;

public class CursoForm extends JFrame {

    private final CursoDao cursoDao = new CursoDao();
    private final Curso curso; // null = novo, não-nulo = editar

    private JTextField txtNome;
    private JTextArea txtDescricao;
    private JButton btnSalvar;

    public CursoForm(Curso curso) {
        this.curso = curso;
        setTitle(curso == null ? "Novo Curso" : "Editar Curso");
        setSize(420, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        init();
        if (curso != null) carregarCampos();
    }

    private void init() {
        setLayout(new BorderLayout(8, 8));
        JPanel form = new JPanel(new GridLayout(4, 1, 5, 5));

        txtNome = new JTextField();
        txtDescricao = new JTextArea(6, 20);
        txtDescricao.setLineWrap(true);
        txtDescricao.setWrapStyleWord(true);

        form.add(labeled("Nome:", txtNome));
        form.add(labeled("Descrição:", new JScrollPane(txtDescricao)));

        add(form, BorderLayout.CENTER);

        btnSalvar = new JButton("Salvar");
        JPanel bot = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bot.add(btnSalvar);
        add(bot, BorderLayout.SOUTH);

        btnSalvar.addActionListener(e -> salvar());
    }

    private JPanel labeled(String label, Component comp) {
        JPanel p = new JPanel(new BorderLayout(5, 5));
        p.add(new JLabel(label), BorderLayout.NORTH);
        p.add(comp, BorderLayout.CENTER);
        return p;
    }

    private void carregarCampos() {
        txtNome.setText(curso.getNome());
        txtDescricao.setText(curso.getDescricao());
    }

    private void salvar() {
        try {
            if (curso == null) {
                Curso c = new Curso();
                c.setNome(txtNome.getText().trim());
                c.setDescricao(txtDescricao.getText().trim());
                cursoDao.save(c);
                JOptionPane.showMessageDialog(this, "Curso salvo!");
            } else {
                curso.setNome(txtNome.getText().trim());
                curso.setDescricao(txtDescricao.getText().trim());
                cursoDao.update(curso);
                JOptionPane.showMessageDialog(this, "Curso atualizado!");
            }
            dispose(); // fecha e sinaliza para a view recarregar
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}
