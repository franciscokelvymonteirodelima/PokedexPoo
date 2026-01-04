package model.frames.loja;

import model.jogador.Jogador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class TelaColecionaveis extends JFrame {

    private Jogador jogador;
    private JTable tabela;
    private DefaultTableModel model;

    private String[] nomes = {
            "Troféu Kanto",
            "Medalha Lendária",
            "Relíquia Ancestral",
            "Emblema do Campeão"
    };

    private int[] precos = {
            1000, 2500, 1800, 3000
    };

    private String[] descricoes = {
            "Conquista por explorar Kanto",
            "Item raro e exclusivo",
            "Relíquia antiga dos mestres",
            "Símbolo máximo de vitória"
    };

    private Set<String> itensComprados = new HashSet<>();

    public TelaColecionaveis(Jogador jogador) {
        this.jogador = jogador;

        setTitle("Colecionáveis & Conquistas");
        setSize(700, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        criarTabela();
        criarRodape();

        setVisible(true);
    }

    private void criarTabela() {
        model = new DefaultTableModel(
                new Object[]{"Item", "Descrição", "Preço", "Status"}, 0
        );

        for (int i = 0; i < nomes.length; i++) {
            model.addRow(new Object[]{
                    nomes[i],
                    descricoes[i],
                    precos[i],
                    "Disponível"
            });
        }

        tabela = new JTable(model);
        add(new JScrollPane(tabela), BorderLayout.CENTER);
    }

    private void criarRodape() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JLabel lblDinheiro = new JLabel("Dinheiro: $" + jogador.getDinheiro());
        JButton btnComprar = new JButton("Comprar");

        btnComprar.addActionListener(e -> comprarItem(lblDinheiro));

        painel.add(lblDinheiro);
        painel.add(btnComprar);

        add(painel, BorderLayout.SOUTH);
    }

    private void comprarItem(JLabel lblDinheiro) {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um item!");
            return;
        }

        String item = nomes[linha];
        int preco = precos[linha];

        if (itensComprados.contains(item)) {
            JOptionPane.showMessageDialog(this, "Você já possui esse item!");
            return;
        }

        if (jogador.gastarDinheiro(preco)) {
            itensComprados.add(item);
            model.setValueAt("COMPRADO", linha, 3);
            lblDinheiro.setText("Dinheiro: $" + jogador.getDinheiro());
            JOptionPane.showMessageDialog(this, "Item colecionável adquirido!");
        }
    }
}
