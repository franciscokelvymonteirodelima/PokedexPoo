package model.frames.loja;

import model.jogador.Jogador;
import model.pokemon.Pokemon;
import model.pokedex.Pokedex;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SistemaDeArquivos {
    private static final String PASTA_SAVES = "saves";
    private static Pokedex pokedex = new Pokedex();

    static {
        try {
            Files.createDirectories(Paths.get(PASTA_SAVES));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean salvarJogador(Jogador jogador, String nomeArquivo) {
        String caminho = PASTA_SAVES + File.separator + nomeArquivo + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            // Seção Dados do Jogador
            writer.write("[JOGADOR]\n");
            writer.write("Nome=" + jogador.getNome() + "\n");
            writer.write("Idade=" + jogador.getIdade() + "\n");
            writer.write("Genero=" + jogador.getGenero() + "\n");
            writer.write("Cidade=" + jogador.getCidadeOrigem() + "\n");
            writer.write("Dinheiro=" + jogador.getDinheiro() + "\n");
            writer.write("PokemonsCapturados=" + jogador.getPokemonsCapturados() + "\n");
            
            // Seção Time
            writer.write("[TIME_POKEMON]\n");
            for (Pokemon p : jogador.getTimePokemon()) {
                writer.write(p.getNumeroPokedex() + "," + p.getNivel() + "\n");
            }

            // Seção PC Box
            writer.write("[PC_BOX]\n");
            for (Pokemon p : jogador.getPcBox()) {
                writer.write(p.getNumeroPokedex() + "," + p.getNivel() + "\n");
            }
            
            // Seção Colecionáveis
            writer.write("[COLECIONAVEIS]\n");
            Set<Integer> colecionaveis = jogador.getColecionaveisComprados();
            if (colecionaveis != null && !colecionaveis.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (Integer indice : colecionaveis) {
                    if (sb.length() > 0) sb.append(",");
                    sb.append(indice);
                }
                writer.write(sb.toString() + "\n");
            }
            
            System.out.println("✅ Save atualizado com sucesso!");
            return true;
        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static Jogador carregarJogador(String caminho) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha, secao = "";
            Jogador jogador = null;
            int pokemonsCapturados = 0; // Armazena temporariamente

            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;

                if (linha.startsWith("[")) {
                    secao = linha;
                    continue;
                }

                if (secao.equals("[JOGADOR]")) {
                    String[] p = linha.split("=", 2);
                    if (p.length < 2) continue;
                    
                    String chave = p[0];
                    String valor = p[1];

                    switch (chave) {
                        case "Nome": 
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
                                int d = Integer.parseInt(valor);
                                jogador.gastarDinheiro(jogador.getDinheiro()); // Reseta para 0
                                jogador.ganharDinheiro(d); // Define o valor do save
                            }
                            break;
                        case "PokemonsCapturados":
                            // CORREÇÃO: Agora carrega corretamente o valor
                            pokemonsCapturados = Integer.parseInt(valor);
                            break;
                    }
                } else if (secao.equals("[TIME_POKEMON]") || secao.equals("[PC_BOX]")) {
                    String[] partes = linha.split(",");
                    if (partes.length == 2 && jogador != null) {
                        int num = Integer.parseInt(partes[0]);
                        int lvl = Integer.parseInt(partes[1]);
                        Pokemon p = buscarNaPokedex(num, lvl);
                        if (p != null) {
                            if (secao.equals("[TIME_POKEMON]")) {
                                jogador.getTimePokemon().add(p);
                            } else {
                                jogador.getPcBox().add(p);
                            }
                        }
                    }
                } else if (secao.equals("[COLECIONAVEIS]")) {
                    if (jogador != null && !linha.isEmpty()) {
                        String[] indices = linha.split(",");
                        for (String indiceStr : indices) {
                            try {
                                int indice = Integer.parseInt(indiceStr.trim());
                                jogador.adicionarColecionavel(indice);
                            } catch (NumberFormatException e) {
                                System.err.println("Erro ao carregar colecionável: " + indiceStr);
                            }
                        }
                    }
                }
            }
            
            // CORREÇÃO: Aplica o contador de pokémons capturados ao final
            if (jogador != null) {
                jogador.setPokemonsCapturados(pokemonsCapturados);
                System.out.println("✅ PokemonsCapturados restaurado: " + pokemonsCapturados);
            }
            
            return jogador;
        } catch (Exception e) {
            System.err.println("❌ Erro ao carregar: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private static Pokemon buscarNaPokedex(int num, int lvl) {
        for (Pokemon p : pokedex.listarTodos()) {
            if (p.getNumeroPokedex() == num) {
                return new Pokemon(p.getNome(), p.getNumeroPokedex(), lvl, p.getTipo1(), p.getTipo2(), 
                                   p.getSomCaracteristico(), p.getHp(), p.getAtaque(), p.getDefesa(), 
                                   p.getSpAtaque(), p.getSpDefesa(), p.getVelocidade(), p.getDescricao(), p.getHabilidade());
            }
        }
        return null;
    }

    public static boolean existemSaves() {
        File f = new File(PASTA_SAVES);
        File[] saves = f.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
        return saves != null && saves.length > 0;
    }
}