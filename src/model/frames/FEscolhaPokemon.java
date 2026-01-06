package model.frames;

import model.pokedex.Pokedex;
import model.pokemon.Pokemon;

import javax.swing.*;
import java.awt.*;


public class FEscolhaPokemon extends JFrame {

    private JLabel labelImagem;
    private JLabel labelNome;
    private JLabel labelTipo;
    private Pokedex pokedex;

    public FEscolhaPokemon(String title){
        super(title);
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pokedex = new Pokedex();
        initComponents();
    }

    private void carregarSprite(JLabel label, String caminho, int largura, int altura) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(caminho));
            Image img = icon.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagem: " + caminho);

        }
    }

    private JButton criarBotaoPokemon(Pokemon p) {
        ImageIcon icon = new ImageIcon(
                getClass().getResource(p.getCaminhoImagem())
        );

        Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);

        JButton botao = new JButton(new ImageIcon(img));
        botao.setPreferredSize(new Dimension(90, 90));
        botao.setFocusPainted(false);
        botao.setContentAreaFilled(false);
        botao.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        botao.addActionListener(e -> mostrarPokemon(p));

        return botao;
    }

    private void mostrarPokemon(Pokemon p) {

        ImageIcon icon = new ImageIcon(
                getClass().getResource(p.getCaminhoImagem())
        );

        Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);

        labelImagem.setIcon(new ImageIcon(img));
        labelNome.setText(p.getNome());
        labelTipo.setText(p.getTipo1() + " / " + p.getTipo2());
    }

    private void initComponents() {

        JPanel panel = new JPanel();
        panel.setLayout(null);
        setContentPane(panel);
        panel.setBackground(GameColors.PANEL_PLAYER);

        JPanel panelPokemons = new JPanel();
        panelPokemons.setLayout(new GridLayout(0, 4, 10, 10));
        panelPokemons.setBounds(20, 20, 420, 520);

        JLabel titulo = new JLabel("Escolha seu Pokemon", SwingConstants.CENTER);
        titulo.setFont(new Font("Consolas", Font.BOLD, 30));
        titulo.setBounds(20, 10, 420, 40);
        panel.add(titulo);

        //  ------ Adicionar as imagens dos pokemons --------
        for (int i = 1; i <= 151; i++) {
            Pokemon p = pokedex.getPokemonPC(i);
            if (p != null) {
                panelPokemons.add(criarBotaoPokemon(p));
            }
        }
       // -------------------------------------------------

        JPanel panelInfo = new JPanel();
        panelInfo.setBounds(460, 20, 420, 520);
        panelInfo.setLayout(null);

        labelImagem = new JLabel();
        labelNome = new JLabel("Selecione um Pokémon");
        labelTipo = new JLabel("");

        labelNome.setFont(new Font("Consolas", Font.BOLD, 22));

        labelImagem.setBounds(110, 20, 200, 200);
        labelNome.setBounds(20, 240, 380, 30);
        labelTipo.setBounds(20, 280, 380, 30);

        labelNome.setHorizontalAlignment(SwingConstants.CENTER);
        labelTipo.setHorizontalAlignment(SwingConstants.CENTER);

        panelInfo.add(labelImagem);
        panelInfo.add(labelNome);
        panelInfo.add(labelTipo);

        JScrollPane scroll = new JScrollPane(panelPokemons);
        scroll.setBounds(20, 60, 420, 480);
        panel.add(scroll);
        panel.add(panelInfo);
    }

    public static void main(String[] args) {
        FEscolhaPokemon frame = new FEscolhaPokemon("Escolha de Pokemons");
        frame.setVisible(true);
    }

}
