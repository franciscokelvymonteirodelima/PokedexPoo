package model.batalha;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Gerenciador extends JPanel implements Runnable {

    // Tamanho da tela igual ao do FBatalha
    public int larguraDaTela = 1280;
    public int alturaDaTela = 720;

    int FPS = 60;
    Thread thread;

    // Imagem do poder
    public BufferedImage imagemPoder;

    // Lista de ataques ativos na tela
    ArrayList<Ataque> listaAtaques = new ArrayList<>();

    public Gerenciador() {
        this.setPreferredSize(new Dimension(larguraDaTela, alturaDaTela));
        this.setLayout(null);

        // --- IMPORTANTE: Deixa o fundo transparente para ver os Pokémons ---
        this.setOpaque(false);
        this.setBackground(new Color(0,0,0,0));
        // ------------------------------------------------------------------

        this.imagemPoder = config("/model/frames/images/FundosSimbolos/ImagemDeAtaque", 50, 50);

        // Inicia o loop visual imediatamente
        threadOn();
    }

    // Método que o FBatalha vai chamar
    public void criarAtaque(int xOrigem, int yOrigem, int xDestino, int yDestino) {
        // Cria o projetil saindo da origem indo para o destino
        Ataque novoAtaque = new Ataque(xOrigem, yOrigem, xDestino, yDestino, 15, imagemPoder); // Velocidade 15
        listaAtaques.add(novoAtaque);
    }

    public BufferedImage config(String imagemC, int largura, int altura) {
        try {
            String caminho = imagemC.startsWith("/") ? imagemC : "/" + imagemC;
            if (!caminho.endsWith(".png")) caminho += ".png";
            InputStream is = Gerenciador.class.getResourceAsStream(caminho);
            if (is == null) {
                // Se não achar, cria uma bolinha vermelha genérica para não dar erro
                BufferedImage imgErro = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);
                Graphics g = imgErro.getGraphics();
                g.setColor(Color.RED);
                g.fillOval(0,0,largura, altura);
                return imgErro;
            }
            imagemPoder = ImageIO.read(is);
            imagemPoder = redimensiona(imagemPoder, largura, altura);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagemPoder;
    }

    public BufferedImage redimensiona(BufferedImage original, int largura, int altura) {
        BufferedImage redim = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB); // ARGB para transparência
        Graphics2D g2 = redim.createGraphics();
        g2.drawImage(original, 0, 0, largura, altura, null);
        g2.dispose();
        return redim;
    }

    public void threadOn() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        double intervalo = 1000000000 / FPS;
        double delta = 0;
        long antes = System.nanoTime();
        long agora;
        while (thread != null) {
            agora = System.nanoTime();
            delta += (agora - antes) / intervalo;
            antes = agora;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        // Atualiza todos os projéteis na lista
        for (int i = 0; i < listaAtaques.size(); i++) {
            Ataque a = listaAtaques.get(i);
            a.update();

            // Remove se chegou no destino (colisão) ou saiu da tela
            if (a.destino() || a.x < 0 || a.x > larguraDaTela || a.y < 0 || a.y > alturaDaTela) {
                listaAtaques.remove(i);
                i--;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Desenha todos os ataques
        for (Ataque a : listaAtaques) {
            if (a.ataquePok != null) {
                g2.drawImage(a.ataquePok, (int)a.x, (int)a.y, null);
            }
        }
        g2.dispose();
    }
}