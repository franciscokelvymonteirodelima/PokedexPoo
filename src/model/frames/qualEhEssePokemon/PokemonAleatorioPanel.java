package model.frames.qualEhEssePokemon;

import model.pokedex.Pokedex;
import model.pokemon.Pokemon;
import javax.swing.*;
import java.awt.*;

public class PokemonAleatorioPanel extends JPanel {
    private JLabel imagemPokemonLabel;

    public PokemonAleatorioPanel() {
        setLayout(new BorderLayout());

        // Obter um Pokémon aleatório da Pokédex
        Pokedex pokedex = new Pokedex();
        int numeroAleatorio = (int) (Math.random() * 151) + 1;
        Pokemon pokemonAleatorio = pokedex.getPokemonPC(numeroAleatorio);

        // Carregar a imagem do Pokémon
        ImageIcon imagemPokemon = new ImageIcon(pokemonAleatorio.getCaminhoImagem());
        Image img = imagemPokemon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        imagemPokemon = new ImageIcon(img);

        // Configurar o JLabel para exibir a imagem
        imagemPokemonLabel = new JLabel(imagemPokemon);
        imagemPokemonLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imagemPokemonLabel, BorderLayout.CENTER);
    }

    public JLabel getImagemPokemonLabel() {
        return imagemPokemonLabel;
    }
}
