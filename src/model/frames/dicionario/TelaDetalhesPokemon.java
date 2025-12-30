package model.frames.dicionario;

import model.pokemon.Pokemon;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class TelaDetalhesPokemon extends JDialog {
    public TelaDetalhesPokemon(JFrame parent, Pokemon pokemon) {
        super(parent, "Detalhes do Pokémon", true);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));
        
        // Painel superior com imagem E informações LADO A LADO
        JPanel painelSuperior = new JPanel(new BorderLayout(15, 10));
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        
        // ===== PAINEL DA IMAGEM (ESQUERDA) =====
        JPanel painelImagem = new JPanel();
        painelImagem.setLayout(new BorderLayout());
        painelImagem.setPreferredSize(new Dimension(150, 150));
        painelImagem.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        
        JLabel labelImagem = carregarImagem(pokemon);
        painelImagem.add(labelImagem, BorderLayout.CENTER);
        
        // ===== PAINEL DE INFORMAÇÕES (DIREITA) =====
        JPanel painelInfo = new JPanel();
        painelInfo.setLayout(new GridLayout(4, 1, 5, 10));
        painelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Labels com fonte maior e melhor formatação
        Font fonteInfo = new Font("Arial", Font.PLAIN, 14);
        
        JLabel labelNome = new JLabel("Nome: " + pokemon.getNome());
        labelNome.setFont(fonteInfo);
        
        JLabel labelNumero = new JLabel("Número: " + pokemon.getNumeroPokedex());
        labelNumero.setFont(fonteInfo);
        
        JLabel labelTipo1 = new JLabel("Tipo 1: " + pokemon.getTipo1());
        labelTipo1.setFont(fonteInfo);
        
        JLabel labelTipo2 = new JLabel("Tipo 2: " + pokemon.getTipo2());
        labelTipo2.setFont(fonteInfo);
        
        painelInfo.add(labelNome);
        painelInfo.add(labelNumero);
        painelInfo.add(labelTipo1);
        painelInfo.add(labelTipo2);
        
        // Adiciona imagem à ESQUERDA e info à DIREITA
        painelSuperior.add(painelImagem, BorderLayout.WEST);
        painelSuperior.add(painelInfo, BorderLayout.CENTER);
        
        // ===== PAINEL DO GRÁFICO =====
        PainelGraficoStatus grafico = new PainelGraficoStatus(pokemon);
        
        // ===== BOTÃO FECHAR =====
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        JPanel painelBotao = new JPanel();
        painelBotao.add(btnFechar);
        
        // Adiciona tudo
        add(painelSuperior, BorderLayout.NORTH);
        add(grafico, BorderLayout.CENTER);
        add(painelBotao, BorderLayout.SOUTH);
        
        pack();
        setMinimumSize(new Dimension(520, 580));
        setLocationRelativeTo(parent);
    }
    
    private JLabel carregarImagem(Pokemon pokemon) {
        try {
            // Tenta primeiro com resources (caminho relativo ao classpath)
            String caminhoResource = "/model/frames/images/GEN 1/" + pokemon.getNumeroPokedex() + ".png";
            java.net.URL imgUrl = getClass().getResource(caminhoResource);
            
            if (imgUrl != null) {
                ImageIcon icon = new ImageIcon(imgUrl);
                Image img = icon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
                JLabel label = new JLabel(new ImageIcon(img));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
            
            // Se não encontrar, tenta caminho absoluto
            String caminhoAbsoluto = "src/model/frames/images/GEN 1/" + pokemon.getNumeroPokedex() + ".png";
            File arquivo = new File(caminhoAbsoluto);
            
            if (arquivo.exists()) {
                ImageIcon icon = new ImageIcon(caminhoAbsoluto);
                Image img = icon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
                JLabel label = new JLabel(new ImageIcon(img));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
            
            System.err.println("Imagem não encontrada: " + caminhoAbsoluto);
            return criarPlaceholder();
            
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem: " + e.getMessage());
            e.printStackTrace();
            return criarPlaceholder();
        }
    }
    
    private JLabel criarPlaceholder() {
        // Placeholder caso a imagem não seja encontrada
        JLabel placeholder = new JLabel("?");
        placeholder.setFont(new Font("Arial", Font.BOLD, 60));
        placeholder.setForeground(Color.GRAY);
        placeholder.setHorizontalAlignment(SwingConstants.CENTER);
        placeholder.setPreferredSize(new Dimension(140, 140));
        return placeholder;
    }
}