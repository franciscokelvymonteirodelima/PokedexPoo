package model.frames.batalha;
import model.batalha.Comparacao;
import model.frames.GameColors;
import model.pokedex.Pokedex;
import model.pokemon.Pokemon;

import javax.swing.*;
import java.awt.*;

public class FBatalha extends JFrame{

    private Comparacao comparacao;

    public FBatalha(String title, Comparacao comparacao){
        super(title);
        this.comparacao =  comparacao;
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private JLabel nomePokemon1; //Dados que serão modificados
    private JLabel nomePokemon2;
    private JLabel spritePokemon1;
    private JLabel hpPokemon1;

    private JLabel levelPokemon1;
    private JLabel levelPokemon2;
    private JLabel spritePokemon2;
    private JLabel hpPokemon2;

    private JLabel perguntaAcao;

    public void atualizarInterface(){
        Pokemon pokemon1 = comparacao.getPokemon1();
        nomePokemon1.setText(pokemon1.getNome().toUpperCase());
        levelPokemon1.setText("Lv" +  pokemon1.getNivel());
        hpPokemon1.setText("Hp " +  pokemon1.getHp());

        Pokemon pokemon2 = comparacao.getPokemon2();
        nomePokemon2.setText(pokemon2.getNome().toUpperCase());
        levelPokemon2.setText("Lv" +  pokemon2.getNivel());
        hpPokemon2.setText("Hp " +  pokemon2.getHp());

        carregarSprite(spritePokemon1, pokemon1.getCaminhoImagem(), 350, 350);
        carregarSprite(spritePokemon2, pokemon2.getCaminhoImagem(), 300, 300);

        perguntaAcao.setText(pokemon2.getNome().toUpperCase() + " do");
    }

    //Metodo para carregar o sprite
    private void carregarSprite(JLabel label, String caminho, int largura, int altura) {
        try {
            if (caminho == null) {
                System.out.println("caminhoImagem is null para o pokemon");
                return;
            }
            java.net.URL url = getClass().getResource(caminho);
            if (url == null) {
                System.out.println("Recurso de imagem não encontrado: " + caminho);
                return;
            }
            ImageIcon icon = new ImageIcon(url);
            Image img = icon.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagem: " + caminho);

        }
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        setContentPane(panel);
        panel.setBackground(GameColors.BATTLE_BG_GRASS);

        // Definicao das caixas de dados do pokemon em batalha
        JPanel panel2 = new JPanel();
        panel2.setBounds(50, 50, 280, 80);   // posição + tamanho
        panel2.setLayout(null);
        panel2.setBackground(GameColors.HUD_BACKGROUND);
        panel2.setBorder(BorderFactory.createLineBorder(GameColors.HUD_BORDER));

        JPanel panel1 = new JPanel();
        panel1.setBounds(900, 400, 280, 80);
        panel1.setLayout(null);
        panel1.setBackground(GameColors.HUD_BACKGROUND);
        panel1.setBorder(BorderFactory.createLineBorder(GameColors.HUD_BORDER));

        //--------- Definicao dos dados do pokemos para a panel1 ----------
        nomePokemon1 = new JLabel();
        levelPokemon1 = new JLabel();
        hpPokemon1 = new JLabel();
        nomePokemon1.setFont(new Font("Arial", Font.BOLD, 17));
        nomePokemon1.setBounds(25, 10, 200, 20);
        levelPokemon1.setFont(new Font("Arial", Font.BOLD, 17));
        levelPokemon1.setBounds(240, 10, 50, 20);

        JPanel barraLevel1 = new JPanel();
        barraLevel1.setBounds(80, 40, 180, 10);
        barraLevel1.setBackground(GameColors.HP_FULL);
        barraLevel1.setLayout(null);

        hpPokemon1.setFont(new Font("Arial", Font.BOLD, 12));
        hpPokemon1.setBounds(30, 40, 50, 10);
        // ----------------------------------------------------------------

        //--------- Definicao dos dados do pokemos para a panel2 ----------
        nomePokemon2 = new JLabel();
        levelPokemon2 = new JLabel();
        hpPokemon2 = new JLabel();
        nomePokemon2.setFont(new Font("Arial", Font.BOLD, 17));
        levelPokemon2.setFont(new Font("Arial", Font.BOLD, 17));
        nomePokemon2.setBounds(25, 10, 200, 20);
        levelPokemon2.setBounds(240, 10, 50, 20);

        JPanel barraLevel2 = new JPanel();
        barraLevel2.setBounds(80, 40, 180, 10);
        barraLevel2.setBackground(GameColors.HP_FULL);
        barraLevel2.setLayout(null);

        hpPokemon2.setFont(new Font("Arial", Font.BOLD, 12));
        hpPokemon2.setBounds(30, 40, 50, 10);
        // -------------------------------------------------------------------

        // ----------- PAINEL INFERIOR --------------------------------------
        JPanel panelInferiorPrincipal = new JPanel();
        panelInferiorPrincipal.setLayout(null);
        panelInferiorPrincipal.setBackground(GameColors.MENU_FRAME_PURPLE);

        // PAINEL DE FUNDO VERMELHO
        JPanel panelAux1 = new JPanel();
        panelAux1.setLayout(null);
        panelAux1.setBackground(GameColors.MENU_BORDER_ORANGE);
        panelAux1.setBounds(10, 10, 600, 140);
        panelInferiorPrincipal.add(panelAux1);

        // PAINEL COM TEXTO
        JPanel panelAux2 = new JPanel();
        panelAux2.setLayout(null);
        panelAux2.setBackground(GameColors.MENU_TEXT_BG_TEAL);
        panelAux2.setBounds(24, 6, 550, 125);
        panelAux1.add(panelAux2);

        // Linha 1
        JLabel linha1 = new JLabel("What will");
        linha1.setForeground(GameColors.TEXT_WHITE);
        linha1.setFont(new Font("Consolas", Font.BOLD, 30));
        linha1.setBounds(40, 25, 500, 30);
        panelAux2.add(linha1);

        //Linha 2
        perguntaAcao = new JLabel(); // Cria vazio
        perguntaAcao.setForeground(GameColors.TEXT_WHITE);
        perguntaAcao.setFont(new Font("Consolas", Font.BOLD, 30));
        perguntaAcao.setBounds(40, 65, 500, 30);
        panelAux2.add(perguntaAcao);

        // PANEL PARA AS OPCOES DE ESCOLHA
        JPanel panelEscolhas = new JPanel();
        panelEscolhas.setLayout(new GridLayout(1, 1));
        panelEscolhas.setBackground(GameColors.MENU_WHITE_BG);
        panelEscolhas.setBounds(620, 10, 600, 140);
        panelInferiorPrincipal.add(panelEscolhas);

        // BOTOES PARA AS OPCOES DE ESCOLHA
        JButton buttonFight = new JButton("Fight");

        // ---- Acao do botao fight ----------
        buttonFight.addActionListener(e -> {
            Pokemon vencedor = comparacao.realizarComparacao();

            JOptionPane.showMessageDialog(null, "O vencedor foi: " + vencedor.getNome());
            this.dispose();
        });

        // -----------------------------------

        buttonFight.setFont(new Font("Arial", Font.BOLD, 80));
        buttonFight.setBackground(GameColors.MENU_WHITE_BG);
        buttonFight.setBorderPainted(false);
        buttonFight.setFocusPainted(false);

        panelEscolhas.add(buttonFight);

        // Dimensoes:
        panelInferiorPrincipal.setBounds(0, 525, 1280, 180);

        // ------------------------------------------------------------------

        panel1.add(barraLevel1);
        panel1.add(nomePokemon1);
        panel1.add(levelPokemon1);
        panel1.add(hpPokemon1);

        panel2.add(barraLevel2);
        panel2.add(nomePokemon2);
        panel2.add(levelPokemon2);
        panel2.add(hpPokemon2);

        panel.add(panelInferiorPrincipal);
        //--------------- Adicao dos sprites --------------------------------------------------

        // -------- Sprite 1 -----------------------------------
        // Nessesario fazer um tratamento de excessao para verificar se achou a imagem !
        spritePokemon1 = new JLabel();
        spritePokemon1.setBounds(200, 220, 350, 350);
        panel.add(spritePokemon1);
        // -------------------------------------------------------------

        // -------- Sprite 2 -----------------------------------
        // Nessesario fazer um tratamento de excessao para verificar se achou a imagem !
        spritePokemon2 = new JLabel();
        spritePokemon2.setBounds(700, 30, 300, 300);
        panel.add(spritePokemon2);
        // ------------------------------------------------------------

        //------- Sprite BattleBase ----------------------

        ImageIcon battleBaseIcon = null;
        java.net.URL battleBaseUrl = getClass().getResource("/model/frames/images/FundosSimbolos/BattleBase.png");
        if (battleBaseUrl != null) {
            battleBaseIcon = new ImageIcon(battleBaseUrl);
        } else {
            System.out.println("Recurso BattleBase.png não encontrado em /model/frames/images/FundosSimbolos/");
        }
        Image playerbattlebase = null;
        JLabel battleBaseLabel = new JLabel();
        if (battleBaseIcon != null) {
            playerbattlebase = battleBaseIcon.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
            battleBaseLabel.setIcon(new ImageIcon(playerbattlebase));
        }
        battleBaseLabel.setBounds(0, -140, 1280, 720);

        // -----------------------------------------------
        // -----------------------------------------------------------------------------------------
        panel.add(panel1);
        panel.add(panel2);
        panel.add(battleBaseLabel);
        atualizarInterface();
    }
}
