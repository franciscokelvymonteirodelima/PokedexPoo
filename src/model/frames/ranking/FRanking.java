package model.frames.ranking;

import javax.swing.*;
import java.awt.*;

public class FRanking extends JFrame {
    public FRanking() {
        setTitle("Ranking");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // ===== Imagem do Fundo Principal =====
        ImageIcon fundoPrincipal = new ImageIcon(getClass().getResource("/model/frames/images/fundo_ranking.png"));
        JLabel background = new JLabel(fundoPrincipal);
        background.setBounds(0, 0, 1280, 720);
        background.setLayout(null);
        setContentPane(background);
        add(background);

        
    }
}
