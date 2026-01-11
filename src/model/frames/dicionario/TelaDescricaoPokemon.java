package model.frames.dicionario;

import model.pokemon.Pokemon;
import javax.swing.*;
import java.awt.*;

public class TelaDescricaoPokemon extends JDialog {
    
    public TelaDescricaoPokemon(JDialog parent, Pokemon pokemon) {
        super(parent, "Informações", true);
        setLayout(new BorderLayout(15, 15));
        
        // Fundo vermelho suave
        Color vermelhoSuave = new Color(220, 100, 100);
        
        // ===== PAINEL PRINCIPAL =====
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelPrincipal.setBackground(vermelhoSuave);
        
        // ===== GRITO =====
        adicionarInfo(painelPrincipal, "Grito:", pokemon.getSomCaracteristico());
        
        // ===== HABILIDADE =====
        adicionarInfo(painelPrincipal, "Habilidade:", pokemon.getHabilidade());
        
        // ===== DESCRIÇÃO =====
        adicionarInfo(painelPrincipal, "Descrição:", pokemon.getDescricao());
        
        // ===== BOTÃO FECHAR =====
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        
        JPanel painelBotao = new JPanel();
        painelBotao.setBackground(vermelhoSuave);
        painelBotao.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        painelBotao.add(btnFechar);
        
        // ===== ADICIONA TUDO =====
        add(painelPrincipal, BorderLayout.CENTER);
        add(painelBotao, BorderLayout.SOUTH);
        
        // ===== CONFIGURAÇÕES DA JANELA =====
        pack();
        setMinimumSize(new Dimension(380, 200));
        setMaximumSize(new Dimension(450, 400));
        setLocationRelativeTo(parent);
        getContentPane().setBackground(vermelhoSuave);
    }
    
    private void adicionarInfo(JPanel painel, String titulo, String valor) {
        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 13));
        labelTitulo.setForeground(Color.WHITE); // Texto branco
        labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel labelValor = new JLabel("<html><body style='width: 340px; color: white;'>" + valor + "</body></html>");
        labelValor.setFont(new Font("Arial", Font.PLAIN, 13));
        labelValor.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        painel.add(labelTitulo);
        painel.add(Box.createVerticalStrut(3));
        painel.add(labelValor);
        painel.add(Box.createVerticalStrut(12));
    }
}