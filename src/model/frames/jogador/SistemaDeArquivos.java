
package model.frames.jogador;

import model.jogador.Jogador;
import model.pokemon.Pokemon;
import model.pokedex.Pokedex;
import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaDeArquivos {
    
    private static final String PASTA_SAVES = "saves";
    private static Pokedex pokedex = new Pokedex(); // Para buscar Pokémons pelo número
    
    // Inicializar pasta de saves
    static {
        try {
            Files.createDirectories(Paths.get(PASTA_SAVES));
            System.out.println("Pasta saves criada ou já existe: " + PASTA_SAVES);
        } catch (IOException e) {
            System.err.println("Erro ao criar pasta de saves: " + e.getMessage());
        }
    }
    
    /**
     * Salva o jogador em um arquivo de texto simples
     * @param jogador O jogador a ser salvo
     * @param nomeArquivo Nome do arquivo (sem extensão)
     * @return true se salvou com sucesso
     */
    public static boolean salvarJogador(Jogador jogador, String nomeArquivo) {
        String caminhoCompleto = PASTA_SAVES + File.separator + nomeArquivo + ".txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoCompleto))) {
            
            // Linha 1: Informações básicas do jogador
            writer.write("[JOGADOR]");
            writer.newLine();
            writer.write("Nome=" + jogador.getNome());
            writer.newLine();
            writer.write("Idade=" + jogador.getIdade());
            writer.newLine();
            writer.write("Genero=" + jogador.getGenero());
            writer.newLine();
            writer.write("Cidade=" + jogador.getCidadeOrigem());
            writer.newLine();
            writer.write("Dinheiro=" + jogador.getDinheiro());
            writer.newLine();
            writer.write("PokemonsCapturados=" + jogador.getPokemonsCapturados());
            writer.newLine();
            
            // Salvar Time Pokémon
            writer.write("[TIME_POKEMON]");
            writer.newLine();
            for (Pokemon p : jogador.getTimePokemon()) {
                writer.write(p.getNumeroPokedex() + "," + p.getNivel());
                writer.newLine();
            }
            
            // Salvar PC Box
            writer.write("[PC_BOX]");
            writer.newLine();
            for (Pokemon p : jogador.getPcBox()) {
                writer.write(p.getNumeroPokedex() + "," + p.getNivel());
                writer.newLine();
            }
            
            System.out.println("✅ Jogo salvo em: " + caminhoCompleto);
            return true;
            
        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Carrega um jogador de um arquivo de texto
     * @param caminhoArquivo Caminho completo do arquivo
     * @return O jogador carregado ou null se houver erro
     */
    public static Jogador carregarJogador(String caminhoArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            
            String linha;
            Jogador jogador = null;
            String secaoAtual = "";
            
            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();
                
                // Ignorar linhas vazias
                if (linha.isEmpty()) {
                    continue;
                }
                
                // Identificar seção
                if (linha.startsWith("[") && linha.endsWith("]")) {
                    secaoAtual = linha;
                    continue;
                }
                
                // Processar dados do jogador
                if (secaoAtual.equals("[JOGADOR]")) {
                    String[] partes = linha.split("=", 2);
                    if (partes.length == 2) {
                        String chave = partes[0];
                        String valor = partes[1];
                        
                        switch (chave) {
                            case "Nome":
                                // Criar o jogador com os dados básicos (vamos completar depois)
                                jogador = new Jogador(valor);
                                break;
                            case "Idade":
                                if (jogador != null) jogador.setIdade(Integer.parseInt(valor));
                                break;
                            case "Genero":
                                if (jogador != null) jogador.setGenero(valor);
                                break;
                            case "Cidade":
                                if (jogador != null) jogador.setCidadeOrigem(valor);
                                break;
                            case "Dinheiro":
                                if (jogador != null) {
                                    // Zerar e adicionar o valor correto
                                    int dinheiro = Integer.parseInt(valor);
                                    jogador.gastarDinheiro(jogador.getDinheiro()); // Zera
                                    jogador.ganharDinheiro(dinheiro); // Adiciona o valor salvo
                                }
                                break;
                        }
                    }
                }
                
                // Processar Time Pokémon
                else if (secaoAtual.equals("[TIME_POKEMON]")) {
                    String[] partes = linha.split(",");
                    if (partes.length == 2 && jogador != null) {
                        int numeroPokedex = Integer.parseInt(partes[0]);
                        int nivel = Integer.parseInt(partes[1]);
                        
                        // Buscar o Pokémon base na Pokédex
                        Pokemon pokemonBase = buscarPokemonNaPokedex(numeroPokedex);
                        if (pokemonBase != null) {
                            // Criar uma cópia do Pokémon
                            Pokemon pokemon = criarCopiaPokemon(pokemonBase, nivel);
                            
                            // Adicionar diretamente ao time (evita incrementar contador)
                            jogador.getTimePokemon().add(pokemon);
                        }
                    }
                }
                
                // Processar PC Box
                else if (secaoAtual.equals("[PC_BOX]")) {
                    String[] partes = linha.split(",");
                    if (partes.length == 2 && jogador != null) {
                        int numeroPokedex = Integer.parseInt(partes[0]);
                        int nivel = Integer.parseInt(partes[1]);
                        
                        Pokemon pokemonBase = buscarPokemonNaPokedex(numeroPokedex);
                        if (pokemonBase != null) {
                            Pokemon pokemon = criarCopiaPokemon(pokemonBase, nivel);
                            jogador.getPcBox().add(pokemon);
                        }
                    }
                }
            }
            
            if (jogador != null) {
                System.out.println("✅ Jogo carregado: " + jogador.getNome());
            }
            return jogador;
            
        } catch (IOException | NumberFormatException e) {
            System.err.println("❌ Erro ao carregar: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Busca um Pokémon na Pokédex pelo número
     */
    private static Pokemon buscarPokemonNaPokedex(int numeroPokedex) {
        for (Pokemon p : pokedex.listarTodos()) {
            if (p.getNumeroPokedex() == numeroPokedex) {
                return p;
            }
        }
        return null;
    }
    
    /**
     * Cria uma cópia de um Pokémon com nível específico
     */
    private static Pokemon criarCopiaPokemon(Pokemon original, int nivel) {
        Pokemon copia = new Pokemon(
            original.getNome(),
            original.getNumeroPokedex(),
            nivel,
            original.getTipo1(),
            original.getTipo2(),
            original.getSomCaracteristico(),
            original.getHp(),
            original.getAtaque(),
            original.getDefesa(),
            original.getSpAtaque(),
            original.getSpDefesa(),
            original.getVelocidade(),
            original.getDescricao(),
            original.getHabilidade());
        return copia;
    }
    
    /**
     * Abre um diálogo para o usuário escolher onde salvar
     */
    public static boolean salvarComDialogo(Jogador jogador) {
        JFileChooser fileChooser = new JFileChooser(PASTA_SAVES);
        fileChooser.setDialogTitle("Salvar Jogo");
        fileChooser.setSelectedFile(new File(jogador.getNome() + ".txt"));
        
        // Filtro para arquivos TXT
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
            }
            
            @Override
            public String getDescription() {
                return "Arquivos de Save (*.txt)";
            }
        });
        
        int resultado = fileChooser.showSaveDialog(null);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            String caminho = arquivo.getAbsolutePath();
            
            // Garantir extensão .txt
            if (!caminho.toLowerCase().endsWith(".txt")) {
                caminho += ".txt";
            }
            
            // Extrair nome do arquivo sem caminho e extensão
            String nomeArquivo = arquivo.getName().replace(".txt", "");
            
            if (salvarJogador(jogador, nomeArquivo)) {
                JOptionPane.showMessageDialog(null,
                    "Jogo salvo com sucesso!\n" + caminho,
                    "Salvo!",
                    JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null,
                    "Erro ao salvar o jogo!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        return false;
    }
    
    /**
     * Abre um diálogo para o usuário escolher qual arquivo carregar
     */
    public static Jogador carregarComDialogo() {
        JFileChooser fileChooser = new JFileChooser(PASTA_SAVES);
        fileChooser.setDialogTitle("Carregar Jogo");
        
        // Filtro para arquivos TXT
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
            }
            
            @Override
            public String getDescription() {
                return "Arquivos de Save (*.txt)";
            }
        });
        
        int resultado = fileChooser.showOpenDialog(null);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            Jogador jogador = carregarJogador(arquivo.getAbsolutePath());
            
            if (jogador != null) {
                JOptionPane.showMessageDialog(null,
                    "Bem-vindo de volta, " + jogador.getNome() + "!",
                    "Jogo Carregado",
                    JOptionPane.INFORMATION_MESSAGE);
                return jogador;
            } else {
                JOptionPane.showMessageDialog(null,
                    "Erro ao carregar o jogo!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        
        return null;
    }
    
    /**
     * Lista todos os arquivos de save disponíveis
     */
    public static File[] listarSaves() {
        File pastasSaves = new File(PASTA_SAVES);
        return pastasSaves.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
    }
    
    /**
     * Verifica se existe algum save
     */
    public static boolean existemSaves() {
        File[] saves = listarSaves();
        return saves != null && saves.length > 0;
    }
}