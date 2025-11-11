package br.com.ifba.turma.view;

import br.com.ifba.turma.dao.TurmaDao;
import br.com.ifba.turma.entity.Turma;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TurmaView extends JFrame {

    private final TurmaDao turmaDao = new TurmaDao();

    private JTextField txtBuscar;
    private JButton btnNovo;
    private JTable tbl;
    private DefaultTableModel model;

    public TurmaView() {
        setTitle("Turmas");
        setSize(900, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        carregarTabela(turmaDao.findAll());
    }

    private void initComponents() {
        setLayout(new BorderLayout(8,8));

        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtBuscar = new JTextField(30);
        JButton btnBuscar = new JButton("Buscar");
        btnNovo = new JButton("＋");
        btnNovo.setToolTipText("Adicionar nova turma");

        topo.add(new JLabel("Pesquisar:"));
        topo.add(txtBuscar);
        topo.add(btnBuscar);
        topo.add(Box.createHorizontalStrut(20));
        topo.add(btnNovo);
        add(topo, BorderLayout.NORTH);

        model = new DefaultTableModel(new Object[]{"ID","Nome","Ano","Curso","✏","🗑"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tbl = new JTable(model);
        tbl.setRowHeight(24);
        add(new JScrollPane(tbl), BorderLayout.CENTER);

        btnNovo.addActionListener(e -> {
            TurmaForm form = new TurmaForm(null);
            form.setVisible(true);
            form.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    carregarTabela(turmaDao.findAll());
                }
            });
        });

        btnBuscar.addActionListener(e -> buscar());

        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tbl.rowAtPoint(e.getPoint());
                int col = tbl.columnAtPoint(e.getPoint());
                if (row < 0) return;
                Long id = (Long) model.getValueAt(row, 0);

                if (col == 4) { // editar
                    Turma t = turmaDao.findById(id);
                    TurmaForm form = new TurmaForm(t);
                    form.setVisible(true);
                    form.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent e) {
                            carregarTabela(turmaDao.findAll());
                        }
                    });
                } else if (col == 5) { // excluir
                    int opt = JOptionPane.showConfirmDialog(TurmaView.this,
                            "Excluir turma ID " + id + " ?",
                            "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (opt == JOptionPane.YES_OPTION) {
                        Turma t = turmaDao.findById(id);
                        if (t != null) {
                            turmaDao.delete(t);
                            carregarTabela(turmaDao.findAll());
                            JOptionPane.showMessageDialog(TurmaView.this, "Turma excluída.");
                        }
                    }
                }
            }
        });
    }

    private void buscar() {
        String termo = txtBuscar.getText().trim().toLowerCase();
        if (termo.isEmpty()) {
            carregarTabela(turmaDao.findAll());
            return;
        }
        List<Turma> lista = turmaDao.findAll().stream()
                .filter(t -> (t.getNome() != null && t.getNome().toLowerCase().contains(termo))
                        || String.valueOf(t.getAno()).contains(termo)
                        || (t.getCurso() != null && t.getCurso().getNome() != null && t.getCurso().getNome().toLowerCase().contains(termo)))
                .toList();
        carregarTabela(lista);
    }

    private void carregarTabela(List<Turma> lista) {
        model.setRowCount(0);
        for (Turma t : lista) {
            Object[] row = new Object[]{
                    t.getId(),
                    t.getNome(),
                    t.getAno(),
                    t.getCurso() != null ? t.getCurso().getNome() : "",
                    "✏",
                    "🗑"
            };
            model.addRow(row);
        }
    }
}
