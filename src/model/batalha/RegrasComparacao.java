package model.batalha;

import model.pokemon.Pokemon;

public abstract class RegrasComparacao {

    public double calcularMediaStatus(Pokemon pokemon) {
        int hp = pokemon.getHp();
        int ataque = pokemon.getAtaque();
        int defesa = pokemon.getDefesa();
        int spAtaque = pokemon.getSpAtaque();
        int spDefesa = pokemon.getSpDefesa();
        int velocidade = pokemon.getVelocidade();

        int somaStatus = hp + ataque + defesa + spAtaque + spDefesa + velocidade;
        return somaStatus / 6.0;
    }
}
