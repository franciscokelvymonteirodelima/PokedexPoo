

import model.jogador.Jogador;
import model.pokemon.Pokemon;
import model.pokedex.Pokedex;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaCriacaoPerfil extends JFrame {
    private Pokedex pokedex;
    private Jogador jogadorCriado;
    
    // Componentes do formulário
    private JTextField txtNome;
    private JSpinner spnIdade;
    private JComboBox<String> cmbGenero;
    private JTextField txtCidade;
    
    // Lista de Pokémons selecionados
    private DefaultListModel<String> modelDisponiveis;
    private DefaultListModel<String> modelSelecionados;
    private JList<String> listDisponiveis;
    private JList<String> listSelecionados;
    
    private List<Pokemon> pokemonsSelecionados;
    private List<Pokemon> pokemonsDisponiveis;

    public TelaCriacaoPerfil() {
        this.pokedex = new Pokedex();
        this.pokemonsSelecionados = new ArrayList<>();
        this.pokemonsDisponiveis = new ArrayList<>(pokedex.listarTodos());
        
        configurarJanela();
        inicializarComponentes();
    }

    private void configurarJanela() {
        setTitle("Criar Novo Perfil de Treinador");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
    }

    private void inicializarComponentes() {
        // Painel principal com margem
        JPanel painelPrincipal = new JPanel(new BorderLayout(15, 15));
        painelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Título
        JLabel titulo = new JLabel("Criar Perfil de Treinador Pokemon", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBorder(new EmptyBorder(0, 0, 20, 0));
        painelPrincipal.add(titulo, BorderLayout.NORTH);
        
        // Painel central com formulário e seleção de Pokémons
        JPanel painelCentral = new JPanel(new GridLayout(1, 2, 15, 0));
        painelCentral.add(criarPainelFormulario());
        painelCentral.add(criarPainelSelecaoPokemon());
        painelPrincipal.add(painelCentral, BorderLayout.CENTER);
        
        // Painel de botões
        painelPrincipal.add(criarPainelBotoes(), BorderLayout.SOUTH);
        
        add(painelPrincipal);
    }

    /* ================= FORMULÁRIO ================= */
    private JPanel criarPainelFormulario() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createTitledBorder("Informações Pessoais"));
        
        // Nome
        painel.add(criarCampo("Nome do Treinador:", txtNome = new JTextField(20)));
        painel.add(Box.createVerticalStrut(15));
        
        // Idade
        SpinnerNumberModel modelIdade = new SpinnerNumberModel(10, 5, 99, 1);
        spnIdade = new JSpinner(modelIdade);
        painel.add(criarCampo("Idade:", spnIdade));
        painel.add(Box.createVerticalStrut(15));
        
        // Gênero
        String[] generos = {"Masculino", "Feminino", "Outro", "Não informado"};
        cmbGenero = new JComboBox<>(generos);
        painel.add(criarCampo("Gênero:", cmbGenero));
        painel.add(Box.createVerticalStrut(15));
        
        // Cidade
        painel.add(criarCampo("Cidade de Origem:", txtCidade = new JTextField("Pallet Town", 20)));
        painel.add(Box.createVerticalStrut(20));
        
        // Informações
        JTextArea txtInfo = new JTextArea(
            "Dicas:\n\n" +
            "- Escolha um nome unico para seu treinador\n" +
            "- Voce comeca com $3000\n" +
            "- Selecione ate 6 Pokemons para seu time inicial\n" +
            "- Pokemons adicionais irao para o PC\n" +
            "- Voce pode editar seu perfil depois"
        );
        txtInfo.setEditable(false);
        txtInfo.setLineWrap(true);
        txtInfo.setWrapStyleWord(true);
        txtInfo.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollInfo = new JScrollPane(txtInfo);
        scrollInfo.setMaximumSize(new Dimension(400, 200));
        painel.add(scrollInfo);
        
        return painel;
    }

    private JPanel criarCampo(String rotulo, JComponent componente) {
        JPanel painel = new JPanel(new BorderLayout(5, 5));
        painel.setMaximumSize(new Dimension(400, 60));
        
        JLabel lbl = new JLabel(rotulo);
        painel.add(lbl, BorderLayout.NORTH);
        painel.add(componente, BorderLayout.CENTER);
        
        return painel;
    }

    /* ================= SELEÇÃO DE POKÉMONS ================= */
    private JPanel criarPainelSelecaoPokemon() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createTitledBorder("Selecionar Pokemons"));
        
        // Painel superior com contador
        JLabel lblContador = new JLabel("Pokemons selecionados: 0/6 (Time)", SwingConstants.CENTER);
        painel.add(lblContador, BorderLayout.NORTH);
        
        // Painel central com as listas
        JPanel painelListas = new JPanel(new GridLayout(1, 2, 10, 0));
        
        // Lista de Pokémons disponíveis
        modelDisponiveis = new DefaultListModel<>();
        carregarPokemonsDisponiveis();
        listDisponiveis = new JList<>(modelDisponiveis);
        listDisponiveis.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        JPanel painelEsquerda = new JPanel(new BorderLayout());
        painelEsquerda.add(new JLabel("Pokédex (Disponíveis):", SwingConstants.CENTER), BorderLayout.NORTH);
        painelEsquerda.add(new JScrollPane(listDisponiveis), BorderLayout.CENTER);
        
        // Campo de busca
        JTextField txtBusca = new JTextField();
        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtrarPokemonsDisponiveis(txtBusca.getText());
            }
        });
        JPanel painelBusca = new JPanel(new BorderLayout(5, 5));
        painelBusca.add(new JLabel("Buscar:"), BorderLayout.WEST);
        painelBusca.add(txtBusca, BorderLayout.CENTER);
        painelEsquerda.add(painelBusca, BorderLayout.SOUTH);
        
        // Lista de Pokémons selecionados
        modelSelecionados = new DefaultListModel<>();
        listSelecionados = new JList<>(modelSelecionados);
        listSelecionados.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        JPanel painelDireita = new JPanel(new BorderLayout());
        painelDireita.add(new JLabel("Meus Pokémons:", SwingConstants.CENTER), BorderLayout.NORTH);
        painelDireita.add(new JScrollPane(listSelecionados), BorderLayout.CENTER);
        
        painelListas.add(painelEsquerda);
        painelListas.add(painelDireita);
        painel.add(painelListas, BorderLayout.CENTER);
        
        // Botões de ação
        JPanel painelBotoesAcao = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(e -> {
            adicionarPokemonsSelecionados();
            lblContador.setText(String.format("Pokemons selecionados: %d/6 (Time) + %d (PC)", 
                Math.min(pokemonsSelecionados.size(), 6),
                Math.max(0, pokemonsSelecionados.size() - 6)));
        });
        
        JButton btnRemover = new JButton("Remover");
        btnRemover.addActionListener(e -> {
            removerPokemonsSelecionados();
            lblContador.setText(String.format("Pokemons selecionados: %d/6 (Time) + %d (PC)", 
                Math.min(pokemonsSelecionados.size(), 6),
                Math.max(0, pokemonsSelecionados.size() - 6)));
        });
        
        JButton btnLimpar = new JButton("Limpar Tudo");
        btnLimpar.addActionListener(e -> {
            limparSelecao();
            lblContador.setText("Pokemons selecionados: 0/6 (Time)");
        });
        
        painelBotoesAcao.add(btnAdicionar);
        painelBotoesAcao.add(btnRemover);
        painelBotoesAcao.add(btnLimpar);
        painel.add(painelBotoesAcao, BorderLayout.SOUTH);
        
        return painel;
    }

    private void carregarPokemonsDisponiveis() {
        modelDisponiveis.clear();
        for (Pokemon p : pokemonsDisponiveis) {
            modelDisponiveis.addElement(formatarPokemon(p));
        }
    }

    private void filtrarPokemonsDisponiveis(String filtro) {
        modelDisponiveis.clear();
        String filtroLower = filtro.toLowerCase().trim();
        
        for (Pokemon p : pokemonsDisponiveis) {
            if (filtroLower.isEmpty() || 
                p.getNome().toLowerCase().contains(filtroLower) ||
                p.getTipo1().toLowerCase().contains(filtroLower) ||
                (p.getTipo2() != null && p.getTipo2().toLowerCase().contains(filtroLower))) {
                modelDisponiveis.addElement(formatarPokemon(p));
            }
        }
    }

    private String formatarPokemon(Pokemon p) {
        String tipo2 = p.getTipo2() != null && !p.getTipo2().equals("-") ? "/" + p.getTipo2() : "";
        return String.format("#%03d %s (%s%s)", 
            p.getNumeroPokedex(), p.getNome(), p.getTipo1(), tipo2);
    }

    private void adicionarPokemonsSelecionados() {
        List<String> selecionados = listDisponiveis.getSelectedValuesList();
        
        for (String pokemonStr : selecionados) {
            // Extrair número da Pokédex do formato "#001 Nome (Tipo)"
            String numStr = pokemonStr.substring(1, 4);
            int num = Integer.parseInt(numStr);
            
            // Encontrar o Pokémon correspondente
            Pokemon pokemon = pokemonsDisponiveis.stream()
                .filter(p -> p.getNumeroPokedex() == num)
                .findFirst()
                .orElse(null);
            
            if (pokemon != null && !pokemonsSelecionados.contains(pokemon)) {
                pokemonsSelecionados.add(pokemon);
                modelSelecionados.addElement(pokemonStr);
            }
        }
    }

    private void removerPokemonsSelecionados() {
        List<String> selecionados = listSelecionados.getSelectedValuesList();
        
        for (String pokemonStr : selecionados) {
            String numStr = pokemonStr.substring(1, 4);
            int num = Integer.parseInt(numStr);
            
            Pokemon pokemon = pokemonsSelecionados.stream()
                .filter(p -> p.getNumeroPokedex() == num)
                .findFirst()
                .orElse(null);
            
            if (pokemon != null) {
                pokemonsSelecionados.remove(pokemon);
                modelSelecionados.removeElement(pokemonStr);
            }
        }
    }

    private void limparSelecao() {
        pokemonsSelecionados.clear();
        modelSelecionados.clear();
    }

    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        JButton btnCriar = new JButton("Criar Perfil");
        btnCriar.setPreferredSize(new Dimension(150, 40));
        btnCriar.addActionListener(e -> criarPerfil());
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setPreferredSize(new Dimension(150, 40));
        btnCancelar.addActionListener(e -> dispose());
        
        painel.add(btnCriar);
        painel.add(btnCancelar);
        
        return painel;
    }

    /* ================= CRIAR PERFIL ================= */
    private void criarPerfil() {
        // Validações
        String nome = txtNome.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, insira um nome para o treinador!", 
                "Nome Obrigatório", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (pokemonsSelecionados.isEmpty()) {
            int resposta = JOptionPane.showConfirmDialog(this,
                "Você não selecionou nenhum Pokémon. Deseja continuar mesmo assim?",
                "Sem Pokémons",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            
            if (resposta != JOptionPane.YES_OPTION) {
                return;
            }
        }
        
        // Criar jogador
        int idade = (Integer) spnIdade.getValue();
        String genero = (String) cmbGenero.getSelectedItem();
        String cidade = txtCidade.getText().trim();
        
        jogadorCriado = new Jogador(nome, idade, genero, cidade);
        
        // Adicionar Pokémons
        for (Pokemon pokemon : pokemonsSelecionados) {
            // Criar uma cópia do Pokémon para o jogador
            Pokemon pokemonCopia = new Pokemon(
                pokemon.getNome(),
                pokemon.getNumeroPokedex(),
                pokemon.getTipo1(),
                pokemon.getTipo2(),
                pokemon.getSomCaracteristico(),
                pokemon.getHp(),
                pokemon.getAtaque(),
                pokemon.getDefesa(),
                pokemon.getSpAtaque(),
                pokemon.getSpDefesa(),
                pokemon.getVelocidade(),
                pokemon.getDescricao(),
                pokemon.getHabilidade()
            );
            jogadorCriado.capturarPokemon(pokemonCopia);
        }
        
        // Mensagem de sucesso
        String mensagem = String.format(
            "Perfil criado com sucesso!\n\n" +
            "Treinador: %s\n" +
            "Idade: %d anos\n" +
            "Cidade: %s\n" +
            "Dinheiro: $%d\n" +
            "Pokemons no Time: %d\n" +
            "Pokemons no PC: %d",
            nome, idade, cidade, jogadorCriado.getDinheiro(),
            jogadorCriado.getTimePokemon().size(),
            jogadorCriado.getPcBox().size()
        );
        
        JOptionPane.showMessageDialog(this, 
            mensagem, 
            "Perfil Criado!", 
            JOptionPane.INFORMATION_MESSAGE);
        
        // Abrir tela do perfil
        abrirTelaPerfil();
    }

    private void abrirTelaPerfil() {
        TelaJogador telaJogador = new TelaJogador(jogadorCriado);
        telaJogador.setVisible(true);
        dispose();
    }

    public Jogador getJogadorCriado() {
        return jogadorCriado;
    }

    /* ================= MAIN PARA TESTE ================= */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            TelaCriacaoPerfil tela = new TelaCriacaoPerfil();
            tela.setVisible(true);
        });
    }
}