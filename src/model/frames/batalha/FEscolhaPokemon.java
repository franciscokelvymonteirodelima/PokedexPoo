package model.frames.batalha;

import model.arquivo.LeitorArquivosSaveGame;
import model.batalha.Comparacao;
import model.frames.dicionario.PainelGraficoStatus;
import model.jogador.Jogador;
import model.pokedex.Pokedex;
import model.pokemon.Pokemon;
import model.frames.GameColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class FEscolhaPokemon extends JFrame {

    private JLabel labelImagem;
    private JLabel labelNome;
    private JLabel labelTipo;
    private Pokedex pokedex;
    private LeitorArquivosSaveGame leitorArquivoSaveGame;
    private PainelGraficoStatus painelGraficoStatus;
    private Jogador jogador;
    private Pokemon pokemonSelecionado;

    public FEscolhaPokemon(String title, Jogador jogador){
        super(title);
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pokedex = new Pokedex();
        leitorArquivoSaveGame = new LeitorArquivosSaveGame();
        this.jogador = jogador;
        initComponents();
    }

    private ImageIcon carregarIconePokemon(Pokemon p) {
        String nomeArquivo = p.getCaminhoImagem();

        if (nomeArquivo.contains("/")) {
            nomeArquivo = nomeArquivo.substring(nomeArquivo.lastIndexOf("/") + 1);
        }
        if (nomeArquivo.toLowerCase().endsWith(".png")) {
            nomeArquivo = nomeArquivo.replace(".png", "");
        }

        String caminhoRecurso = "/model/frames/images/GEN1/" + nomeArquivo + ".png";
        URL imgURL = getClass().getResource(caminhoRecurso);

        if (imgURL == null) {
            System.err.println("Imagem não encontrada no pacote: " + caminhoRecurso);
            return null;
        }

        return new ImageIcon(imgURL);
    }

    private JButton criarBotaoPokemon(Pokemon p) {
        ImageIcon icon = carregarIconePokemon(p);
        JButton botao;

        if (icon != null) {
            Image img = icon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH); // Aumentei um pouco a img
            botao = new JButton(p.getNome(), new ImageIcon(img));
        } else {
            botao = new JButton(p.getNome());
        }
        botao.setVerticalTextPosition(SwingConstants.BOTTOM); // Texto embaixo da imagem
        botao.setHorizontalTextPosition(SwingConstants.CENTER); // Centralizado horizontalmente
        botao.setIconTextGap(10); // Espaço entre imagem e texto

        botao.setBackground(Color.WHITE);
        botao.setForeground(new Color(50, 50, 50)); // Cinza escuro para o texto
        botao.setFont(new Font("Consolas", Font.BOLD, 14)); // Fonte monoespaçada estilosa

        botao.setFocusPainted(false); // Remove a linha pontilhada de foco
        botao.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2)); // Borda cinza suave
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Mãozinha ao passar o mouse

        botao.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                botao.setBackground(new Color(230, 240, 255)); // Azul bem clarinho ao passar o mouse
                botao.setBorder(BorderFactory.createLineBorder(new Color(100, 150, 255), 2)); // Borda azul
            }

            public void mouseExited(MouseEvent evt) {
                botao.setBackground(Color.WHITE); // Volta para branco
                botao.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2)); // Volta borda cinza
            }
        });

        botao.addActionListener(e -> mostrarPokemon(p));

        return botao;
    }
    private void mostrarPokemon(Pokemon p) {

        this.pokemonSelecionado = p;

        ImageIcon icon = carregarIconePokemon(p);

        if (icon != null) {
            // Redimensiona para o painel de info (200x200)
            Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            labelImagem.setIcon(new ImageIcon(img));
            labelImagem.setText("");
        } else {
            labelImagem.setIcon(null);
            labelImagem.setText("Imagem não encontrada");
        }

        labelNome.setText(p.getNome());
        labelTipo.setText("Tipo 1: " + p.getTipo1() + " / " + "Tipo 2: " + p.getTipo2());

        if (painelGraficoStatus != null) {
            painelGraficoStatus.setPokemon(p);
        }
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
        //ArrayList<Integer> listaDeIds = leitorArquivoSaveGame.lerNumerosPokemon();
        List<Pokemon> listaPokemons = jogador.getPcBox(); // ou jogador.getTimePokemon();

        Pokemon pGrafico = null;
        
        for (Pokemon p : listaPokemons) {
            if (p != null) {
                panelPokemons.add(criarBotaoPokemon(p));
            }
            if (pGrafico == null) {
                pGrafico = p;
            }
        }

        painelGraficoStatus = new PainelGraficoStatus(pGrafico);
        painelGraficoStatus.setBounds(10, 320, 400, 150);
        
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
        panelInfo.add(painelGraficoStatus);

        JButton btnLutar = new JButton("IR PARA A BATALHA!");
        btnLutar.setBounds(60, 480, 300, 40);
        btnLutar.setBackground(new Color(220, 20, 60));
        btnLutar.setForeground(Color.WHITE);
        btnLutar.setFont(new Font("Arial", Font.BOLD, 18));
        btnLutar.addActionListener(e -> iniciarBatalha());
        panelInfo.add(btnLutar);

    }

    private void iniciarBatalha() {
        if (pokemonSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um Pokémon primeiro!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Pokedex pokedexInimiga = new Pokedex();
        int idInimigo = (int) (Math.random() * 151) + 1;
        Pokemon inimigo = pokedexInimiga.getPokemonPC(idInimigo);

        Comparacao comparacao = new Comparacao(pokemonSelecionado, inimigo);

        FBatalha telaBatalha = new FBatalha("Batalha Pokémon", comparacao);
        telaBatalha.setVisible(true);

        this.dispose();
    }

}

