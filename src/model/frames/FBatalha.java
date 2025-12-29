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
        JPanel panel2 = new JPanel();

        panel1.setBounds(50, 50, 300, 80);   // posição + tamanho
        panel2.setBounds(900, 400, 300, 80);

        panel1.setLayout(null);
        panel2.setLayout(null);

        panel1.setBackground(GameColors.HUD_BACKGROUND);
        panel1.setBorder(BorderFactory.createLineBorder(GameColors.HUD_BORDER));

        panel2.setBackground(GameColors.HUD_BACKGROUND);
        panel2.setBorder(BorderFactory.createLineBorder(GameColors.HUD_BORDER));

        //--------- Definicao dos dados do pokemos para a panel1 ----------
        JLabel nomePokemon1 = new JLabel("LOTAD");
        JLabel pokemonLevel1 = new JLabel("Lv7");
        nomePokemon1.setFont(new Font("Arial", Font.BOLD, 15));
        pokemonLevel1.setFont(new Font("Arial", Font.BOLD, 15));

        nomePokemon1.setBounds(25, 10, 60, 20);
        pokemonLevel1.setBounds(250, 10, 50, 10);

        JPanel barraLevel1 = new JPanel();
        barraLevel1.setBounds(60, 40, 180, 10);
        barraLevel1.setBackground(GameColors.HP_FULL);
        barraLevel1.setLayout(null);
        // ----------------------------------------------------------------

        //--------- Definicao dos dados do pokemos para a panel2 ----------
        JLabel nomePokemon2 = new JLabel("TORCHIC");
        JLabel pokemonLevel2 = new JLabel("Lv7");
        nomePokemon2.setFont(new Font("Arial", Font.BOLD, 15));
        pokemonLevel2.setFont(new Font("Arial", Font.BOLD, 15));

        nomePokemon2.setBounds(920, 370, 100, 100);
        pokemonLevel2.setBounds(1147, 370, 100, 100);

        JPanel barraLevel2 = new JPanel();
        barraLevel2.setBounds(957, 440, 180, 10);
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
        panelEscolhas.setBackground(GameColors.MENU_ACTIONS_BG);
        panelEscolhas.setBorder(BorderFactory.createLineBorder(GameColors.MENU_BORDER_PURPLE));
        panelEscolhas.setBounds(650, 10, 600, 140);
        panelInferiorPrincipal.add(panelEscolhas);

        // BOTOES PARA AS OPCOES DE ESCOLHA
        JRadioButton buttonFight = new JRadioButton("Fight");
        JRadioButton buttonPokemon = new JRadioButton("Pokemon");
        JRadioButton buttonBag = new JRadioButton("Bag");
        JRadioButton buttonRun = new JRadioButton("Run");
        buttonFight.setSelected(true);

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


        panel.add(nomePokemon2);
        panel.add(pokemonLevel2);
        panel.add(barraLevel2);
        panel.add(panelInferiorPrincipal);

        panel1.add(barraLevel1);
        panel1.add(nomePokemon1);
        panel1.add(pokemonLevel1);

        //--------------- Adicao dos sprites --------------------------------------------------

        // -------- Sprinte LOTAD -----------------------------------
        // Nessesario fazer um tratamento de excessao para verificar se achou a imagem !
        ImageIcon lotadIcon = new ImageIcon(getClass().getResource("images/Lotad.png"));

        Image imageLotadConfig = lotadIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);

        JLabel lotadSprite = new JLabel(new ImageIcon(imageLotadConfig));

        // Calculo para o alinhamento
        int alturaImagemLotad = 300;

        int panel1Y = panel1.getY();
        int panelHight  = panel1.getHeight();

        int yAlinhado =  panel1Y + (panelHight - alturaImagemLotad ) / 2;

        int xAlinhadoLotad = panel2.getX();

        lotadSprite.setBounds(xAlinhadoLotad, yAlinhado, 300, 300);
        // -------------------------------------------------------------

        // -------- Sprinte TORCHIC -----------------------------------
        // Nessesario fazer um tratamento de excessao para verificar se achou a imagem !
        ImageIcon torchicIcon = new ImageIcon(getClass().getResource("images/Torchic.png"));

        Image imageTorchicConfig = torchicIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);

        // Calculo para o alinhamento
        int alturaImagemTorchic = 300;

        int panel2Y = panel2.getY();
        int panelHight2 = panel2.getHeight();

        int YAlinhadoTorchic = panel2Y + (panelHight2 - alturaImagemTorchic ) / 2;

        JLabel torchicSprite = new JLabel(new ImageIcon(imageTorchicConfig));

        torchicSprite.setBounds(50, 295, 300, 300);
        // ------------------------------------------------------------

        // -----------------------------------------------------------------------------------------

        panel.add(panel1);
        panel.add(panel2);
        panel.add(lotadSprite);
        panel.add(torchicSprite);
    }

    public static void main(String[] args) {
        FBatalha frame = new FBatalha("Batalha");
        frame.setVisible(true);
    }
}
