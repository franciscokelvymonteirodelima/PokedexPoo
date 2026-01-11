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
        
        // Fundo vermelho suave
        painelPrincipal.setBackground(new Color(220, 100, 100));
        
        JLabel titulo = new JLabel("Dicionário", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE); // Texto branco para contrastar com o fundo
        painelPrincipal.add(titulo, BorderLayout.NORTH);
        
        Pokedex pokedex = new Pokedex();
        PokedexTableModel model = new PokedexTableModel(pokedex);
        
        tabela = new JTable(model);
        tabela.setRowHeight(40);
        tabela.setFont(new Font("Arial", Font.PLAIN, 14));
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tabela.setAutoCreateRowSorter(true);
        
        // Renderizador para a coluna "Info" (botão menor)
        tabela.getColumn("Info").setCellRenderer(new ButtonRenderer());
        tabela.getColumn("Info").setCellEditor(new ButtonEditor(tabela));
        tabela.getColumn("Info").setPreferredWidth(60); // Largura menor para o botão
        tabela.getColumn("Info").setMaxWidth(80);
        
        // Renderizador para as colunas de tipo com cores
        tabela.getColumn("Tipo 1").setCellRenderer(new TipoRenderer());
        tabela.getColumn("Tipo 2").setCellRenderer(new TipoRenderer());
        
        JScrollPane scrollPane = new JScrollPane(tabela);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);
        
        add(painelPrincipal);
    }
}