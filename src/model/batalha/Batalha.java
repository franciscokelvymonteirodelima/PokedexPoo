package model.batalha;

import model.pokemon.Pokemon;

public class Batalha {
    
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private Pokemon vencedor;
    private double mediaStatusPokemon1;
    private double mediaStatusPokemon2;
    
    // inicializando a batalha com os pokemons
    public Batalha(Pokemon pokemon1, Pokemon pokemon2) {
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.vencedor = null;
    }
    
    // inicializando a batalha com os pokemons e o vencedor
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
    
    // realizando a batalha
    public Pokemon realizarBatalha() {
        this.mediaStatusPokemon1 = calcularMediaStatus(pokemon1);
        this.mediaStatusPokemon2 = calcularMediaStatus(pokemon2);
        
        if (mediaStatusPokemon1 > mediaStatusPokemon2) {
            this.vencedor = pokemon1;
        } else if (mediaStatusPokemon2 > mediaStatusPokemon1) {
            this.vencedor = pokemon2;
        } else {
            if (pokemon1.getVelocidade() > pokemon2.getVelocidade()) {
                this.vencedor = pokemon1;
            } else {
                this.vencedor = pokemon2;
            }
        }
        
        return this.vencedor;
    }
    
    // getters e setters
    
    public Pokemon getVencedor() {
        return vencedor;
    }
    
    public Pokemon getPokemon1() {
        return pokemon1;
    }
    
    public Pokemon getPokemon2() {
        return pokemon2;
    }
    
    public double getMediaStatusPokemon1() {
        return mediaStatusPokemon1;
    }
    
    public double getMediaStatusPokemon2() {
        return mediaStatusPokemon2;
    }
}
