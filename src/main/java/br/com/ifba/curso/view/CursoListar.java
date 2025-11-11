package br.com.ifba.curso.view;

import br.com.ifba.curso.dao.CursoDao;
import br.com.ifba.curso.entity.Curso;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CursoListar extends JFrame {

    private final CursoDao cursoDao = new CursoDao();
    private JTable tabela;
    private DefaultTableModel modelo;

    public CursoListar() {
        setTitle("Lista de Cursos");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        modelo = new DefaultTableModel(new Object[]{"ID", "Nome", "Descrição"}, 0);
        tabela = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll, BorderLayout.CENTER);

        listarCursos();
    }

    private void listarCursos() {
        modelo.setRowCount(0); // limpa tabela
        List<Curso> cursos = cursoDao.findAll();
        for (Curso c : cursos) {
            modelo.addRow(new Object[]{c.getId(), c.getNome(), c.getDescricao()});
        }
    }
}
