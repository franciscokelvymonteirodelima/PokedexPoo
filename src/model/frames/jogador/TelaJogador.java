package model.frames.jogador;

import model.jogador.Jogador;
import model.pokemon.Pokemon;
import model.frames.jogador.TelaMenu;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;

public class TelaJogador extends JFrame {

    private Jogador jogador;
    
    // Cores padronizadas
    private static final Color COR_FUNDO = new Color(220, 100, 100);
    private static final Color COR_PAINEL = new Color(240, 240, 240);
    private static final Color COR_BORDA = new Color(180, 80, 80);
    private static final Color COR_TEXTO = Color.BLACK;

    public TelaJogador(Jogador jogador) {
        this.jogador = jogador;

        setTitle("Perfil do Jogador");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Define cor de fundo da janela
        getContentPane().setBackground(COR_FUNDO);

        inicializarTela();
    }

    private void inicializarTela() {
        setLayout(new BorderLayout(15, 15));

        add(criarPainelPerfil(), BorderLayout.NORTH);
        add(criarPainelCentral(), BorderLayout.CENTER);
        add(criarPainelBotoes(), BorderLayout.SOUTH);
    }

    public void atualizarInterface() {
        // Remove tudo o que está na tela atualmente
        getContentPane().removeAll();
        
        // Chama o método que monta a tela novamente com os dados atualizados
        inicializarTela();
        
        // Avisa o Swing para revalidar o layout e repintar
        revalidate();
        repaint();
    }

    /* ================= PERFIL ================= */
    private JPanel criarPainelPerfil() {
        JPanel painel = new JPanel(new BorderLayout(15, 15));
        painel.setBackground(COR_PAINEL);
        painel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COR_BORDA, 2),
                "Perfil",
                0,
                0,
                null,
                COR_TEXTO
            ),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel lblImagem = criarLabelImagem();

        JPanel info = new JPanel(new GridLayout(2, 3, 10, 10));
        info.setBackground(COR_PAINEL);
        
        info.add(criarLabel("Nome: " + jogador.getNome()));
        info.add(criarLabel("Idade: " + jogador.getIdade()));
        info.add(criarLabel("Gênero: " + jogador.getGenero()));
        info.add(criarLabel("Cidade: " + jogador.getCidadeOrigem()));
        info.add(criarLabel("Dinheiro: $" + jogador.getDinheiro()));
        info.add(criarLabel("Capturados: " + jogador.getPokemonsCapturados()));

        painel.add(lblImagem, BorderLayout.WEST);
        painel.add(info, BorderLayout.CENTER);

        return painel;
    }

    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setForeground(COR_TEXTO);
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        return label;
    }

    /* ================= IMAGEM ================= */
    private JLabel criarLabelImagem() {
        String caminho = System.getProperty("user.dir")
            + "/src/model/frames/images/personagens/ash.webp";

        File arquivo = new File(caminho);

        if (arquivo.exists()) {
            ImageIcon icon = new ImageIcon(caminho);
            Image img = icon.getImage().getScaledInstance(120, 160, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(img));
        }

        JLabel erro = new JLabel("Sem imagem");
        erro.setPreferredSize(new Dimension(120, 160));
        erro.setHorizontalAlignment(SwingConstants.CENTER);
        erro.setForeground(COR_TEXTO);

        System.err.println("Imagem não encontrada: " + caminho);
        return erro;
    }

    /* ================= CENTRAL ================= */
    private JTabbedPane criarPainelCentral() {
        JTabbedPane abas = new JTabbedPane();
        abas.setBackground(COR_PAINEL);
        abas.setForeground(COR_TEXTO);

        abas.addTab("Time Pokémon", criarPainelTime());
        abas.addTab("PC Box", criarPainelPC());

        return abas;
    }

    /* ================= TIME ================= */
    private JPanel criarPainelTime() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(COR_PAINEL);
        painel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COR_BORDA, 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        DefaultListModel<String> model = new DefaultListModel<>();
        for (Pokemon p : jogador.getTimePokemon()) {
            model.addElement(p.getNome());
        }

        JList<String> lista = new JList<>(model);
        lista.setBackground(Color.WHITE);
        lista.setForeground(COR_TEXTO);
        lista.setFont(new Font("Arial", Font.PLAIN, 12));
        lista.setBorder(BorderFactory.createLineBorder(COR_BORDA, 1));
        
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBorder(BorderFactory.createLineBorder(COR_BORDA, 2));
        
        painel.add(scroll, BorderLayout.CENTER);

        return painel;
    }

    /* ================= PC ================= */
    private JPanel criarPainelPC() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(COR_PAINEL);
        painel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COR_BORDA, 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        DefaultListModel<String> model = new DefaultListModel<>();
        for (Pokemon p : jogador.getPcBox()) {
            model.addElement(p.getNome());
        }

        JList<String> lista = new JList<>(model);
        lista.setBackground(Color.WHITE);
        lista.setForeground(COR_TEXTO);
        lista.setFont(new Font("Arial", Font.PLAIN, 12));
        lista.setBorder(BorderFactory.createLineBorder(COR_BORDA, 1));
        
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBorder(BorderFactory.createLineBorder(COR_BORDA, 2));
        
        painel.add(scroll, BorderLayout.CENTER);

        return painel;
    }

    /* ============= COLECIONÁVEIS ============= */
    private JPanel criarPainelColecionaveis() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(COR_PAINEL);
        painel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COR_BORDA, 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        DefaultListModel<String> model = new DefaultListModel<>();
        for (String item : jogador.getColecionaveis()) {
            model.addElement(item);
        }

        JList<String> lista = new JList<>(model);
        lista.setBackground(Color.WHITE);
        lista.setForeground(COR_TEXTO);
        lista.setFont(new Font("Arial", Font.PLAIN, 12));
        lista.setBorder(BorderFactory.createLineBorder(COR_BORDA, 1));
        
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBorder(BorderFactory.createLineBorder(COR_BORDA, 2));
        
        painel.add(scroll, BorderLayout.CENTER);

        return painel;
    }

    /* ================= BOTOES ================= */
    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new FlowLayout());
        painel.setBackground(COR_FUNDO);

        JButton btnSalvar = criarBotao("Salvar Jogo");
        btnSalvar.addActionListener(e -> jogador.salvarProgresso());

        JButton btnVoltar = criarBotao("Voltar ao Menu");
        btnVoltar.addActionListener(e -> {
            new TelaMenu().setVisible(true);
            this.dispose();
        });

        JButton btnEditar = criarBotao("Editar Time");
        btnEditar.addActionListener(e -> {
            // Abre a tela de edição
            TelaGerenciarTime telaEdicao = new TelaGerenciarTime(jogador, this);
            telaEdicao.setVisible(true);
        
            // Assim que o código chegar aqui, significa que a tela de edição foi fechada
            // Então chamamos o refresh automático:
            atualizarInterface();
        });

        painel.add(btnSalvar);
        painel.add(btnVoltar);
        painel.add(btnEditar);

        return painel;
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setBackground(COR_PAINEL);
        botao.setForeground(COR_TEXTO);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COR_BORDA, 2),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        botao.setFont(new Font("Arial", Font.BOLD, 12));
        
        // Efeito hover
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(new Color(255, 255, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(COR_PAINEL);
            }
        });
        
        return botao;
    }
}