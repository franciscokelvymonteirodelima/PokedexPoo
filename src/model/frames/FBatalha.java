package model.frames;
import javax.swing.*;
import java.awt.*;

public class FBatalha extends JFrame{

    public FBatalha(String title){
        super(title);
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        setContentPane(panel);
        panel.setBackground(GameColors.BATTLE_BG_GRASS);

        // Definicao das caixas de dados do pokemon em batalha
        JPanel panel1 = new JPanel();
        panel1.setBounds(50, 50, 280, 80);   // posição + tamanho
        panel1.setLayout(null);
        panel1.setBackground(GameColors.HUD_BACKGROUND);
        panel1.setBorder(BorderFactory.createLineBorder(GameColors.HUD_BORDER));

        JPanel panel2 = new JPanel();
        panel2.setBounds(900, 400, 280, 80);
        panel2.setLayout(null);
        panel2.setBackground(GameColors.HUD_BACKGROUND);
        panel2.setBorder(BorderFactory.createLineBorder(GameColors.HUD_BORDER));

        //--------- Definicao dos dados do pokemos para a panel1 ----------
        JLabel nomePokemon1 = new JLabel("LOTAD");
        JLabel pokemonLevel1 = new JLabel("Lv7");
        nomePokemon1.setFont(new Font("Arial", Font.BOLD, 17));
        pokemonLevel1.setFont(new Font("Arial", Font.BOLD, 17));

        nomePokemon1.setBounds(25, 10, 60, 20);
        pokemonLevel1.setBounds(240, 10, 50, 20);

        JPanel barraLevel1 = new JPanel();
        barraLevel1.setBounds(80, 40, 180, 10);
        barraLevel1.setBackground(GameColors.HP_FULL);
        barraLevel1.setLayout(null);
        // ----------------------------------------------------------------

        //--------- Definicao dos dados do pokemos para a panel2 ----------
        JLabel nomePokemon2 = new JLabel("TORCHIC");
        JLabel pokemonLevel2 = new JLabel("Lv7");
        nomePokemon2.setFont(new Font("Arial", Font.BOLD, 17));
        pokemonLevel2.setFont(new Font("Arial", Font.BOLD, 17));

        nomePokemon2.setBounds(25, 10, 100, 20);
        pokemonLevel2.setBounds(240, 10, 50, 20);

        JPanel barraLevel2 = new JPanel();
        barraLevel2.setBounds(80, 40, 180, 10);
        barraLevel2.setBackground(GameColors.HP_FULL);
        barraLevel2.setLayout(null);
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
        JLabel linha2 = new JLabel("TORCHIC do?");
        linha2.setForeground(GameColors.TEXT_WHITE);
        linha2.setFont(new Font("Consolas", Font.BOLD, 30));
        linha2.setBounds(40, 65, 500, 30);
        panelAux2.add(linha2);

        // PANEL PARA AS OPCOES DE ESCOLHA
        JPanel panelEscolhas = new JPanel();
        panelEscolhas.setLayout(new GridLayout(2, 2, 10, 10));
        panelEscolhas.setBackground(GameColors.MENU_WHITE_BG);
        panelEscolhas.setBounds(620, 10, 600, 140);
        panelInferiorPrincipal.add(panelEscolhas);

        // BOTOES PARA AS OPCOES DE ESCOLHA
        JButton buttonFight = new JButton("Fight");
        buttonFight.setFont(new Font("Arial", Font.BOLD, 30));
        buttonFight.setBackground(GameColors.MENU_WHITE_BG);
        buttonFight.setBorderPainted(false);
        buttonFight.setFocusPainted(false);
        JButton buttonPokemon = new JButton("Pokemon");
        buttonPokemon.setFont(new Font("Arial", Font.BOLD, 30));
        buttonPokemon.setBackground(GameColors.MENU_WHITE_BG);
        buttonPokemon.setBorderPainted(false);
        buttonPokemon.setFocusPainted(false);
        JButton buttonBag = new JButton("Bag");
        buttonBag.setFont(new Font("Arial", Font.BOLD, 30));
        buttonBag.setBackground(GameColors.MENU_WHITE_BG);
        buttonBag.setBorderPainted(false);
        buttonBag.setFocusPainted(false);
        JButton buttonRun = new JButton("Run");
        buttonRun.setFont(new Font("Arial", Font.BOLD, 30));
        buttonRun.setBackground(GameColors.MENU_WHITE_BG);
        buttonRun.setBorderPainted(false);
        buttonRun.setFocusPainted(false);

        //Agrupar Botoes:
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(buttonFight);
        buttonGroup.add(buttonPokemon);
        buttonGroup.add(buttonBag);
        buttonGroup.add(buttonRun);

        panelEscolhas.add(buttonFight);
        panelEscolhas.add(buttonPokemon);
        panelEscolhas.add(buttonBag);
        panelEscolhas.add(buttonRun);

        // Dimensoes:
        panelInferiorPrincipal.setBounds(0, 525, 1280, 180);

        // ------------------------------------------------------------------

        panel1.add(barraLevel1);
        panel1.add(nomePokemon1);
        panel1.add(pokemonLevel1);

        panel2.add(nomePokemon2);
        panel2.add(pokemonLevel2);
        panel2.add(barraLevel2);

        panel.add(panelInferiorPrincipal);
        //--------------- Adicao dos sprites --------------------------------------------------

        // -------- Sprinte LOTAD -----------------------------------
        // Nessesario fazer um tratamento de excessao para verificar se achou a imagem !
        ImageIcon lotadIcon = new ImageIcon(getClass().getResource("images/Lotad.png"));
        // Carregar o caminho: não faz configuracoes
        Image imageLotadConfig = lotadIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        // Faz as configs necessarias

        JLabel lotadSprite = new JLabel(new ImageIcon(imageLotadConfig));
        // O JLabel so pode receber ImageIcon, entao eu transformo imageCongig(Image) em ImageIcon e passo para JLabel (coldura)

        lotadSprite.setBounds(700,30, 300, 300);
        // -------------------------------------------------------------

        // -------- Sprinte TORCHIC -----------------------------------
        // Nessesario fazer um tratamento de excessao para verificar se achou a imagem !
        ImageIcon torchicIcon = new ImageIcon(getClass().getResource("images/Torchic.png"));

        Image imageTorchicConfig = torchicIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);

