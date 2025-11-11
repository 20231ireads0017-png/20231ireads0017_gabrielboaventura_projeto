package br.com.ifba.turma.view;

import br.com.ifba.curso.dao.CursoDao;
import br.com.ifba.curso.entity.Curso;
import br.com.ifba.turma.dao.TurmaDao;
import br.com.ifba.turma.entity.Turma;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TurmaForm extends JFrame {

    private final TurmaDao turmaDao = new TurmaDao();
    private final CursoDao cursoDao = new CursoDao();

    private JTextField txtNome;
    private JTextField txtAno;
    private JComboBox<Curso> cmbCurso;
    private JButton btnSalvar;

    private final Turma turma; // null = novo, != null = editar

    public TurmaForm(Turma turma) {
        this.turma = turma;
        setTitle(turma == null ? "Nova Turma" : "Editar Turma");
        setSize(420, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        init();
        if (turma != null) carregarCampos();
    }

    private void init() {
        setLayout(new BorderLayout(8,8));

        JPanel form = new JPanel(new GridLayout(3,2,5,5));
        txtNome = new JTextField();
        txtAno = new JTextField();

        cmbCurso = new JComboBox<>();
        carregarCursos();

        form.add(new JLabel("Nome:"));
        form.add(txtNome);
        form.add(new JLabel("Ano:"));
        form.add(txtAno);
        form.add(new JLabel("Curso:"));
        form.add(cmbCurso);

        add(form, BorderLayout.CENTER);

        btnSalvar = new JButton("Salvar");
        JPanel bot = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bot.add(btnSalvar);
        add(bot, BorderLayout.SOUTH);

        btnSalvar.addActionListener(e -> salvar());
    }

    private void carregarCursos() {
        DefaultComboBoxModel<Curso> model = new DefaultComboBoxModel<>();
        List<Curso> cursos = cursoDao.findAll();
        for (Curso c : cursos) model.addElement(c);
        cmbCurso.setModel(model);
        cmbCurso.setRenderer((list, value, index, isSelected, cellHasFocus) ->
                new JLabel(value != null ? value.getNome() : ""));
    }

    private void carregarCampos() {
        txtNome.setText(turma.getNome());
        txtAno.setText(String.valueOf(turma.getAno()));
        if (turma.getCurso() != null) {
            // seleciona curso correspondente
            ComboBoxModel<Curso> model = cmbCurso.getModel();
            for (int i = 0; i < model.getSize(); i++) {
                Curso c = model.getElementAt(i);
                if (c.getId().equals(turma.getCurso().getId())) {
                    cmbCurso.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    private void salvar() {
        try {
            String nome = txtNome.getText().trim();
            int ano = Integer.parseInt(txtAno.getText().trim());
            Curso curso = (Curso) cmbCurso.getSelectedItem();

            if (turma == null) {
                Turma t = new Turma();
                t.setNome(nome);
                t.setAno(ano);
                t.setCurso(curso);
                turmaDao.save(t);
                JOptionPane.showMessageDialog(this, "Turma salva!");
            } else {
                turma.setNome(nome);
                turma.setAno(ano);
                turma.setCurso(curso);
                turmaDao.update(turma);
                JOptionPane.showMessageDialog(this, "Turma atualizada!");
            }
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ano inválido.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}
