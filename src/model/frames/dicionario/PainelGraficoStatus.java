package model.frames.dicionario;

import model.pokemon.Pokemon;
import javax.swing.*;
import java.awt.*;

public class PainelGraficoStatus extends JPanel {
    private Pokemon pokemon;
    private static final int ALTURA_BARRA = 22;
    private static final int ESPACO = 18;
    private static final int Y_INICIAL = 40;
    private static final int MARGEM_INFERIOR = 40; // Aumentei de 30 para 40
    private static final int LARGURA_PANEL = 460;
    private static final int NUM_BARRAS = 6;
    
    public PainelGraficoStatus(Pokemon pokemon) {
        this.pokemon = pokemon;
        setBackground(Color.WHITE);
    }
    
    @Override
    public Dimension getPreferredSize() {
        int alturaTotal = Y_INICIAL + (NUM_BARRAS * ALTURA_BARRA) + 
                          ((NUM_BARRAS - 1) * ESPACO) + MARGEM_INFERIOR;
        
        return new Dimension(LARGURA_PANEL, alturaTotal);
    }
    
    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize(); // Garante que o tamanho mínimo é respeitado
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (pokemon == null) return;
        
        Graphics2D g2 = (Graphics2D) g;
        // Antialiasing
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        
        int[] valores = {
                pokemon.getHp(),
                pokemon.getAtaque(),
                pokemon.getDefesa(),
                pokemon.getSpAtaque(),
                pokemon.getSpDefesa(),
                pokemon.getVelocidade()
        };
        
        String[] labels = {"HP", "ATK", "DEF", "SPA", "SPD", "VEL"};
        
        Color[] cores = {
                new Color(220, 20, 60),   // HP
                new Color(255, 140, 0),   // ATK
                new Color(65, 105, 225),  // DEF              
                new Color(138, 43, 226),  // SPA
                new Color(255, 215, 0),   // SPD
                new Color(60, 179, 113)   // VEL
        };
        
        int xInicial = 90;
        int larguraMax = 220;
        
        // Maior valor para escala
        int maxValor = 1;
        for (int v : valores) {
            if (v > maxValor) maxValor = v;
        }
        
        // Título
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.drawString("Status Base", 180, 20); // Centralizei melhor
        
        g2.setFont(new Font("Arial", Font.PLAIN, 12));
        
        for (int i = 0; i < valores.length; i++) {
            int largura = (int) ((double) valores[i] / maxValor * larguraMax);
            int y = Y_INICIAL + i * (ALTURA_BARRA + ESPACO);
            
            // Label
            g2.setColor(Color.BLACK);
            g2.drawString(labels[i], 30, y + 15);
            
            // Barra
            g2.setColor(cores[i]);
            g2.fillRoundRect(
                    xInicial,
                    y,
                    largura,
                    ALTURA_BARRA,
                    8,
                    8
            );
            
            // Borda
            g2.setColor(Color.BLACK);
            g2.drawRoundRect(
                    xInicial,
                    y,
                    largura,
                    ALTURA_BARRA,
                    8,
                    8
            );
            
            // Valor
            g2.drawString(
                    String.valueOf(valores[i]),
                    xInicial + largura + 8,
                    y + 15
            );
        }
    }
}