package model.frames.inicio;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;

import model.batalha.Comparacao;
import model.frames.GameColors;
import model.frames.batalha.*;
import model.frames.qualEhEssePokemon.*;
import model.frames.dicionario.*;
import model.frames.ranking.*;
import model.pokedex.Pokedex;
import model.pokemon.Pokemon;
import model.frames.jogador.SistemaDeArquivos;
//import model.frames.jogador.TelaMenu; , nao adicione isso ainda estou fazendo testes :/ 


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
                botao.addActionListener(e -> irParaSelecaoBatalha());
                botoesMenu[i] = botao;
            } else if(i == 1) {
                botao.setBackground(GameColors.BUTTON_MINIGAME); // Azul para MiniGame
                botao.addActionListener(e -> irParaSelecaoMiniGame());
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

    private void carregarSaveJogador() {
        try {
            model.jogador.Jogador temp = SistemaDeArquivos.carregarComDialogo();
            if (temp != null) {
                Sessao.jogadorLogado = temp;
                JOptionPane.showMessageDialog(this, "Bem-vindo de volta, " + temp.getNome() + "!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void irParaSelecaoBatalha() {
        System.out.println("Tentando iniciar batalha..."); // Log no console

        if (Sessao.jogadorLogado == null) {
            System.out.println("Erro: Sessao.jogadorLogado está NULL");
            JOptionPane.showMessageDialog(this,
                    "Nenhum save carregado! Vá em 'Jogador' para carregar.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        System.out.println("Jogador encontrado: " + Sessao.jogadorLogado.getNome());
        try {
            FEscolhaPokemon tela = new FEscolhaPokemon("Escolha",  Sessao.jogadorLogado);
            tela.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao abrir tela de escolha: " + e.getMessage());
        }
    }

    private void irParaSelecaoMiniGame(){
        System.out.println("Tentando iniciar mini game ...");
        if(Sessao.jogadorLogado == null) {
            System.out.println("Erro ao iniciar mini game - Nenhum save carregado!");
            JOptionPane.showMessageDialog(this,
                    "Nenhum save carregado! Vá em 'Jogador' para carregar.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        System.out.println("Jogador encontrado: " + Sessao.jogadorLogado.getNome());
        try{
            FWhosThatPoke fWhosThatPoke = new FWhosThatPoke();
            fWhosThatPoke.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao abrir tela de minigame: " + e.getMessage());
        }
    }

    private void botaoMiniGameAction() {
        System.out.println("Tentando iniciar minigame..."); // Log no console

        if (Sessao.jogadorLogado == null) {
            System.out.println("Erro: Sessao.jogadorLogado está NULL");
            JOptionPane.showMessageDialog(this,
                    "Nenhum save carregado! Vá em 'Jogador' para carregar.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        System.out.println("Jogador encontrado: " + Sessao.jogadorLogado.getNome());
        try {
            FWhosThatPoke tela = new FWhosThatPoke();
            tela.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao abrir tela de escolha: " + e.getMessage());
        }
    }

    private void botaoPokedexAction() {
        TelaPokedex framePokedex = new TelaPokedex();
        framePokedex.setVisible(true);
    }

    private void botaoRankingAction() {
        FRanking frameRanking = new FRanking();
        frameRanking.setVisible(true);
    }

    private void botaoJogadorAction() {
        try{
            SistemaDeArquivos.existemSaves();
            //isso pode ser considerado uma gambiara mas ta funcionando por enquanto kkk desculpa enyo ...
            model.frames.jogador.TelaMenu frameJogador = new model.frames.jogador.TelaMenu();
            frameJogador.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao iniciar jogador: " + ex.getClass().getSimpleName() + " - " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void botaoLojaAction() {
        model.frames.loja.TelaMenu frameLoja = new model.frames.loja.TelaMenu();
        frameLoja.setVisible(true);
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