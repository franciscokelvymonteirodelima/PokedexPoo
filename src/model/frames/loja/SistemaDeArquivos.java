package model.frames.loja;

import model.jogador.Jogador;
import model.pokemon.Pokemon;
import model.pokedex.Pokedex;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

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
            
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static Jogador carregarJogador(String caminho) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha, secao = "";
            Jogador jogador = null;

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
                        case "Nome": jogador = new Jogador(valor); break;
                        case "Idade": if (jogador != null) jogador.setIdade(Integer.parseInt(valor)); break;
                        case "Genero": if (jogador != null) jogador.setGenero(valor); break;
                        case "Cidade": if (jogador != null) jogador.setCidadeOrigem(valor); break;
                        case "Dinheiro": 
                            if (jogador != null) {
                                int d = Integer.parseInt(valor);
                                jogador.gastarDinheiro(jogador.getDinheiro()); // Reseta para 0
                                jogador.ganharDinheiro(d); // Define o valor do save
                            }
                            break;
                        case "PokemonsCapturados":
                             // Se sua classe Jogador tiver setPokemonsCapturados, adicione aqui
                             break;
                    }
                } else if (secao.equals("[TIME_POKEMON]") || secao.equals("[PC_BOX]")) {
                    String[] partes = linha.split(",");
                    if (partes.length == 2 && jogador != null) {
                        int num = Integer.parseInt(partes[0]);
                        int lvl = Integer.parseInt(partes[1]);
                        Pokemon p = buscarNaPokedex(num, lvl);
                        if (p != null) {
                            if (secao.equals("[TIME_POKEMON]")) jogador.getTimePokemon().add(p);
                            else jogador.getPcBox().add(p);
                        }
                    }
                }
            }
            return jogador;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Pokemon buscarNaPokedex(int num, int lvl) {
        for (Pokemon p : pokedex.listarTodos()) {
            if (p.getNumeroPokedex() == num) {
                // Retorna uma nova instância baseada na original com o nível correto
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