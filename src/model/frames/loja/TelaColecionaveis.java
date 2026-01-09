package model.frames.loja;

import model.jogador.Jogador;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.net.URL;

public class TelaColecionaveis extends JFrame {

    private Jogador jogador;
    private DefaultTableModel model;
    private JTable tabela;
    private JLabel lblDinheiro;

    private static final String CAMINHO_IMAGENS = "/model/frames/images/LOJA/";

    private String[] itens = {
            "Poké Badge Comum",
            "Medalha Regional",
            "Troféu de Ginásio",
            "Insígnia Elite",
            "Relíquia Antiga",
            "Troféu Kanto",
            "Medalha Lendária",
            "Artefato Místico",
            "Cristal Ancestral",
            "Coroa do Campeão Supremo"
    };

    private int[] precos = {
            200, 350, 500, 800, 1200,
            1500, 2500, 4000, 6000, 10000
    };

    private String[] raridades = {
            "Comum",
            "Comum",
            "Comum",
            "Incomum",
            "Rara",
            "Rara",
            "Muito Rara",
            "Épica",
            "Épica",
            "LENDÁRIA"
    };

    public TelaColecionaveis(Jogador jogador) {
        this.jogador = jogador;

        setTitle("Loja de Colecionáveis");
        setSize(900, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        /* ================= TOPO ================= */
        JLabel lblTitulo = new JLabel("LOJA DE COLECIONÁVEIS");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel topo = new JPanel(new BorderLayout());
        topo.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));
        topo.add(lblTitulo, BorderLayout.CENTER);

