package model.frames.ranking;

import model.jogador.Jogador;
import model.frames.loja.SistemaDeArquivos;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FRanking extends JFrame {

    public FRanking() {
        setTitle("Ranking - Top 50");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // ===== 1. Fundo =====
        ImageIcon fundoPrincipal = new ImageIcon(getClass().getResource("/model/frames/images/FundosSimbolos/ImagemRanking.png"));
        Image img = fundoPrincipal.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(img));
        background.setBounds(0, 0, 1280, 720);
        background.setLayout(null);
        setContentPane(background);

        // ===== 2. Dados =====
        List<Jogador> todosJogadores = carregarSaves();

        // Ranking Captura
        todosJogadores.sort(Comparator.comparingInt(Jogador::getPokemonsCapturados).reversed());
        String[][] dadosCaptura = formatarDadosParaRanking(todosJogadores, "captura");

        // Ranking Dinheiro
        todosJogadores.sort(Comparator.comparingInt(Jogador::getDinheiro).reversed());
        String[][] dadosDinheiro = formatarDadosParaRanking(todosJogadores, "dinheiro");

        // ===== 3. Painéis com Scroll =====
        
        // Painel Esquerdo (Mestres) - Texto Verde
        JPanel painelMestres = PainelRanking("Mestres Pokémon", dadosCaptura, new Color(100, 255, 100));
        painelMestres.setBounds(150, 50, 420, 450); // Aumentei um pouco a largura para caber a barra de scroll
        background.add(painelMestres);

        // Painel Direito (Ricos) - Texto Dourado
        JPanel painelRicos = PainelRanking("Magnatas", dadosDinheiro, new Color(255, 215, 0));
        painelRicos.setBounds(710, 50, 420, 450);
        background.add(painelRicos);

        // Botão Voltar
        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.setBounds(540, 600, 200, 50);
        btnVoltar.setFont(new Font("Monospaced", Font.BOLD, 20));
        btnVoltar.setBackground(new Color(255, 204, 51));
        btnVoltar.setBorder(new LineBorder(Color.BLACK, 3));
        btnVoltar.setFocusPainted(false);
        background.add(btnVoltar);
        btnVoltar.addActionListener(e -> {
            dispose();
            new model.frames.inicio.FInicio().setVisible(true);
        });
    }

    private List<Jogador> carregarSaves() {
        List<Jogador> lista = new ArrayList<>();
        File pasta = new File("saves");
        if (pasta.exists() && pasta.isDirectory()) {
            File[] arquivos = pasta.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    Jogador j = SistemaDeArquivos.carregarJogador(arquivo.getPath());
                    if (j != null) lista.add(j);
                }
            }
        }
        return lista;
    }

    private String[][] formatarDadosParaRanking(List<Jogador> jogadores, String tipo) {
        // ALTERAÇÃO: Limite aumentado para 50
        int tamanho = Math.min(jogadores.size(), 50);
        String[][] dados = new String[tamanho][2];

        for (int i = 0; i < tamanho; i++) {
            Jogador j = jogadores.get(i);
            String nomeFormatado = (i + 1) + ". " + j.getNome();
            String valorFormatado = "";

            if (tipo.equals("captura")) {
                valorFormatado = j.getPokemonsCapturados() + " Capturas";
            } else if (tipo.equals("dinheiro")) {
                valorFormatado = "R$ " + j.getDinheiro();
            }

            dados[i][0] = nomeFormatado;
            dados[i][1] = valorFormatado;
        }
        
        if (tamanho == 0) return new String[][]{{"Nenhum save", "-"}};
        return dados;
    }

    /**
     * Cria o painel com título fixo e lista rolável (Scroll).
     */
    private JPanel PainelRanking(String titulo, String[][] dados, Color corDestaque) {
        // 1. Painel Principal (Container)
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setBackground(new Color(0, 0, 0, 180)); // Fundo semi-transparente
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBorder(new LineBorder(Color.WHITE, 2));

        // 2. Título (Fica fixo no topo, fora do scroll)
        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Monospaced", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(new EmptyBorder(10, 0, 10, 0)); // Espaçamento
        painelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // 3. Painel Interno da Lista (Onde ficam os nomes)
        JPanel painelLista = new JPanel();
        painelLista.setLayout(new BoxLayout(painelLista, BoxLayout.Y_AXIS));
        painelLista.setBackground(new Color(0, 0, 0, 0)); // Totalmente transparente (herda o fundo do pai)

        for (String[] linha : dados) {
            JPanel linhaPanel = new JPanel(new BorderLayout());
            linhaPanel.setBackground(new Color(0, 0, 0, 0)); // Transparente
            linhaPanel.setMaximumSize(new Dimension(380, 40));
            linhaPanel.setPreferredSize(new Dimension(380, 40));

            JLabel lblNome = new JLabel(" " + linha[0]);
            lblNome.setFont(new Font("SansSerif", Font.BOLD, 16)); // Fonte levemente menor para caber mais
            lblNome.setForeground(Color.WHITE);

            JLabel lblValor = new JLabel(linha[1] + "  "); // Espaço extra na direita pra não colar na barra
            lblValor.setFont(new Font("SansSerif", Font.BOLD, 16));
            lblValor.setForeground(corDestaque);

            linhaPanel.add(lblNome, BorderLayout.WEST);
            linhaPanel.add(lblValor, BorderLayout.EAST);

            painelLista.add(linhaPanel);
            painelLista.add(Box.createRigidArea(new Dimension(0, 5))); // Espaço menor entre linhas
        }

        // 4. Configurando o ScrollPane
        JScrollPane scrollPane = new JScrollPane(painelLista);
        scrollPane.setBorder(null); // Remove borda padrão do scroll
        scrollPane.getViewport().setOpaque(false); // Área de visualização transparente
        scrollPane.setOpaque(false); // ScrollPane transparente
        
        // Remove a barra horizontal
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        // Customiza a barra vertical para ficar bonita no jogo
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Velocidade do scroll
        scrollPane.getVerticalScrollBar().setUI(new EstiloBarraRolagem(corDestaque));

        painelPrincipal.add(scrollPane, BorderLayout.CENTER);

        return painelPrincipal;
    }

    // Classe interna para customizar a aparência da barra de rolagem (UI)
    static class EstiloBarraRolagem extends BasicScrollBarUI {
        private final Color corThumb;

        public EstiloBarraRolagem(Color cor) {
            this.corThumb = cor;
        }

        @Override
        protected void configureScrollBarColors() {
            this.thumbColor = corThumb;
            this.trackColor = new Color(0, 0, 0, 50); // Fundo da trilha levemente escuro
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }

        private JButton createZeroButton() {
            JButton btn = new JButton();
            btn.setPreferredSize(new Dimension(0, 0)); // Remove os botões de seta
            return btn;
        }
        
        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            if(thumbBounds.isEmpty() || !scrollbar.isEnabled()) return;
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(corThumb);
            // Desenha a barra arredondada e mais fina
            g2.fillRoundRect(thumbBounds.x + 4, thumbBounds.y, 8, thumbBounds.height, 10, 10);
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        FRanking frame = new FRanking();
        frame.setVisible(true);
    }
}