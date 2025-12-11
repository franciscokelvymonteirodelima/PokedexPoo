package model.pokedex;

import model.pokemon.Pokemon;
import java.util.List;
import java.util.ArrayList;	

public class Pokedex {

    //deois ver essa questao de usar list ou arraylist para esses atributos
    protected List<Pokemon> dicionarioPokemon;
    protected List<Pokemon> favoritos;
    protected List<Pokemon> capturados;
    protected int totalCapturados;

    public Pokedex(){
        this.dicionarioPokemon = new ArrayList<>();
        this.favoritos = new ArrayList<>();
        this.capturados = new ArrayList<>();
        this.totalCapturados = 0;
    }

    public List<Pokemon> listarTodos(){
        return new ArrayList<>(dicionarioPokemon); // neste caso ele ta retornando a copia da lista, para nao modificar a lista original
    }




    

    
}
