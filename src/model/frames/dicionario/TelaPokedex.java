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

        // ===== CORES PADRÃO DOS BOTÕES =====
        Color vermelhoFundo = new Color(220, 100, 100);

        UIManager.put("Button.background", Color.WHITE);
        UIManager.put("Button.foreground", vermelhoFundo);
        UIManager.put("Button.font", new Font("Arial", Font.BOLD, 12));
        UIManager.put("Button.focusPainted", false);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Fundo vermelho suave
        painelPrincipal.setBackground(vermelhoFundo);

        JLabel titulo = new JLabel("Dicionário", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        Pokedex pokedex = new Pokedex();
        PokedexTableModel model = new PokedexTableModel(pokedex);

        tabela = new JTable(model);
        tabela.setRowHeight(40);
        tabela.setFont(new Font("Arial", Font.PLAIN, 14));
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tabela.setAutoCreateRowSorter(true);

        // Botão "Info"
        tabela.getColumn("Info").setCellRenderer(new ButtonRenderer());
        tabela.getColumn("Info").setCellEditor(new ButtonEditor(tabela));
        tabela.getColumn("Info").setPreferredWidth(60);
        tabela.getColumn("Info").setMaxWidth(80);

        // Tipos com cores
        tabela.getColumn("Tipo 1").setCellRenderer(new TipoRenderer());
        tabela.getColumn("Tipo 2").setCellRenderer(new TipoRenderer());

        JScrollPane scrollPane = new JScrollPane(tabela);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);

        add(painelPrincipal);
    }
}
