package model.frames.dicionario;

import model.pokedex.Pokedex;

import javax.swing.*;
import java.awt.*;

public class TelaPokedex extends JFrame {

    private JTable tabela;

    public TelaPokedex() {
        setTitle("Dicionário Pokémon");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inicializarTela();
    }

    private void inicializarTela() {
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Dicionário", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        Pokedex pokedex = new Pokedex();
        PokedexTableModel model = new PokedexTableModel(pokedex);
        tabela = new JTable(model);

        tabela.setRowHeight(40);
        tabela.setFont(new Font("Arial", Font.PLAIN, 14));
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tabela.setAutoCreateRowSorter(true);

        tabela.getColumn("Info").setCellRenderer(new ButtonRenderer());
        tabela.getColumn("Info").setCellEditor(new ButtonEditor(tabela));

        JScrollPane scrollPane = new JScrollPane(tabela);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);

        add(painelPrincipal);
    }
}
