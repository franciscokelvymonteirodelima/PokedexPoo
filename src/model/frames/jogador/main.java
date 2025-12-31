package model.frames.jogador;

import model.frames.jogador.TelaJogador;
import model.jogador.Jogador;
import model.pokemon.Pokemon;

import javax.swing.SwingUtilities;

public class main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Jogador jogador = new Jogador("Ash", 10, "Masculino", "Pallet Town");

            // Pokémons fake só pra teste
            jogador.capturarPokemon(new Pokemon("Pikachu", 25, 5, null, null, null, 0, 0, 0, 0, 0, 0, null, null));
            jogador.capturarPokemon(new Pokemon("Bulbasaur", 1, 7, null, null, null, 0, 0, 0, 0, 0, 0, null, null));
            jogador.capturarPokemon(new Pokemon("Charmander", 4, 8, null, null, null, 0, 0, 0, 0, 0, 0, null, null));

            new TelaJogador(jogador).setVisible(true);
        });
    }
}
