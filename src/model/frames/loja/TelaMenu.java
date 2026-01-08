package model.frames.loja;

import model.jogador.Jogador;
import javax.swing.*;
import java.awt.*;

public class TelaMenu extends JFrame {

    public TelaMenu() {
        setTitle("Pokemon Manager - Menu");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel lblTitulo = new JLabel("POKEMON MANAGER");
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));

        // Botões
        JButton btnCarregar = new JButton("Carregar Save");
        JButton btnSair = new JButton("Sair");

        Dimension tamanhoBotao = new Dimension(160, 30);

        btnCarregar.setMaximumSize(tamanhoBotao);
        btnSair.setMaximumSize(tamanhoBotao);

        btnCarregar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSair.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ações
        btnCarregar.addActionListener(e -> {
            if (SistemaDeArquivos.existemSaves()) {
                JFileChooser chooser = new JFileChooser("saves");
                if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    Jogador j = SistemaDeArquivos.carregarJogador(
                            chooser.getSelectedFile().getAbsolutePath()
                    );
                    if (j != null) {
                        new TelaColecionaveis(j);
                        dispose();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum save encontrado!");
            }
        });

        btnSair.addActionListener(e -> System.exit(0));

        // Montagem
        painelPrincipal.add(lblTitulo);
        painelPrincipal.add(Box.createVerticalStrut(30));
        painelPrincipal.add(btnCarregar);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(btnSair);

        add(painelPrincipal);
        setVisible(true);
    }
}
