package model.frames.qualEhEssePokemon;

import javax.swing.*;

import model.pokemon.Pokemon;

import java.awt.*;

public class FWhosThatPoke extends JFrame {
    private GamePokemon game;
    private JTextArea campoTexto;;
    private JLabel score;
    private PokemonAleatorioPanel painelPokemonAleatorio;
    private JButton[] botoesRodadas;
    private JButton btnEnviar;
    private int rodadaAtual = -1;

    public FWhosThatPoke() {
        setTitle("Who's that Pokemon");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        game = new GamePokemon();
        
        // ===== Imagem do Fundo Principal =====
        ImageIcon fundoPrincipal = new ImageIcon(getClass().getResource("/model/frames/images/FundosSimbolos/FUNDO_PRINCIPAL_MINIGAME.jpg"));
        JLabel background = new JLabel(fundoPrincipal);
        background.setBounds(0, 0, 1280, 720);
        setContentPane(background);
        background.setLayout(null);

        // ===== Título =====
        JLabel titulo = new JLabel("Who's that Pokemon", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(0, 10, 1280, 40);
        background.add(titulo);

        // ===== Campo para Inserir o Pokemon =====
        campoTexto = new JTextArea();
        campoTexto.setLayout(new BorderLayout());
        campoTexto.setFont(new Font("Arial", Font.PLAIN, 40));
        campoTexto.setLineWrap(true);
        campoTexto.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        campoTexto.setBounds(200, 60, 600, 50);
        background.add(campoTexto);

        // ====== Botão Enviar =====
        btnEnviar = new JButton("Send");
        btnEnviar.setBounds(810, 60, 100, 50);
        btnEnviar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEnviar.addActionListener(e -> verificarRespostas());
        background.add(btnEnviar);

        // ===== Score =====
        score = new JLabel("Score : 0 / " + game.getTotal(), SwingConstants.CENTER);
        score.setFont(new Font("Arial", Font.BOLD, 24));
        score.setBounds(900, 70, 200, 30);
        background.add(score);

        // ===== Imagem do Fundo Secundário =====
        ImageIcon imagemPokemon = new ImageIcon(getClass().getResource("/model/frames/images/FundosSimbolos/FUNDO_MINIGAME.jpg"));
        Image img = imagemPokemon.getImage().getScaledInstance(880, 350, Image.SCALE_SMOOTH);
        imagemPokemon = new ImageIcon(img);

        // ===== Painel do Pokemon =====
        JLabel painelPokemon = new JLabel(imagemPokemon);  
        painelPokemon.setLayout(null);
        painelPokemon.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
        painelPokemon.setBounds(200, 140, 880, 350);
        background.add(painelPokemon);

        // ===== Pokemon Aleatório =====
        painelPokemonAleatorio = new PokemonAleatorioPanel();
        painelPokemonAleatorio.setBounds(275, 40, 350, 350);
        painelPokemon.add(painelPokemonAleatorio);

        // ===== BOTÕES NUMERADOS (1 a 10) =====
        int xInicial = 155;
        int y = 540;
        int largura = 80;
        int altura = 80;
        int espaco = 10;
        botoesRodadas = new JButton[11];

        for (int i = 1; i <= 10; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.setBounds(xInicial + (i - 1) * (largura + espaco), y, largura, altura);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            final int rodada = i - 1;
            btn.addActionListener(e -> selecionarRodada(rodada));
            botoesRodadas[i - 1] = btn;
            background.add(btn);
        }

        // ===== Botão END =====
        JButton btnEnd = new JButton("END");
        btnEnd.setBounds(xInicial + 10 * (largura + espaco), y, largura, altura);
        btnEnd.setFont(new Font("Arial", Font.BOLD, 20));
        btnEnd.setBackground(Color.BLACK);
        btnEnd.setForeground(Color.WHITE);
        btnEnd.addActionListener(e -> finalizarJogo());
        botoesRodadas[10] = btnEnd;
        background.add(btnEnd);

        selecionarRodada(0);
    }

    private void selecionarRodada(int index) {
        if(index < 0 || index >= game.getTotal()){
            return;
        }
        rodadaAtual = index;
        Pokemon pokemon = game.selecionarRodada(index);
        if(pokemon == null){
            JOptionPane.showMessageDialog(this, "Erro: Não foi possível carregar o Pokémon da rodada " + (index + 1));
            return;
        }
        painelPokemonAleatorio.atualizarPokemon(pokemon);
        
        boolean jaRespondida = game.rodadaJaRespondida(index);
        if(jaRespondida){
            campoTexto.setText("Rodada já respondida!");
            campoTexto.setEditable(false);
            btnEnviar.setEnabled(false);
        } else{
            campoTexto.setText("");
            campoTexto.setEnabled(true);
            campoTexto.setEditable(true);
            btnEnviar.setEnabled(true);
            campoTexto.requestFocus();
        }
    }

    private void verificarRespostas(){
        if(rodadaAtual == -1){
            JOptionPane.showMessageDialog(this, "Selecione uma rodada primeiro!");
            return;
        }

        if(game.rodadaJaRespondida(rodadaAtual)){
            JOptionPane.showMessageDialog(this, "Você já respondeu essa rodada!");
            return;
        }

        String resposta = campoTexto.getText().trim();
        if(resposta.isEmpty()){
            JOptionPane.showMessageDialog(this, "Por favor, insira o nome de Pokémon.");
            return;
        }

        boolean correta = game.verificarResposta(resposta);
        Pokemon pokemonAtual = game.getPokemonAtual();
        if(pokemonAtual == null){
            JOptionPane.showMessageDialog(this, "Erro ao obter o Pokémon atual.");
            return;
        }

        if(correta){
            game.setAcertos();
            JOptionPane.showMessageDialog(this, "Correto! Ele é realmente o " + pokemonAtual.getNome() + "!");
        } else{
            JOptionPane.showMessageDialog(this, "Errado! O Pokémon é o " + pokemonAtual.getNome() + "!");
        }

        atualizarScore();

        campoTexto.setEnabled(false);
        btnEnviar.setEnabled(false);
        campoTexto.setText("Rodada já respondida!");

        if(rodadaAtual >=0 && rodadaAtual < botoesRodadas.length - 1){
            botoesRodadas[rodadaAtual].setEnabled(false);
        }
    }

    private void atualizarScore(){
        int acertos = game.getAcertos();
        int total = game.getTotal();
        score.setText("Score : " + acertos + " / " + total);
    }

    private void finalizarJogo(){
        int acertos = game.getAcertos();
        int total = game.getTotal();
        String mensagem = String.format("Jogo finalizado!\nSeu score final %d de %d!\nPorcentagem de acerto: %.1f%%", acertos, total, (acertos * 100.0) / total);
        JOptionPane.showMessageDialog(this, mensagem);
        dispose();
    }

    public static void main(String[] args) {
        FWhosThatPoke frame = new FWhosThatPoke();
        frame.setVisible(true);
    }
}
