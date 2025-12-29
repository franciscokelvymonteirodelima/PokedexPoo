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
            "",
            "",
            ""
        };

        for (int i = 0; i < textos.length; i++) {
            JButton botao = new JButton(textos[i]);
            botao.setBounds(xBotao, yInicial + i * (alturaBotao + espaco), larguraBotao, alturaBotao);
            botao.setFont(new Font("Arial", Font.BOLD, 20));
            add(botao);
        }
    }

    public static void main(String[] args) {
        FInicio frame = new FInicio();
        frame.setVisible(true);
    }
}