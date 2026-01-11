package model.frames.jogador;

import model.jogador.Jogador;
import model.pokemon.Pokemon;
import javax.swing.*;
import java.awt.*;

public class TelaGerenciarTime extends JDialog {
    
    // Cor vermelho suave padrão
    private static final Color VERMELHO_SUAVE = new Color(220, 100, 100);
    
    private Jogador jogador;
    private DefaultListModel<Pokemon> modelPC;
    private DefaultListModel<Pokemon> modelTime;
    private JList<Pokemon> listaPC;
    private JList<Pokemon> listaTime;
    
    public TelaGerenciarTime(Jogador jogador, JFrame pai) {
        super(pai, "Gerenciar Time", true);
        this.jogador = jogador;
        setSize(600, 400);
        setLocationRelativeTo(pai);
        setLayout(new BorderLayout());
        
        // Fundo vermelho suave
        getContentPane().setBackground(VERMELHO_SUAVE);
        
        // Configuração das Listas
        modelPC = new DefaultListModel<>();
        jogador.getPcBox().forEach(modelPC::addElement);
        listaPC = new JList<>(modelPC);
        
        modelTime = new DefaultListModel<>();
        jogador.getTimePokemon().forEach(modelTime::addElement);
        listaTime = new JList<>(modelTime);
        
        // Painel Central com as listas e botões
        JPanel painelCentral = new JPanel(new GridLayout(1, 3, 10, 10));
        painelCentral.setBackground(VERMELHO_SUAVE);
        painelCentral.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        painelCentral.add(criarPainelLista("PC Box", listaPC));
        painelCentral.add(criarPainelBotoesTroca());
        painelCentral.add(criarPainelLista("Meu Time (Máx 6)", listaTime));
        
        add(painelCentral, BorderLayout.CENTER);
        
        // Botão fechar
        JPanel painelBotao = new JPanel();
        painelBotao.setBackground(VERMELHO_SUAVE);
        painelBotao.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        JButton btnFechar = new JButton("Finalizar");
        btnFechar.setPreferredSize(new Dimension(120, 35));
        btnFechar.addActionListener(e -> dispose());
        painelBotao.add(btnFechar);
        
        add(painelBotao, BorderLayout.SOUTH);
    }
    
    private JPanel criarPainelLista(String titulo, JList<Pokemon> lista) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE, 2),
            titulo,
            0,
            0,
            new Font("Arial", Font.BOLD, 13),
            Color.WHITE
        ));
        p.setBackground(VERMELHO_SUAVE);
        
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBackground(Color.WHITE);
        p.add(scroll, BorderLayout.CENTER);
        
        return p;
    }
    
    private JPanel criarPainelBotoesTroca() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(VERMELHO_SUAVE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        
        JButton btnParaTime = new JButton(">>");
        JButton btnParaPC = new JButton("<<");
        
        // Estilizar botões
        btnParaTime.setPreferredSize(new Dimension(60, 35));
        btnParaPC.setPreferredSize(new Dimension(60, 35));
        
        btnParaTime.addActionListener(e -> moverParaTime());
        btnParaPC.addActionListener(e -> moverParaPC());
        
        p.add(btnParaTime, gbc);
        gbc.gridy = 1;
        p.add(btnParaPC, gbc);
        
        return p;
    }
    
    private void moverParaTime() {
        Pokemon selecionado = listaPC.getSelectedValue();
        if (selecionado != null) {
            if (jogador.getTimePokemon().size() < 6) {
                jogador.getPcBox().remove(selecionado);
                jogador.getTimePokemon().add(selecionado);
                atualizarModels();
            } else {
                JOptionPane.showMessageDialog(this, "O time já está cheio!");
            }
        }
    }
    
    private void moverParaPC() {
        Pokemon selecionado = listaTime.getSelectedValue();
        if (selecionado != null) {
            if (jogador.getTimePokemon().size() > 1) {
                jogador.getTimePokemon().remove(selecionado);
                jogador.getPcBox().add(selecionado);
                atualizarModels();
            } else {
                JOptionPane.showMessageDialog(this, "Você precisa de pelo menos 1 Pokémon no time!");
            }
        }
    }
    
    private void atualizarModels() {
        modelPC.clear();
        jogador.getPcBox().forEach(modelPC::addElement);
        modelTime.clear();
        jogador.getTimePokemon().forEach(modelTime::addElement);
    }
}