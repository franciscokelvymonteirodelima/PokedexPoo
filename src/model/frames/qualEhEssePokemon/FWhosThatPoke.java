package model.frames.qualEhEssePokemon;

import javax.swing.*;
import java.awt.*;

public class FWhosThatPoke extends JFrame {

    public FWhosThatPoke() {
        setTitle("Who's that Pokemon");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // ===== Fundo =====
        ImageIcon fundoPrincipal = new ImageIcon("/home/jeudes/PokedexPoo-main/src/model/frames/images/FUNDO_PRINCIPAL_MINIGAME.jpg");
        JLabel background = new JLabel(fundoPrincipal);
        background.setBounds(0, 0, 1280, 720);
        add(background);
        background.setLayout(null);

        // ===== Título =====
        JLabel titulo = new JLabel("Who's that Pokemon", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(0, 10, 1280, 40);
        add(titulo);

        // ===== Campo para Inserir o Pokemon =====
        JTextArea campoTexto = new JTextArea("Insira o nome do Pokemon");
        campoTexto.setLayout(new BorderLayout());
        campoTexto.setFont(new Font("Arial", Font.PLAIN, 24));
        campoTexto.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        campoTexto.setBounds(250, 60, 600, 50);

        add(campoTexto);

        // ===== Acertos e erros =====
        int acertos = 0;
        int total = 10;

        // ===== Score =====
        JLabel score = new JLabel("Score : " + acertos + " / " + total, SwingConstants.CENTER);
        score.setFont(new Font("Arial", Font.PLAIN, 16));
        score.setBounds(850, 70, 200, 30);
        score.setFont(score.getFont().deriveFont(Font.BOLD, 24f));
        add(score);

        // ===== Imagem do Pokemon (usada como fundo) =====
        ImageIcon imagemPokemon = new ImageIcon("/home/jeudes/PokedexPoo-main/src/model/frames/images/FUNDO_MINIGAME.jpg");
        Image img = imagemPokemon.getImage().getScaledInstance(880, 350, Image.SCALE_SMOOTH);
        imagemPokemon = new ImageIcon(img);

        // ===== Painel do Pokemon =====
        JLabel painelPokemon = new JLabel(imagemPokemon);
        painelPokemon.setLayout(new BorderLayout());
        painelPokemon.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
        painelPokemon.setBounds(200, 140, 880, 350);

        add(painelPokemon);

        // ===== BOTÕES NUMERADOS (1 a 10) =====
        int xInicial = 155;
        int y = 500;
        int largura = 80;
        int altura = 80;
        int espaco = 10;

        for (int i = 1; i <= 11; i++) {
            if(i == 11) {
                JButton btnFinalizar = new JButton("END");
                btnFinalizar.setBounds(xInicial + 10 * (largura + espaco), y, largura, altura);
                btnFinalizar.setFont(new Font("Arial", Font.BOLD, 20));
                btnFinalizar.setBackground(Color.BLACK);
                btnFinalizar.setForeground(Color.WHITE);
                add(btnFinalizar);
                break;
            }
            JButton btn = new JButton(String.valueOf(i));
            btn.setBounds(xInicial + (i - 1) * (largura + espaco), y, largura, altura);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            add(btn);
        }

        // ===== Painel do Pokemon Aleatório =====
        PokemonAleatorioPanel painelPokemonAleatorio = new PokemonAleatorioPanel();
        painelPokemonAleatorio.setBounds(240, 180, 800, 300);
        painelPokemon.add(painelPokemonAleatorio);
    }

    public static void main(String[] args) {
        FWhosThatPoke frame = new FWhosThatPoke();
        frame.setVisible(true);
    }
}
