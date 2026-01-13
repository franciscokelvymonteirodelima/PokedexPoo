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

        // Fundo
        ImageIcon fundoPrincipal = new ImageIcon(getClass().getResource("/model/frames/images/FundosSimbolos/ImagemRanking.png"));
        Image img = fundoPrincipal.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(img));
        background.setBounds(0, 0, 1280, 720);
        background.setLayout(null);
        setContentPane(background);

        // Dados dos Jogadores em .txt
        List<Jogador> todosJogadores = carregarSaves();

        // Ranking Score Minigame
        todosJogadores.sort(Comparator.comparingInt(Jogador::getScore).reversed());
        String[][] dadosCaptura = formatarDados(todosJogadores, "score");

        // Ranking Dinheiro
        todosJogadores.sort(Comparator.comparingInt(Jogador::getDinheiro).reversed());
        String[][] dadosDinheiro = formatarDados(todosJogadores, "dinheiro");

        // ===== 3. Painéis com Scroll =====
        
        // Painel Esquerdo (Mestres) - Texto Verde
        JPanel painelMestres = PainelRanking("Pokedex Human", dadosCaptura, new Color(100, 255, 100));
        painelMestres.setBounds(150, 50, 420, 450);
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
        btnVoltar.addActionListener(e -> dispose());
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

    private String[][] formatarDados(List<Jogador> jogadores, String tipo) {
        int tamanho = Math.min(jogadores.size(), 50);
        String[][] dados = new String[tamanho][2];

        for (int i = 0; i < tamanho; i++) {
            Jogador j = jogadores.get(i);
            String nomeFormatado = (i + 1) + ". " + j.getNome();
            String valorFormatado = "";

            if (tipo.equals("score")) {
                valorFormatado = j.getScore() + " Points";
            } else if (tipo.equals("dinheiro")) {
                valorFormatado = "R$ " + j.getDinheiro();
            }

            dados[i][0] = nomeFormatado;
            dados[i][1] = valorFormatado;
        }
        
        if (tamanho == 0) return new String[][]{{"Nenhum save", "-"}};
        return dados;
    }

    // Paineis de Ranking com Scroll
    private JPanel PainelRanking(String titulo, String[][] dados, Color corDestaque) {
        // Painel Principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setBackground(new Color(0, 0, 0, 180)); // Fundo semi-transparente
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBorder(new LineBorder(Color.WHITE, 2));

        // Titulo do Painel
        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Monospaced", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(new EmptyBorder(10, 0, 10, 0)); // Espaçamento
        painelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // Painel com a lista de jogadores
        JPanel painelLista = new JPanel();
        painelLista.setLayout(new BoxLayout(painelLista, BoxLayout.Y_AXIS));
        painelLista.setBackground(new Color(0, 0, 0, 180)); // Fundo semi-transparente para evitar problemas de renderização
        painelLista.setOpaque(true);

        for (String[] linha : dados) {
            JPanel linhaPanel = new JPanel(new BorderLayout());
            linhaPanel.setBackground(new Color(0, 0, 0, 0)); // Transparente
            linhaPanel.setMaximumSize(new Dimension(380, 40));
            linhaPanel.setPreferredSize(new Dimension(380, 40));

            JLabel lblNome = new JLabel(" " + linha[0]);
            lblNome.setFont(new Font("SansSerif", Font.BOLD, 16));
            lblNome.setForeground(Color.WHITE);

            JLabel lblValor = new JLabel(linha[1] + "  ");
            lblValor.setFont(new Font("SansSerif", Font.BOLD, 16));
            lblValor.setForeground(corDestaque);

            linhaPanel.add(lblNome, BorderLayout.WEST);
            linhaPanel.add(lblValor, BorderLayout.EAST);

            painelLista.add(linhaPanel);
            painelLista.add(Box.createRigidArea(new Dimension(0, 5))); // Espaço menor entre linhas
        }

        JScrollPane scrollPane = new JScrollPane(painelLista);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setOpaque(true);
        scrollPane.getViewport().setBackground(new Color(0, 0, 0, 180));
        scrollPane.setOpaque(true);
        
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Velocidade do scroll
        scrollPane.getVerticalScrollBar().setUI(new EstiloBarraRolagem(corDestaque));

        painelPrincipal.add(scrollPane, BorderLayout.CENTER);

        return painelPrincipal;
    }

    // Customização da barra de rolagem
    static class EstiloBarraRolagem extends BasicScrollBarUI {
        private final Color corThumb;

        public EstiloBarraRolagem(Color cor) {
            this.corThumb = cor;
        }

        @Override
        protected void configureScrollBarColors() {
            this.thumbColor = corThumb;
            this.trackColor = new Color(0, 0, 0, 50); // Fundo meio transparente da barra
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
            // deixa a barra mais fina
            g2.fillRoundRect(thumbBounds.x + 4, thumbBounds.y, 8, thumbBounds.height, 10, 10);
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        FRanking frame = new FRanking();
        frame.setVisible(true);
    }
}