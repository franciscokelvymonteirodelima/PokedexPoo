package model.frames.inicio;

import javax.swing.*;
import java.awt.*;

import model.batalha.Comparacao;
import model.frames.GameColors;
import model.frames.batalha.*;
import model.frames.qualEhEssePokemon.*;
import model.frames.dicionario.*;
// import model.frames.ranking.*;
import model.pokedex.Pokedex;
import model.pokemon.Pokemon;
import model.frames.jogador.SistemaDeArquivos;
import model.frames.jogador.TelaMenu;


public class FInicio extends JFrame {
    private JButton[] botoesMenu;

    public FInicio() {
        setTitle("MENU");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        // ===== Fundo =====
        ImageIcon fundoMenu = new ImageIcon(getClass().getResource("/model/frames/images/FundosSimbolos/FUNDO_MENU.jpg"));
        JLabel background = new JLabel(fundoMenu);
        background.setBounds(0, 0, 1280, 720);
        setContentPane(background);
        setLayout(null);

        // ===== Nome do Site =====
        ImageIcon iconePokemon = new ImageIcon(getClass().getResource("/model/frames/images/FundosSimbolos/LogoInicio_400x.png"));
        JLabel labelIcone = new JLabel(iconePokemon);
        labelIcone.setBounds(320, 10, 640, 120);
        add(labelIcone);

        // ===== BOTÕES DO MENU =====
        int larguraBotao = 500;
        int alturaBotao = 55;
        int xBotao = (1280 - larguraBotao) / 2;
        int yInicial = 140;
        int espaco = 20;
        
        String[] textos = {
            "Batalha Pokemon",
            "Qual é esse Pokemon? (MiniGame)",
            "Pokedex",
            "Ranking",
            "Jogador",
            "Loja",
            "Sair"
        };
        
        botoesMenu = new JButton[textos.length];

        for (int i = 0; i < textos.length - 1; i++) {
            JButton botao = new JButton(textos[i]);
            botao.setBounds(xBotao, yInicial + i * (alturaBotao + espaco), larguraBotao, alturaBotao);
            botao.setFont(new Font("Arial", Font.BOLD, 20));
            botao.setForeground(Color.WHITE);
            add(botao);
            if(i == 0) {
                botao.setBackground(GameColors.BUTTON_BATTLE); // Verde para Batalha Pokemon
                botao.addActionListener(e -> botaoBatalhaAction());
                botoesMenu[i] = botao;
            } else if(i == 1) {
                botao.setBackground(GameColors.BUTTON_MINIGAME); // Azul para MiniGame
                botao.addActionListener(e -> botaoMiniGameAction());
                botoesMenu[i] = botao;
            } else if(i == 2){
                botao.setBackground(GameColors.BUTTON_POKEDEX); // Roxo para Pokedex
                botao.addActionListener(e -> botaoPokedexAction());
                botoesMenu[i] = botao;
            } else if(i == 3){
                botao.setBackground(GameColors.BUTTON_RANKING); // Laranja para Ranking
                botao.addActionListener(e -> botaoRankingAction());
                botoesMenu[i] = botao;
            } else if(i == 4){
                botao.setBackground(GameColors.BUTTON_PLAYER); // Roxo para Jogador
                botao.addActionListener(e -> botaoJogadorAction());
                botoesMenu[i] = botao;
            } else if(i == 5){
                botao.setBackground(GameColors.BUTTON_SHOP); // Laranja para Loja
                botao.addActionListener(e -> botaoLojaAction());
                botoesMenu[i] = botao;
            }
        }
        // ===== Botão Sair =====
        larguraBotao = 350;
        JButton botaoSair = new JButton(textos[textos.length - 1]);
        botaoSair.setBounds(xBotao + 75, yInicial + (textos.length - 1) * (alturaBotao + espaco), larguraBotao, alturaBotao);
        botaoSair.setBackground(GameColors.BUTTON_EXIT);
        botaoSair.setFont(new Font("Arial", Font.BOLD, 20));
        botaoSair.setForeground(Color.WHITE);
        botaoSair.addActionListener(e -> sairDoJogo());
        add(botaoSair);
        botoesMenu[textos.length - 1] = botaoSair;
    }

    private void botaoBatalhaAction() {
        try {
            Pokedex pokedex1 = new Pokedex();
            int numeroAleatorio1 = (int) (Math.random() * 151) + 1;
            Pokemon pokemon1 = pokedex1.getPokemonPC(numeroAleatorio1);

            Pokedex pokedex2 = new Pokedex();
            int numeroAleatorio2 = (int) (Math.random() * 151) + 1;
            Pokemon pokemon2 = pokedex2.getPokemonPC(numeroAleatorio2);

            Comparacao comparacao = new Comparacao(pokemon1, pokemon2);
            FBatalha frameBatalha = new FBatalha("Batalha Pokemon", comparacao);
            frameBatalha.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao iniciar batalha: " + ex.getClass().getSimpleName() + " - " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void botaoMiniGameAction() {
        FWhosThatPoke frameMiniGame = new FWhosThatPoke();
        frameMiniGame.setVisible(true);
    }

    private void botaoPokedexAction() {
        TelaPokedex framePokedex = new TelaPokedex();
        framePokedex.setVisible(true);
    }

    private void botaoRankingAction() {
        // FRanking frameRanking = new FRanking();
        // frameRanking.setVisible(true);
    }

    private void botaoJogadorAction() {
        try{
            SistemaDeArquivos.existemSaves();
            TelaMenu frameJogador = new TelaMenu();
            frameJogador.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao iniciar jogador: " + ex.getClass().getSimpleName() + " - " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void botaoLojaAction() {
        // TelaLoja frameLoja = new TelaLoja();
        // frameLoja.setVisible(true);
    }

    private void sairDoJogo() {
        int resposta = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja sair do jogo?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );

        if (resposta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}