        /* ================= TABELA ================= */
        model = new DefaultTableModel(
                new Object[]{"Imagem", "Item", "Preço", "Raridade", "Status"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return ImageIcon.class;
                }
                return String.class;
            }
        };

        // Preenche a tabela e carrega status dos itens salvos
        for (int i = 0; i < itens.length; i++) {
            ImageIcon icon = carregarImagem(i + 1);
            
            // Verifica se o item já foi comprado anteriormente
            String status = jogador.temColecionavel(i) ? "COMPRADO" : "Disponível";
            
            model.addRow(new Object[]{
                    icon,
                    itens[i],
                    "R$ " + precos[i],
                    raridades[i],
                    status
            });
        }

        tabela = new JTable(model);
        tabela.setRowHeight(60);
        tabela.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        // Ajuste da coluna de imagem
        tabela.getColumnModel().getColumn(0).setPreferredWidth(70);
        tabela.getColumnModel().getColumn(0).setMaxWidth(80);

        // Renderer específico para ImageIcon
        tabela.getColumnModel().getColumn(0).setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                
                JLabel label = new JLabel();
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.CENTER);
                
                if (value instanceof ImageIcon) {
                    label.setIcon((ImageIcon) value);
                }
                
                if (isSelected) {
                    label.setBackground(table.getSelectionBackground());
                    label.setOpaque(true);
                } else {
                    label.setBackground(Color.WHITE);
                    label.setOpaque(true);
                }
                
                return label;
            }
        });

        // Centralizar outras colunas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tabela.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        // Renderizador de raridade (cores)
        tabela.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column
                );

                setHorizontalAlignment(SwingConstants.CENTER);

                if (!isSelected) {
                    c.setFont(new Font("SansSerif", Font.PLAIN, 14));
                    c.setBackground(Color.WHITE);

                    switch (raridades[row]) {
                        case "Comum":
                            c.setForeground(new Color(128, 128, 128));
                            break;
                        case "Incomum":
                            c.setForeground(new Color(0, 102, 204));
                            break;
                        case "Rara":
                            c.setForeground(new Color(0, 153, 0));
                            break;
                        case "Muito Rara":
                            c.setForeground(new Color(204, 102, 0));
                            break;
                        case "Épica":
                            c.setForeground(new Color(153, 51, 153));
                            break;
                        case "LENDÁRIA":
                            c.setForeground(new Color(218, 165, 32));
                            c.setFont(c.getFont().deriveFont(Font.BOLD, 15f));
                            break;
                    }
                }

                return c;
            }
        });

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        /* ================= RODAPÉ ================= */
        lblDinheiro = new JLabel(String.format("Dinheiro: R$ %d | Colecionáveis: %d/10", 
                                                jogador.getDinheiro(), 
                                                jogador.quantidadeColecionaveis()));
        lblDinheiro.setFont(new Font("SansSerif", Font.BOLD, 14));

        JButton btnComprar = new JButton("Comprar Item");
        btnComprar.setPreferredSize(new Dimension(160, 32));

        btnComprar.addActionListener(e -> {
            int row = tabela.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um item!");
                return;
            }

            if ("COMPRADO".equals(model.getValueAt(row, 4))) {
                JOptionPane.showMessageDialog(this, "Item já comprado!");
                return;
            }

            if (jogador.gastarDinheiro(precos[row])) {
                // Marca o item como comprado
                model.setValueAt("COMPRADO", row, 4);
                
                // Adiciona o colecionável ao jogador
                jogador.adicionarColecionavel(row);
                
                // Atualiza o label de dinheiro e quantidade
                lblDinheiro.setText(String.format("Dinheiro: R$ %d | Colecionáveis: %d/10", 
                                                   jogador.getDinheiro(), 
                                                   jogador.quantidadeColecionaveis()));

                // Salva o progresso
                boolean salvou = SistemaDeArquivos.salvarJogador(jogador, jogador.getNome());
                
                if (salvou) {
                    JOptionPane.showMessageDialog(
                        this, 
                        "Compra realizada com sucesso!\nProgresso salvo automaticamente."
                    );
                    System.out.println("✓ Colecionável " + row + " salvo com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(
                        this, 
                        "Compra realizada, mas houve erro ao salvar!\nTente salvar manualmente.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                    );
                    System.err.println("✗ Erro ao salvar colecionável " + row);
                }
            } else {
                JOptionPane.showMessageDialog(
                        this, "Dinheiro insuficiente!"
                );
            }
        });

        // Botão Voltar ao Menu
        JButton btnVoltar = new JButton("Voltar ao Menu");
        btnVoltar.setPreferredSize(new Dimension(160, 32));
        
        btnVoltar.addActionListener(e -> {
            // Fecha a tela atual
            dispose();
            
            // Abre a TelaMenu
            new TelaMenu();
        });

        JPanel rodape = new JPanel();
        rodape.setBorder(BorderFactory.createEmptyBorder(10, 20, 15, 20));
        rodape.setLayout(new BoxLayout(rodape, BoxLayout.X_AXIS));

        rodape.add(lblDinheiro);
        rodape.add(Box.createHorizontalGlue());
        rodape.add(btnVoltar);
        rodape.add(Box.createRigidArea(new Dimension(10, 0))); // Espaçamento entre botões
        rodape.add(btnComprar);

        /* ================= MONTAGEM ================= */
        add(topo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(rodape, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Carrega a imagem e redimensiona
     */
    private ImageIcon carregarImagem(int numero) {
        try {
            URL url = getClass().getResource(CAMINHO_IMAGENS + numero + ".png");
            
            if (url != null) {
                ImageIcon icon = new ImageIcon(url);
                
                // Redimensionar a imagem para caber na célula
                Image img = icon.getImage();
                Image imgRedimensionada = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                
                System.out.println("✓ Imagem carregada: " + CAMINHO_IMAGENS + numero + ".png");
                return new ImageIcon(imgRedimensionada);
            } else {
                System.out.println("✗ Imagem não encontrada: " + numero + ".png");
                return criarIconePlaceholder(numero);
            }
        } catch (Exception e) {
            System.out.println("✗ Erro ao carregar imagem " + numero + ": " + e.getMessage());
            return criarIconePlaceholder(numero);
        }
    }

    /**
     * Cria um ícone placeholder quando a imagem não é encontrada
     */
    private ImageIcon criarIconePlaceholder(int numero) {
        int tamanho = 50;
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(
            tamanho, tamanho, java.awt.image.BufferedImage.TYPE_INT_RGB
        );
        
        Graphics2D g2d = img.createGraphics();
        
        Color[] cores = {
            new Color(200, 200, 200),
            new Color(173, 216, 230),
            new Color(144, 238, 144),
            new Color(255, 182, 193)
        };
        g2d.setColor(cores[numero % cores.length]);
        g2d.fillRect(0, 0, tamanho, tamanho);
        
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawRect(0, 0, tamanho - 1, tamanho - 1);
        
        g2d.setFont(new Font("SansSerif", Font.BOLD, 20));
        String texto = String.valueOf(numero);
        FontMetrics fm = g2d.getFontMetrics();
        int x = (tamanho - fm.stringWidth(texto)) / 2;
        int y = ((tamanho - fm.getHeight()) / 2) + fm.getAscent();
        g2d.drawString(texto, x, y);
        
        g2d.dispose();
        
        return new ImageIcon(img);
    }
}