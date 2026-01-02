package model.batalha;

import model.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class LogsComparacao {
    private boolean isComparacaofinalizada;
    private List<String> logsComparacao;
    private Pokemon pokemonVencedor;

    public LogsComparacao(boolean isComparacaofinalizada, List<String> logsComparacao, Pokemon pokemonVencedor) {
        this.isComparacaofinalizada = false;
        this.logsComparacao = new ArrayList<>();
        this.pokemonVencedor = pokemonVencedor;
    }

    public void adicionarLog(String log){
        this.logsComparacao.add(log);
    }

    public boolean isComparacaofinalizada() {
        return isComparacaofinalizada;
    }

    public Pokemon getPokemonVencedor() {
        return pokemonVencedor;
    }
    public void setPokemonVencedor(Pokemon pokemonVencedor) {
        this.pokemonVencedor = pokemonVencedor;
    }

    public void setComparacaofinalizada(boolean comparacaofinalizada) {
        isComparacaofinalizada = comparacaofinalizada;
    }

    public String logFormatado(){
        return String.join("\n", logsComparacao);
    }
}
