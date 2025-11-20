package br.com.ifba.curso.view;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.service.CursoService;
import br.com.ifba.curso.dao.CursoDao;
import br.com.ifba.curso.dao.CursoIDao;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CursoView extends javax.swing.JFrame {

    private final CursoService cursoService;

    public CursoView() {
        initComponents();
        CursoIDao cursoDao = new CursoDao();
        cursoService = new CursoService(cursoDao);
        configurarEventos();
        carregarCursosNaTabela();
    }

    private void configurarEventos() {
        adicionar.addActionListener(e -> abrirTelaAdicionar());
        editar.addActionListener(e -> editarCurso());
        excluir.addActionListener(e -> excluirCurso());
        pesquisar.addActionListener(e -> pesquisarCurso());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pesquise = new javax.swing.JLabel();
        barra_pesquise = new javax.swing.JTextField();
        adicionar = new javax.swing.JButton();
        editar = new javax.swing.JButton();
        excluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        pesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pesquise.setText("Pesquise:");

        adicionar.setText("Adicionar");

        editar.setText("Editar");

        excluir.setText("Excluir");

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Descriçao"
            }
        ));
        jScrollPane1.setViewportView(tabela);

        pesquisar.setText("Pesquisar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(31, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(pesquise)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(barra_pesquise, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(excluir, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(editar, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(editar)
                        .addGap(3, 3, 3)
                        .addComponent(excluir))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pesquisar)
                        .addComponent(barra_pesquise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pesquise)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new CursoView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionar;
    private javax.swing.JTextField barra_pesquise;
    private javax.swing.JButton editar;
    private javax.swing.JButton excluir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton pesquisar;
    private javax.swing.JLabel pesquise;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables

    private void abrirTelaAdicionar() {
        String nome = JOptionPane.showInputDialog(this, "Nome do curso:");
        String descricao = JOptionPane.showInputDialog(this, "Descrição do curso:");

        if (nome == null || descricao == null) return;

        Curso curso = new Curso();
        curso.setNome(nome);
        curso.setDescricao(descricao);

        try {
            cursoService.saveCurso(curso); 
            carregarCursosNaTabela();      
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + e.getMessage());
        }
    }

    private void editarCurso() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um curso para editar.");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        Long id = Long.valueOf(model.getValueAt(linha, 0).toString());
        Curso curso = cursoService.findById(id);

        if (curso == null) {
            JOptionPane.showMessageDialog(this, "Curso não encontrado.");
            return;
        }

        String novoNome = JOptionPane.showInputDialog(this, "Novo nome:", curso.getNome());
        String novaDesc = JOptionPane.showInputDialog(this, "Nova descrição:", curso.getDescricao());

        curso.setNome(novoNome);
        curso.setDescricao(novaDesc);

        cursoService.updateCurso(curso);
        carregarCursosNaTabela();
    }

    private void excluirCurso() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um curso para excluir.");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        Long id = Long.valueOf(model.getValueAt(linha, 0).toString());

        int confirm = JOptionPane.showConfirmDialog(this, "Excluir curso?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Curso curso = cursoService.findById(id);
            cursoService.deleteCurso(curso);
            carregarCursosNaTabela();
        }
    }

    private void pesquisarCurso() {
        String termo = barra_pesquise.getText().trim();
        if (termo.isEmpty()) {
            carregarCursosNaTabela();
            return;
        }

        List<Curso> cursos = cursoService.findByName(termo);

        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);

        for (Curso c : cursos) {
            model.addRow(new Object[]{c.getId(), c.getNome(), c.getDescricao()});
        }
    }

    private void carregarCursosNaTabela() {
        List<Curso> cursos = cursoService.getAllCursos();

        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);

        for (Curso c : cursos) {
            model.addRow(new Object[]{c.getId(), c.getNome(), c.getDescricao()});
        }
    }
}
