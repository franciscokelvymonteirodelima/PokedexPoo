package model.frames.jogador;

import model.jogador.Jogador;

import javax.swing.*;
import java.awt.*;

public class TelaMenu extends JFrame {

    public TelaMenu() {
        configurarJanela();
        inicializarComponentes();
    }

    private void configurarJanela() {
        setTitle("Pokémon Manager");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Título
        JLabel lblTitulo = new JLabel("Pokémon Manager");
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));

        painelPrincipal.add(lblTitulo);
        painelPrincipal.add(Box.createVerticalStrut(30));

        // Botões padrão
        JButton btnNovoJogo = new JButton("Novo Jogo");
        JButton btnCarregar = new JButton("Carregar Jogo");
        JButton btnSair = new JButton("Sair");

        btnNovoJogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCarregar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSair.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelPrincipal.add(btnNovoJogo);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(btnCarregar);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(btnSair);

        // Ações
        btnNovoJogo.addActionListener(e -> novoJogo());
        btnCarregar.addActionListener(e -> carregarJogo());
        btnSair.addActionListener(e -> System.exit(0));

        add(painelPrincipal, BorderLayout.CENTER);
    }

    private void novoJogo() {
        TelaCriacaoPerfil telaCriacao = new TelaCriacaoPerfil();
        telaCriacao.setVisible(true);
        this.dispose();
    }

    private void carregarJogo() {
        if (!SistemaDeArquivos.existemSaves()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nenhum save encontrado.\nCrie um novo jogo primeiro.",
                    "Aviso",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        Jogador jogador = SistemaDeArquivos.carregarComDialogo();

        if (jogador != null) {
            TelaJogador telaJogador = new TelaJogador(jogador);
            telaJogador.setVisible(true);
            this.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            new TelaMenu().setVisible(true);
        });
    }
}
