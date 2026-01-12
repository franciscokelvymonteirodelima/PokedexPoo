package model.frames.loja;

import model.jogador.Jogador;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TelaMenu extends JFrame {
    
    // Cores padronizadas
    private static final Color COR_FUNDO = new Color(220, 100, 100);
    private static final Color COR_BOTAO = Color.WHITE;
    private static final Color COR_TEXTO_BOTAO = new Color(180, 80, 80);
    private static final Color COR_BORDA = new Color(180, 80, 80);
    private static final Color COR_TITULO = Color.WHITE;

    public TelaMenu() {
        setTitle("Pokemon Manager - Menu");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Define cor de fundo
        getContentPane().setBackground(COR_FUNDO);

        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelPrincipal.setBackground(COR_FUNDO);

        // Título
        JLabel lblTitulo = new JLabel("POKEMON MANAGER");
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setForeground(COR_TITULO);

        // Botões
        JButton btnCarregar = criarBotao("Carregar Save");
        JButton btnSair = criarBotao("Sair");

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

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setBackground(COR_BOTAO);
        botao.setForeground(COR_TEXTO_BOTAO);
        botao.setFocusPainted(false);
        botao.setFont(new Font("SansSerif", Font.BOLD, 14));
        
        Dimension tamanhoBotao = new Dimension(180, 35);
        botao.setMaximumSize(tamanhoBotao);
        botao.setPreferredSize(tamanhoBotao);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        botao.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COR_BORDA, 2),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        
        // Efeito hover
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(new Color(255, 220, 220));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(COR_BOTAO);
            }
        });
        
        return botao;
    }
}