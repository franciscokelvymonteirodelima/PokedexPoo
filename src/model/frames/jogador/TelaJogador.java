package model.frames.jogador;

import model.jogador.Jogador;
import model.pokemon.Pokemon;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class TelaJogador extends JFrame {

    // Cor vermelho suave padrão (igual à TelaMenu)
    private static final Color VERMELHO_SUAVE = new Color(220, 100, 100);
    
    private Jogador jogador;

    public TelaJogador(Jogador jogador) {
        this.jogador = jogador;

        setTitle("Perfil do Jogador");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        inicializarTela();
    }

    private void inicializarTela() {
        setLayout(new BorderLayout(15, 15));
        
        // Fundo vermelho (igual à TelaCriacaoPerfil)
        getContentPane().setBackground(VERMELHO_SUAVE);

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

    private JPanel criarPainelPerfil() {
        JPanel painel = new JPanel(new BorderLayout(15, 15));
        // Borda com título em branco (sobre fundo vermelho)
        painel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE, 2),
            "Perfil",
            0, 0,
            new Font("Arial", Font.BOLD, 14),
            Color.WHITE
        ));
        painel.setBackground(VERMELHO_SUAVE);

        JLabel lblImagem = criarLabelImagem();

        JPanel info = new JPanel(new GridLayout(2, 3, 10, 10));
        info.setBackground(VERMELHO_SUAVE);
        
        // Labels com texto branco (sobre fundo vermelho)
        JLabel lblNome = new JLabel("Nome: " + jogador.getNome());
        lblNome.setForeground(Color.WHITE);
        info.add(lblNome);
        
        JLabel lblIdade = new JLabel("Idade: " + jogador.getIdade());
        lblIdade.setForeground(Color.WHITE);
        info.add(lblIdade);
        
        JLabel lblGenero = new JLabel("Gênero: " + jogador.getGenero());
        lblGenero.setForeground(Color.WHITE);
        info.add(lblGenero);
        
        JLabel lblCidade = new JLabel("Cidade: " + jogador.getCidadeOrigem());
        lblCidade.setForeground(Color.WHITE);
        info.add(lblCidade);
        
        JLabel lblDinheiro = new JLabel("Dinheiro: $" + jogador.getDinheiro());
        lblDinheiro.setForeground(Color.WHITE);
        info.add(lblDinheiro);
        
        JLabel lblCapturados = new JLabel("Capturados: " + jogador.getPokemonsCapturados());
        lblCapturados.setForeground(Color.WHITE);
        info.add(lblCapturados);

        painel.add(lblImagem, BorderLayout.WEST);
        painel.add(info, BorderLayout.CENTER);

        return painel;
    }

    private JLabel criarLabelImagem() {

    String caminho = System.getProperty("user.dir")
    + "/src/model/frames/images/personagens/ash.webp"; // Mudou para WEBP

    File arquivo = new File(caminho);

    if (arquivo.exists()) {
        ImageIcon icon = new ImageIcon(caminho);
        Image img = icon.getImage().getScaledInstance(120, 160, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(img));
    }

    JLabel erro = new JLabel("Sem imagem");
    erro.setPreferredSize(new Dimension(120, 160));
    erro.setHorizontalAlignment(SwingConstants.CENTER);

    System.err.println("Imagem não encontrada: " + caminho);
    return erro;
}

    private JTabbedPane criarPainelCentral() {
        JTabbedPane abas = new JTabbedPane();
        abas.setBackground(VERMELHO_SUAVE);
        abas.setForeground(Color.WHITE);

        abas.addTab("Time Pokémon", criarPainelTime());
        abas.addTab("PC Box", criarPainelPC());

        return abas;
    }

    private JPanel criarPainelTime() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painel.setBackground(VERMELHO_SUAVE);

        DefaultListModel<String> model = new DefaultListModel<>();
        for (Pokemon p : jogador.getTimePokemon()) {
            model.addElement(p.getNome());  // Removi para testes: + " (Lv." + p.getNivel() + ")"
        }

        JList<String> lista = new JList<>(model);
        lista.setBackground(Color.WHITE);
        lista.setForeground(VERMELHO_SUAVE);
        painel.add(new JScrollPane(lista), BorderLayout.CENTER);

        return painel;
    }

    private JPanel criarPainelPC() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painel.setBackground(VERMELHO_SUAVE);

        DefaultListModel<String> model = new DefaultListModel<>();
        for (Pokemon p : jogador.getPcBox()) {
            model.addElement(p.getNome());  // Removi para testes: + " (Lv." + p.getNivel() + ")"
        }

        JList<String> lista = new JList<>(model);
        lista.setBackground(Color.WHITE);
        lista.setForeground(VERMELHO_SUAVE);
        painel.add(new JScrollPane(lista), BorderLayout.CENTER);

        return painel;
    }

    private JPanel criarPainelColecionaveis() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painel.setBackground(Color.WHITE);

        DefaultListModel<String> model = new DefaultListModel<>();
        for (Integer item : jogador.getColecionaveisComprados()) {
            model.addElement(String.valueOf(item));
        }

        JList<String> lista = new JList<>(model);
        lista.setBackground(Color.WHITE);
        lista.setForeground(VERMELHO_SUAVE);
        painel.add(new JScrollPane(lista), BorderLayout.CENTER);

        return painel;
    }

    private JPanel criarPainelBotoes() {

        JButton btnEditar = criarBotaoEstilizado("Editar Time");
        btnEditar.addActionListener(e -> {
        // Abre a tela de edição
        TelaGerenciarTime telaEdicao = new TelaGerenciarTime(jogador, this);
        telaEdicao.setVisible(true);
    
        // Assim que o código chegar aqui, significa que a tela de edição foi fechada
        // Então chamamos o refresh automático:
        atualizarInterface();
        });
    

        JPanel painel = new JPanel(new FlowLayout());
        painel.setBackground(VERMELHO_SUAVE);

        JButton btnSalvar = criarBotaoEstilizado("Salvar Jogo");
        btnSalvar.addActionListener(e -> jogador.salvarProgresso());

        JButton btnVoltar = criarBotaoEstilizado("Voltar ao Menu");
//        btnVoltar.addActionListener(e -> {
//            new TelaMenu().setVisible(true);
//            this.dispose();
//        });

        btnVoltar.addActionListener(e -> {
            this.dispose();
        });

        painel.add(btnSalvar);
        painel.add(btnVoltar);
        painel.add(btnEditar);

        return painel;
    }
    
    private JButton criarBotaoEstilizado(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(Color.WHITE);
        btn.setForeground(VERMELHO_SUAVE);
        btn.setFocusPainted(false);
        return btn;
    }
}