        JLabel torchicSprite = new JLabel(new ImageIcon(imageTorchicConfig));

        torchicSprite.setBounds(230, 220, 350, 350);
        // ------------------------------------------------------------

        //------- Sprite BattleBase ----------------------

        ImageIcon battleBaseIcon = new ImageIcon(getClass().getResource("images/BattleBase.png"));
        Image playerbattlebase = battleBaseIcon.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);

        JLabel battleBaseLabel = new JLabel(new ImageIcon(playerbattlebase));
        battleBaseLabel.setBounds(0, -140, 1280, 720);

        // -----------------------------------------------

        //------- Sprite MascSimbol ----------------------
        ImageIcon simbolMascIcon = new ImageIcon(getClass().getResource("images/MascSimbol.png"));
        Image simbolMascIconCpnfig = simbolMascIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

        JLabel simbolMasc =  new JLabel(new ImageIcon(simbolMascIconCpnfig));
        panel1.add(simbolMasc);
        simbolMasc.setBounds(83, 7, 25, 25);
        // -----------------------------------------------

        //------- Sprite MascSimbol ----------------------
        ImageIcon simbolFemIcon = new ImageIcon(getClass().getResource("images/FemSimbol.png"));
        Image simbolFemIconCpnfig = simbolFemIcon.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);

        JLabel simbolFem =  new JLabel(new ImageIcon(simbolFemIconCpnfig));
        panel2.add(simbolFem);
        simbolFem.setBounds(105, 7, 22, 22);
        // -----------------------------------------------

        // -----------------------------------------------------------------------------------------
        panel.add(panel1);
        panel.add(panel2);
        panel.add(lotadSprite);
        panel.add(torchicSprite);
        panel.add(battleBaseLabel);

    }

    public static void main(String[] args) {
        FBatalha frame = new FBatalha("Batalha");
        frame.setVisible(true);
    }
}
