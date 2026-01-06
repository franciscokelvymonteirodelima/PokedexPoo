package model.frames.qualEhEssePokemon;

import model.pokedex.Pokedex;
import model.pokemon.Pokemon;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PokemonAleatorioPanel extends JPanel {
    private JLabel imagemPokemonLabel;

    public PokemonAleatorioPanel() {
        setLayout(new BorderLayout());

        // Obter um Pokémon aleatório da Pokédex
        Pokedex pokedex = new Pokedex();
        int numeroAleatorio = (int) (Math.random() * 151) + 1;
        Pokemon pokemonAleatorio = pokedex.getPokemonPC(numeroAleatorio);

        // Carregar a imagem do Pokémon e escalonar para 96x96 preservando alpha
        ImageIcon imagemPokemon = new ImageIcon(getClass().getResource(pokemonAleatorio.getCaminhoImagem()));
        Image imgSrc = imagemPokemon.getImage();

        BufferedImage bimage = new BufferedImage(350, 350, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bimage.createGraphics();
        g2d.setComposite(AlphaComposite.Src);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(imgSrc, 0, 0, 350, 350, null);
        g2d.dispose();

        imagemPokemon = new ImageIcon(bimage);

        // Configurar o JLabel para exibir a imagem
        imagemPokemonLabel = new JLabel(imagemPokemon);
        imagemPokemonLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagemPokemonLabel.setOpaque(false);
        setOpaque(false);
        add(imagemPokemonLabel, BorderLayout.CENTER);
    }

    public JLabel getImagemPokemonLabel() {
        return imagemPokemonLabel;
    }
}
