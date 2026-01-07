package model.frames;

import model.arquivo.LeitorArquivosSaveGame;
import model.pokedex.Pokedex;
import model.pokemon.Pokemon;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class FEscolhaPokemon extends JFrame {

    private JLabel labelImagem;
    private JLabel labelNome;
    private JLabel labelTipo;
    private Pokedex pokedex;
    private LeitorArquivosSaveGame leitorArquivoSaveGame;

    public FEscolhaPokemon(String title){
        super(title);
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pokedex = new Pokedex();
        leitorArquivoSaveGame = new LeitorArquivosSaveGame();
        initComponents();
    }

    private ImageIcon carregarIconePokemon(Pokemon p) {
        String nomeArquivo = p.getCaminhoImagem();

        // Limpeza básica do nome
        if (nomeArquivo.contains("/")) {
            nomeArquivo = nomeArquivo.substring(nomeArquivo.lastIndexOf("/") + 1);
        }
        if (nomeArquivo.toLowerCase().endsWith(".png")) {
            nomeArquivo = nomeArquivo.replace(".png", "");
        }

        // O caminho muda pois agora é relativo à pasta 'src'
        // Como a classe FEscolhaPokemon está em 'model.frames', e images está em 'model.frames.images'
        // O caminho é relativo a classe ou absoluto a partir do src (usando /)

        // Tenta caminho normal (ex: "8.png")
        String caminhoRecurso = "/model/frames/images/GEN1/" + nomeArquivo + ".png";
        java.net.URL imgURL = getClass().getResource(caminhoRecurso);

        // Se falhar, tenta com 3 dígitos (ex: "008.png")
        if (imgURL == null) {
            try {
                int id = Integer.parseInt(nomeArquivo);
                String nomeFormatado = String.format("%03d", id);
                caminhoRecurso = "/model/frames/images/GEN1/" + nomeFormatado + ".png";
                imgURL = getClass().getResource(caminhoRecurso);
            } catch (NumberFormatException e) {
                // ignora
            }
        }

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

        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(new Color(230, 240, 255)); // Azul bem clarinho ao passar o mouse
                botao.setBorder(BorderFactory.createLineBorder(new Color(100, 150, 255), 2)); // Borda azul
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(Color.WHITE); // Volta para branco
                botao.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2)); // Volta borda cinza
            }
        });

        botao.addActionListener(e -> mostrarPokemon(p));

        return botao;
    }
    private void mostrarPokemon(Pokemon p) {

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
        ArrayList<Integer> listaDeIds = leitorArquivoSaveGame.lerNumerosPokemon();

        for (Integer id : listaDeIds) {
            Pokemon p = pokedex.getPokemonPC(id);
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