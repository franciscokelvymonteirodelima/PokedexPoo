package model.frames.loja;

import model.jogador.Jogador;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class TelaMenu extends JFrame {

    public TelaMenu() {
        setTitle("Pokemon Manager - Menu");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnCarregar = new JButton("Carregar Save");
        JButton btnSair = new JButton("Sair");

        btnCarregar.addActionListener(e -> {
            if (SistemaDeArquivos.existemSaves()) {
                JFileChooser chooser = new JFileChooser("saves");
                if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    Jogador j = SistemaDeArquivos.carregarJogador(chooser.getSelectedFile().getAbsolutePath());
                    if (j != null) {
                        new TelaColecionaveis(j);
                        this.dispose();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum save encontrado!");
            }
        });

        btnSair.addActionListener(e -> System.exit(0));

        add(new JLabel("POKEMON MANAGER", SwingConstants.CENTER));
        add(btnCarregar);
        add(btnSair);
    }
}