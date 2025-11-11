package br.com.ifba.curso.view;

import br.com.ifba.curso.dao.CursoDao;
import br.com.ifba.curso.entity.Curso;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CursoView extends JFrame {

    private final CursoDao cursoDao = new CursoDao();

    private JTextField txtBuscar;
    private JButton btnNovo;
    private JTable tbl;
    private DefaultTableModel model;

    public CursoView() {
        setTitle("Cursos");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        carregarTabela(cursoDao.findAll());
    }

    private void initComponents() {
        setLayout(new BorderLayout(8, 8));

        // topo: busca + novo
        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtBuscar = new JTextField(30);
        JButton btnBuscar = new JButton("Buscar");
        btnNovo = new JButton("＋"); // símbolo de adicionar
        btnNovo.setToolTipText("Adicionar novo curso");

        topo.add(new JLabel("Pesquisar:"));
        topo.add(txtBuscar);
        topo.add(btnBuscar);
        topo.add(Box.createHorizontalStrut(20));
        topo.add(btnNovo);

        add(topo, BorderLayout.NORTH);

        // tabela
        model = new DefaultTableModel(new Object[]{"ID", "Nome", "Descrição", "✏", "🗑"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // não editar direto
            }
        };
        tbl = new JTable(model);
        tbl.setRowHeight(24);
        add(new JScrollPane(tbl), BorderLayout.CENTER);

        // ações
        btnNovo.addActionListener(e -> {
            CursoForm form = new CursoForm(null);
            form.setVisible(true);
            form.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    carregarTabela(cursoDao.findAll());
                }
            });
        });

        btnBuscar.addActionListener(e -> buscar());

        // clique em editar/excluir nas colunas
        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tbl.rowAtPoint(e.getPoint());
                int col = tbl.columnAtPoint(e.getPoint());
                if (row < 0) return;
                Long id = (Long) model.getValueAt(row, 0);

                if (col == 3) { // editar
                    CursoForm form = new CursoForm(cursoDao.findById(id));
                    form.setVisible(true);
                    form.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent e) {
                            carregarTabela(cursoDao.findAll());
                        }
                    });
                } else if (col == 4) { // excluir
                    int opt = JOptionPane.showConfirmDialog(CursoView.this,
                            "Excluir curso ID " + id + " ?",
                            "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (opt == JOptionPane.YES_OPTION) {
                        Curso c = cursoDao.findById(id);
                        if (c != null) {
                            cursoDao.delete(c);
                            carregarTabela(cursoDao.findAll());
                            JOptionPane.showMessageDialog(CursoView.this, "Curso excluído.");
                        }
                    }
                }
            }
        });
    }

    private void buscar() {
        String termo = txtBuscar.getText().trim().toLowerCase();
        if (termo.isEmpty()) {
            carregarTabela(cursoDao.findAll());
            return;
        }
        List<Curso> lista = cursoDao.findAll().stream()
                .filter(c -> (c.getNome() != null && c.getNome().toLowerCase().contains(termo))
                        || (c.getDescricao() != null && c.getDescricao().toLowerCase().contains(termo)))
                .toList();
        carregarTabela(lista);
    }

    private void carregarTabela(List<Curso> lista) {
        model.setRowCount(0);
        for (Curso c : lista) {
            Object[] row = new Object[]{
                    c.getId(),
                    c.getNome(),
                    c.getDescricao(),
                    "✏",
                    "🗑"
            };
            model.addRow(row);
        }
    }
}
