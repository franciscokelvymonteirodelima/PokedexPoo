package model.frames.jogador;

import model.jogador.Jogador;
import model.pokemon.Pokemon;

import javax.swing.*;
import java.awt.*;

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
    }

    /* ================= PERFIL ================= */
    private JPanel criarPainelPerfil() {
        JPanel painel = new JPanel(new GridLayout(2, 4, 10, 10));
        painel.setBorder(BorderFactory.createTitledBorder("Perfil"));

        painel.add(new JLabel("Nome: " + jogador.getNome()));
        painel.add(new JLabel("Idade: " + jogador.getIdade()));
        painel.add(new JLabel("Gênero: " + jogador.getGenero()));
        painel.add(new JLabel("Cidade: " + jogador.getCidadeOrigem()));
        painel.add(new JLabel("Dinheiro: $" + jogador.getDinheiro()));
        painel.add(new JLabel("Capturados: " + jogador.getPokemonsCapturados()));

        return painel;
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
            model.addElement(p.getNome() + " (Lv." + p.getNivel() + ")");
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
            model.addElement(p.getNome() + " (Lv." + p.getNivel() + ")");
        }

        JList<String> lista = new JList<>(model);
        painel.add(new JScrollPane(lista), BorderLayout.CENTER);

        return painel;
    }
}
