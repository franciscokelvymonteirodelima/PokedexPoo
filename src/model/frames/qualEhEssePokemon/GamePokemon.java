package model.frames.qualEhEssePokemon;

import java.util.ArrayList;
import java.util.List;

import model.pokedex.Pokedex;
import model.pokemon.Pokemon;

public class GamePokemon extends AbstractGameP {

    private Pokemon[] rodadas = new Pokemon[10];
    private int rodadaAtual = -1;
    private boolean[] rodadasRespondidas = new boolean[10];

    // ===== Construtor que inicializa as rodadas com Pokémons aleatórios (sem repetição) =====
public GamePokemon() {
        Pokedex pokedex = new Pokedex();
        List<Integer> pcsUsados = new ArrayList<>(); // Lista que vai guardar os PCs já usados

        for (int i = 0; i < 10; i++) {
            Pokemon pokemon = null;
            
            // Logica de busca de um pokemon aleatorio sem repetição
            while (pokemon == null) {
                int pokePC = (int) (Math.random() * 151) + 1;
                
                if (!pcsUsados.contains(pokePC)) {
                    pokemon = pokedex.getPokemonPC(pokePC);
                    
                    if (pokemon != null) {
                        pcsUsados.add(pokePC); // Marca como usado
                    }
                }
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

        if(correta)
            super.setAcertos();

        rodadasRespondidas[rodadaAtual] = true;
        return correta;
    }

    public boolean rodadaJaRespondida(int index) {
        if (index < 0 || index >= rodadasRespondidas.length) {
            return false;
        }
        return rodadasRespondidas[index];
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

