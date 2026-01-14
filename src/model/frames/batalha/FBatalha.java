package model.frames.batalha;
import model.batalha.Comparacao;
import model.batalha.Gerenciador;
import model.frames.GameColors;
import model.frames.inicio.Sessao;
import model.frames.jogador.SistemaDeArquivos;
import model.pokedex.Pokedex;
import model.pokemon.Pokemon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class FBatalha extends JFrame{

    private Comparacao comparacao;  // Objeto criado para poder utilizar os metodos da classe
    private Gerenciador g;  // Servira para a animação do poder

    public FBatalha(String title, Comparacao comparacao){
        super(title);
        this.comparacao =  comparacao;
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        g = new Gerenciador();
        g.setBounds(0, 0, 1280, 720);
        // O gerenciador é meio que uma "segunda tela" que fica por trás, ela é transparente e vai rodar
        // o movimento do poder percorrendo de um pokemon ao outro
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

        perguntaAcao.setText(pokemon1.getNome().toUpperCase() + " do");
    }

    //Metodo para carregar o sprite
    private void carregarSprite(JLabel label, String caminho, int largura, int altura) {
        try {
            if (caminho == null) {
                System.out.println("caminhoImagem is null para o pokemon");
                return;
            }
            java.net.URL url = getClass().getResource(caminho);
            // java.net.URL -> retorna uma URL, o getClass() pega a referencia da classe que ele esta
            // o getResource() nao procura arquivos internos do Pc, pois se fosse assim, nao funcionaria
            // para outrs PCs (C:/Users/Voce/Projeto/img.png - > vai mudar de pc para pc)
            // o getResource vai procurar dentro das pastas do projeto.
            if (url == null) {
                System.out.println("Recurso de imagem não encontrado: " + caminho);
                return;
            }
            ImageIcon icon = new ImageIcon(url);
            //Criado usando a url
            Image img = icon.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH); //deixa a img bonita
            //Ajuste
            label.setIcon(new ImageIcon(img));
            // Transformando a img em um label "porta retrato que vai a imagem"
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagem: " + caminho);

        }
    }

    private void finalizarBatalha() {
        Pokemon vencedor = comparacao.realizarComparacao();
        Pokemon p1 = comparacao.getPokemon1();
        Pokemon p2 = comparacao.getPokemon2();
        boolean jogadorVenceu = vencedor.equals(p1);
        int moedasGanhas = 0;
        int scoreGanho = 0;
        StringBuilder msgRecompensa = new StringBuilder();
        // StringBuilder é melhor de usar e mais pratico do que o String, pois a concatenação de Strings
        // fica mais facil
        if (jogadorVenceu && Sessao.jogadorLogado != null) {
            moedasGanhas = 100;
            scoreGanho = 50;

            Sessao.jogadorLogado.ganharDinheiro(moedasGanhas);
            Sessao.jogadorLogado.adicionarScore(scoreGanho);
            boolean salvou = SistemaDeArquivos.salvarJogador(Sessao.jogadorLogado, Sessao.jogadorLogado.getNome());
            // Atualização dos atributos de moeda e score e salvar no arquivo

            msgRecompensa.append("RECOMPENSAS:\n");
            msgRecompensa.append("+").append(moedasGanhas).append(" Moedas\n");
            msgRecompensa.append("+").append(scoreGanho).append(" Score\n");
            if (!salvou) msgRecompensa.append("(Erro ao salvar arquivo)\n");
        } else if (!jogadorVenceu) {
            msgRecompensa.append("Você perdeu... Tente novamente!\n");
        }
        JPanel painelResultados = new JPanel(new BorderLayout());

        // Título
        JLabel lblTitulo = new JLabel(jogadorVenceu ? "VITÓRIA!" : "DERROTA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(jogadorVenceu ? new Color(34, 139, 34) : Color.RED);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelResultados.add(lblTitulo, BorderLayout.NORTH);

        // Tabela de Status
        JPanel painelStats = new JPanel(new GridLayout(8, 3, 10, 5)); // 8 linhas (cabeçalho + 6 stats + média), 3 colunas
        painelStats.setBorder(new EmptyBorder(15, 10, 15, 10));

        // Cabeçalho
        painelStats.add(new JLabel("Atributo", SwingConstants.CENTER));
        painelStats.add(new JLabel(p1.getNome(), SwingConstants.CENTER));
        painelStats.add(new JLabel(p2.getNome(), SwingConstants.CENTER));

        // Adiciona linhas de comparação
        adicionarLinhaStat(painelStats, "HP", p1.getHp(), p2.getHp());
        adicionarLinhaStat(painelStats, "Atk", p1.getAtaque(), p2.getAtaque());
        adicionarLinhaStat(painelStats, "Def", p1.getDefesa(), p2.getDefesa());
        adicionarLinhaStat(painelStats, "Sp. Atk", p1.getSpAtaque(), p2.getSpAtaque());
        adicionarLinhaStat(painelStats, "Sp. Def", p1.getSpDefesa(), p2.getSpDefesa());
        adicionarLinhaStat(painelStats, "Speed", p1.getVelocidade(), p2.getVelocidade());

        JLabel lblMedia = new JLabel("MÉDIA GERAL");
        lblMedia.setFont(new Font("Arial", Font.BOLD, 14));
        painelStats.add(lblMedia);

        DecimalFormat df = new DecimalFormat("#.0");
        JLabel lblM1 = new JLabel(df.format(comparacao.calcularMediaStatus(p1)), SwingConstants.CENTER);
        JLabel lblM2 = new JLabel(df.format(comparacao.calcularMediaStatus(p2)), SwingConstants.CENTER);
        lblM1.setFont(new Font("Arial", Font.BOLD, 14));
        lblM2.setFont(new Font("Arial", Font.BOLD, 14));

        if(comparacao.calcularMediaStatus(p1) > comparacao.calcularMediaStatus(p2)) lblM1.setForeground(new Color(0, 150, 0));
        else lblM2.setForeground(new Color(0, 150, 0));

        painelStats.add(lblM1);
        painelStats.add(lblM2);

        painelResultados.add(painelStats, BorderLayout.CENTER);

        JTextArea txtRecompensa = new JTextArea(msgRecompensa.toString());
        txtRecompensa.setEditable(false);
        txtRecompensa.setBackground(null);
        txtRecompensa.setFont(new Font("Consolas", Font.BOLD, 14));
        txtRecompensa.setBorder(new EmptyBorder(10, 20, 10, 20));
        painelResultados.add(txtRecompensa, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(this, painelResultados, "Resultado da Batalha", JOptionPane.PLAIN_MESSAGE);

        this.dispose(); // Fecha a janela de batalha
    }

    private void adicionarLinhaStat(JPanel painel, String nomeStat, int v1, int v2) {
        painel.add(new JLabel(nomeStat));

        JLabel l1 = new JLabel(String.valueOf(v1), SwingConstants.CENTER);
        JLabel l2 = new JLabel(String.valueOf(v2), SwingConstants.CENTER);

        if (v1 > v2) {
            l1.setFont(new Font("Arial", Font.BOLD, 12));
            l1.setForeground(Color.BLUE);
        } else if (v2 > v1) {
            l2.setFont(new Font("Arial", Font.BOLD, 12));
            l2.setForeground(Color.RED);
        }

        painel.add(l1);
        painel.add(l2);
    }

    private void initComponents() {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1280, 720);
        setContentPane(layeredPane);

        // JLayeredPane ele cria um paienl que fica "ao fundo".É necessario pois nessa tela de batalha tem que
        // ter a principal e a movimentação da esfera de poder que aocntece no fundo, mas como o fundo é
        // invisivel, ela parece ocorrer na tela principal

        JPanel panelFundo = new JPanel();
        panelFundo.setLayout(null);
        panelFundo.setBackground(GameColors.BATTLE_BG_GRASS);
        panelFundo.setBounds(0, 0, 1280, 720);

        layeredPane.add(panelFundo, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(g, JLayeredPane.PALETTE_LAYER);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        setContentPane(panel);
        panel.setBackground(GameColors.BATTLE_BG_GRASS);

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
        buttonFight.setFont(new Font("Arial", Font.BOLD, 80));
        buttonFight.setBackground(GameColors.MENU_WHITE_BG);
        buttonFight.setBorderPainted(false);
        buttonFight.setFocusPainted(false);

        // ---- Acao do botao fight ----------
        buttonFight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pega coordenadas para o tiro
                int x1 = spritePokemon1.getX() + spritePokemon1.getWidth()/2;
                int y1 = spritePokemon1.getY() + spritePokemon1.getHeight()/2;
                int x2 = spritePokemon2.getX() + spritePokemon2.getWidth()/2;
                int y2 = spritePokemon2.getY() + spritePokemon2.getHeight()/2;

                // Animação
                g.criarAtaque(x1, y1, x2, y2);
                g.criarAtaque(x2, y2, x1, y1); // Ataque mútuo

                // Timer para esperar a animação acabar e vir o resultado
                Timer timer = new Timer(1000, ev -> {
                    finalizarBatalha();
                });
                timer.setRepeats(false);
                timer.start();
            }
        });

        // -----------------------------------

        buttonFight.setFont(new Font("Arial", Font.BOLD, 80));
        buttonFight.setBackground(GameColors.MENU_WHITE_BG);
        buttonFight.setBorderPainted(false);
        buttonFight.setFocusPainted(false);

        panelEscolhas.add(buttonFight);

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
        panel.add(g);
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
