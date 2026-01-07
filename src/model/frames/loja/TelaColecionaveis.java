package model.frames.loja;

import model.jogador.Jogador;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaColecionaveis extends JFrame {
    private Jogador jogador;
    private DefaultTableModel model;
    private JTable tabela;
    private JLabel lblDinheiro;

    private String[] itens = {"Troféu Kanto", "Medalha Lendária"};
    private int[] precos = {1000, 2500};

    public TelaColecionaveis(Jogador jogador) {
        this.jogador = jogador;
        setTitle("Loja de Colecionáveis");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new Object[]{"Item", "Preço", "Status"}, 0);
        for (int i = 0; i < itens.length; i++) {
            model.addRow(new Object[]{itens[i], precos[i], "Disponível"});
        }

        tabela = new JTable(model);
        lblDinheiro = new JLabel("Dinheiro: $" + jogador.getDinheiro());
        JButton btnComprar = new JButton("Comprar");

        btnComprar.addActionListener(e -> {
            int row = tabela.getSelectedRow();
            if (row != -1 && jogador.gastarDinheiro(precos[row])) {
                model.setValueAt("COMPRADO", row, 2);
                lblDinheiro.setText("Dinheiro: $" + jogador.getDinheiro());
                
                // AUTO-SAVE: Salva no arquivo com o nome do jogador
                SistemaDeArquivos.salvarJogador(jogador, jogador.getNome());
                JOptionPane.showMessageDialog(this, "Sucesso! Save atualizado.");
            }
        });

        JPanel sul = new JPanel();
        sul.add(lblDinheiro);
        sul.add(btnComprar);

        add(new JScrollPane(tabela), BorderLayout.CENTER);
        add(sul, BorderLayout.SOUTH);
        setVisible(true);
    }
}