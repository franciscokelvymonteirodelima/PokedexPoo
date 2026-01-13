package model.batalha;

import java.awt.image.BufferedImage;

public class Ataque {

    double x, y, dx, dy, alvoX, alvoY;
    BufferedImage ataquePok;

    public Ataque(int origemX, int origemY, int alvoX, int alvoY, double velocidade, BufferedImage ataquePok){
        this.x = origemX;
        this.y = origemY;
        this.alvoX = alvoX;
        this.alvoY = alvoY;
        this.ataquePok = ataquePok;

        // Cálculo usando a distância entre pontos
        double dX = alvoX - origemX;
        double dY = alvoY - origemY;
        double distancia = Math.sqrt(dX * dX + dY * dY);

        // Direção
        if (distancia > 0) {
            this.dx = (dX / distancia) * velocidade;
            this.dy = (dY / distancia) * velocidade;
        }

    }
    public void update() {
        x += dx;
        y += dy;
    }
    public boolean destino() {
        double dX = x - alvoX;
        double dY = y - alvoY;
        double distanciaRestante = Math.sqrt(dX * dX + dY * dY);

        return distanciaRestante < 10;
    }
}