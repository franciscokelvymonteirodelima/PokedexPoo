package model.frames.qualEhEssePokemon;

import model.pokedex.Pokedex;
import model.pokemon.Pokemon;

public class GamePokemon {

    private Pokemon[] rodadas = new Pokemon[10];
    private int rodadaAtual = -1;
    private int acertos = 0;

    // ===== Construtor que inicializa as rodadas com Pokémons aleatórios =====
    public GamePokemon() {
        Pokedex pokedex = new Pokedex();

        for (int i = 0; i < 10; i++) {
            int poke = (int) (Math.random() * 151) + 1;
            rodadas[i] = pokedex.getPokemonPC(poke);
        }
    }

    // ===== Seleciona a rodada atual e retorna o Pokemon correspondente =====
    public Pokemon selecionarRodada(int index) {
        rodadaAtual = index;
        return rodadas[index];
    }

    // ===== Verifica se a resposta do jogador está correta =====
    public boolean verificarResposta(String resposta) {
        if (rodadaAtual == -1) 
            return false;

        Pokemon p = rodadas[rodadaAtual];
        return resposta.trim().equalsIgnoreCase(p.getNome());
    }

    // ===== Incrementa o número de acertos =====
    public void setAcertos() {
        acertos++;
    }

    // ===== Getters =====
    public int getAcertos() {
        return acertos;
    }

    public int getTotal() {
        return rodadas.length;
    }

    public Pokemon getPokemonAtual() {
        return rodadas[rodadaAtual];
    }
}

