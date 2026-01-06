package model.frames.qualEhEssePokemon;

import model.pokedex.Pokedex;
import model.pokemon.Pokemon;

public class GamePokemon {

    private Pokemon[] rodadas = new Pokemon[10];
    private int rodadaAtual = -1;
    private int acertos = 0;
    private boolean[] rodadasRespondidas = new boolean[10];

    // ===== Construtor que inicializa as rodadas com Pokémons aleatórios =====
    public GamePokemon() {
        Pokedex pokedex = new Pokedex();

        for (int i = 0; i < 10; i++) {
            int poke = (int) (Math.random() * 151) + 1;
            Pokemon pokemon = pokedex.getPokemonPC(poke);
            int tentativas = 0;
            while(pokemon == null && tentativas < 10){
                poke = (int) (Math.random() * 151) + 1;
                pokemon = pokedex.getPokemonPC(poke);
                tentativas++;
            }
            rodadas[i] = pokemon;
        }
    }

    // ===== Seleciona a rodada atual e retorna o Pokemon correspondente =====
    public Pokemon selecionarRodada(int index) {
        rodadaAtual = index;
        return rodadas[index];
    }

    // ===== Verifica se a resposta do jogador está correta =====
    public boolean verificarResposta(String resposta) {
        if (rodadaAtual == -1 || rodadasRespondidas[rodadaAtual]) 
            return false;

        Pokemon p = rodadas[rodadaAtual];
        if(p == null || p.getNome() == null)
            return false;

        String respostaNormalizada = resposta.trim().replaceAll("\\s+", " ").toLowerCase();
        String nomeNormalizado = p.getNome().trim().replaceAll("\\s+", " ").toLowerCase();
        boolean correta = respostaNormalizada.equals(nomeNormalizado);

        rodadasRespondidas[rodadaAtual] = true;
        return correta;
    }

    public boolean rodadaJaRespondida(int index) {
        if (index < 0 || index >= rodadasRespondidas.length) {
            return false;
        }
        return rodadasRespondidas[index];
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
        if(rodadaAtual < 0 || rodadaAtual >= rodadas.length){
            return null;
        }
        return rodadas[rodadaAtual];
    }
}

