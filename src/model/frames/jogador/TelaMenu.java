package model.frames.jogador;

import model.frames.inicio.Sessao;
import model.jogador.Jogador;
import javax.swing.*;
import java.awt.*;
import model.frames.jogador.SistemaDeArquivos;

public class TelaMenu extends JFrame {
    
    // Cor vermelho suave padrão
    private static final Color VERMELHO_SUAVE = new Color(220, 100, 100);
    
    public TelaMenu() {
        configurarJanela();
        inicializarComponentes();
    }
    
    private void configurarJanela() {
        setTitle("Pokémon Manager");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Fundo vermelho suave
        getContentPane().setBackground(VERMELHO_SUAVE);
    }
    
    private void inicializarComponentes() {
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        painelPrincipal.setBackground(VERMELHO_SUAVE);
        
        // Título
        JLabel lblTitulo = new JLabel("Pokémon Manager");
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        painelPrincipal.add(lblTitulo);
        painelPrincipal.add(Box.createVerticalStrut(30));
        
        // Botões estilizados
        JButton btnNovoJogo = criarBotaoEstilizado("Novo Jogo");
        JButton btnCarregar = criarBotaoEstilizado("Carregar Jogo");
        JButton btnSair = criarBotaoEstilizado("Sair");
        
        painelPrincipal.add(btnNovoJogo);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(btnCarregar);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(btnSair);
        
        // Ações
        btnNovoJogo.addActionListener(e -> {
            model.frames.jogador.TelaCriacaoPerfil telaCriacao = new model.frames.jogador.TelaCriacaoPerfil();
            telaCriacao.setVisible(true);
            this.dispose();
        });
        btnCarregar.addActionListener(e -> {
            model.jogador.Jogador jogadorCarregado = SistemaDeArquivos.carregarComDialogo();

            if (jogadorCarregado != null) {

                Sessao.jogadorLogado = jogadorCarregado;

                new TelaJogador(jogadorCarregado).setVisible(true);

                this.dispose();
            }

        });
        btnSair.addActionListener(e -> {
            dispose();
        });
        
        add(painelPrincipal, BorderLayout.CENTER);
    }
    
    private JButton criarBotaoEstilizado(String texto) {
        JButton btn = new JButton(texto);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(200, 40));
        btn.setFont(new Font("Arial", Font.PLAIN, 14));
        btn.setBackground(Color.WHITE);
        btn.setForeground(VERMELHO_SUAVE);
        btn.setFocusPainted(false);
        return btn;
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