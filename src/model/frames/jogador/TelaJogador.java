package model.frames.jogador;

import model.jogador.Jogador;
import model.pokemon.Pokemon;
import model.frames.jogador.TelaMenu;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class TelaJogador extends JFrame {

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

        add(criarPainelPerfil(), BorderLayout.NORTH);
        add(criarPainelCentral(), BorderLayout.CENTER);
        add(criarPainelBotoes(), BorderLayout.SOUTH);
    }

    /* ================= PERFIL ================= */
    private JPanel criarPainelPerfil() {
        JPanel painel = new JPanel(new BorderLayout(15, 15));
        painel.setBorder(BorderFactory.createTitledBorder("Perfil"));

        JLabel lblImagem = criarLabelImagem();

        JPanel info = new JPanel(new GridLayout(2, 3, 10, 10));
        info.add(new JLabel("Nome: " + jogador.getNome()));
        info.add(new JLabel("Idade: " + jogador.getIdade()));
        info.add(new JLabel("Gênero: " + jogador.getGenero()));
        info.add(new JLabel("Cidade: " + jogador.getCidadeOrigem()));
        info.add(new JLabel("Dinheiro: $" + jogador.getDinheiro()));
        info.add(new JLabel("Capturados: " + jogador.getPokemonsCapturados()));

        painel.add(lblImagem, BorderLayout.WEST);
        painel.add(info, BorderLayout.CENTER);

        return painel;
    }

    /* ================= IMAGEM ================= */
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


    /* ================= CENTRAL ================= */
    private JTabbedPane criarPainelCentral() {
        JTabbedPane abas = new JTabbedPane();

        abas.addTab("Time Pokémon", criarPainelTime());
        abas.addTab("PC Box", criarPainelPC());

        return abas;
    }

    /* ================= TIME ================= */
    private JPanel criarPainelTime() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        DefaultListModel<String> model = new DefaultListModel<>();
        for (Pokemon p : jogador.getTimePokemon()) {
            model.addElement(p.getNome());  // Removi para testes: + " (Lv." + p.getNivel() + ")"
        }

        JList<String> lista = new JList<>(model);
        painel.add(new JScrollPane(lista), BorderLayout.CENTER);

        return painel;
    }

    /* ================= PC ================= */
    private JPanel criarPainelPC() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        DefaultListModel<String> model = new DefaultListModel<>();
        for (Pokemon p : jogador.getPcBox()) {
            model.addElement(p.getNome());  // Removi para testes: + " (Lv." + p.getNivel() + ")"
        }

        JList<String> lista = new JList<>(model);
        painel.add(new JScrollPane(lista), BorderLayout.CENTER);

        return painel;
    }

    /* ================= BOTOES ================= */
    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new FlowLayout());

        JButton btnSalvar = new JButton("Salvar Jogo");
        btnSalvar.addActionListener(e -> jogador.salvarProgresso());

        JButton btnVoltar = new JButton("Voltar ao Menu");
        btnVoltar.addActionListener(e -> {
            new TelaMenu().setVisible(true);
            this.dispose();
        });

        painel.add(btnSalvar);
        painel.add(btnVoltar);

        return painel;
    }
}
