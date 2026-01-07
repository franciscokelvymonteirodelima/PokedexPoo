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

        // Carregar a imagem do Pokémon sem fundo
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

    public void atualizarPokemon(Pokemon pokemon){
        if(pokemon == null){
            imagemPokemonLabel.setIcon(null);
            imagemPokemonLabel.setText("Pokemon não encontrado");
        }

        String caminhoImagem = pokemon.getCaminhoImagem();
        if(caminhoImagem == null || caminhoImagem.isEmpty()){
            imagemPokemonLabel.setIcon(null);
            imagemPokemonLabel.setText("Imagem não disponível");
            return;
        }

        Image imagemPokemon = new ImageIcon(getClass().getResource(caminhoImagem)).getImage();
        BufferedImage bimage = new BufferedImage(350, 350, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bimage.createGraphics();
        g2d.setComposite(AlphaComposite.Src);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(imagemPokemon, 0, 0, 350, 350, null);
        g2d.dispose();

        ImageIcon imagemRedimensionada = new ImageIcon(bimage);
        
        int larguraRedimensionada = imagemRedimensionada.getIconWidth();
        int alturaRedimensionada = imagemRedimensionada.getIconHeight();

        int larguraNova = 350;
        int alturaNova= (int) ((double) alturaRedimensionada / larguraRedimensionada * larguraNova);

        if(alturaNova > 350){
            alturaNova = 350;
            larguraNova = (int) ((double) larguraRedimensionada / alturaRedimensionada * alturaNova);
        }

        Image imgFinal = imagemRedimensionada.getImage().getScaledInstance(larguraNova, alturaNova, Image.SCALE_SMOOTH);
        imagemRedimensionada = new ImageIcon(imgFinal);
        imagemPokemonLabel.setIcon(imagemRedimensionada);
        imagemPokemonLabel.setText("");
    }

    public JLabel getImagemPokemonLabel() {
        return imagemPokemonLabel;
    }
}
