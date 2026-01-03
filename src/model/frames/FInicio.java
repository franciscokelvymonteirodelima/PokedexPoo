package model.frames;
import javax.swing.*;
import java.awt.*;

public class FInicio extends JFrame {
    public FInicio() {
        setTitle("MENU");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        // ===== Fundo =====
        ImageIcon fundoMenu = new ImageIcon("/home/jeudes/PokedexPoo-main/src/model/frames/images/FUNDO_MENU.jpg");
        JLabel background = new JLabel(fundoMenu);
        background.setBounds(0, 0, 1280, 720);
        setContentPane(background);
        setLayout(null);

        // ===== Nome do Site =====
        ImageIcon iconePokemon = new ImageIcon("/home/jeudes/PokedexPoo-main/src/model/frames/images/LogoInicio_400x.png");
        JLabel labelIcone = new JLabel(iconePokemon);
        labelIcone.setBounds(320, 10, 640, 120);
        add(labelIcone);
        // JLabel nomeSite = new JLabel("Nome do Site", SwingConstants.CENTER);
        // nomeSite.setFont(new Font("Arial", Font.BOLD, 36));
        // nomeSite.setBounds(0, 10, 1280, 60);
        // add(nomeSite);

        // ===== BOTÕES DO MENU =====
        int larguraBotao = 500;
        int alturaBotao = 55;
        int xBotao = (1280 - larguraBotao) / 2;
        int yInicial = 140;
        int espaco = 20;

        String[] textos = {
            "Pokedex",
            "Batalha Pokemon",
            "Qual é esse Pokemon? (MiniGame)",
            "SHooping",
            "jogo",
            "joosjos"
        };

        for (int i = 0; i < textos.length; i++) {
            JButton botao = new JButton(textos[i]);
            botao.setBounds(xBotao, yInicial + i * (alturaBotao + espaco), larguraBotao, alturaBotao);
            botao.setFont(new Font("Arial", Font.BOLD, 20));
            botao.setForeground(Color.WHITE);
            add(botao);
            if(i == 0) {
                botao.setBackground(Color.RED); // Vermelho para Batalha Pokemon
            } else if(i == 1) {
                botao.setBackground(Color.BLUE); // Azul para MiniGame
            } else if(i == 2){
                botao.setBackground(Color.GREEN); // Verde para Pokedex
            } else if(i == 3){
                botao.setBackground(Color.PINK); //  para Shopping
            } else if(i == 4){
                botao.setBackground(Color.ORANGE); // Laranja
            } else if(i == 5){
                botao.setBackground(Color.MAGENTA); // Magenta para os outros
            }
        }
    }

    public static void main(String[] args) {
        FInicio frame = new FInicio();
        frame.setVisible(true);
    }
}