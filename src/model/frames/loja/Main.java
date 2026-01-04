package model.frames.loja;

import model.jogador.Jogador;

public class Main {
    public static void main(String[] args) {
        Jogador jogador = new Jogador("Red");
        new TelaColecionaveis(jogador);
    }
}
