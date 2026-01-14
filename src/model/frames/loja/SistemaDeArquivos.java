package model.frames.loja;

import model.jogador.Jogador;
import model.pokemon.Pokemon;
import model.pokedex.Pokedex;

import java.io.*;
import java.nio.file.*;
import java.util.Set;

public class SistemaDeArquivos {

    private static final String PASTA_SAVES = "saves";
    private static final Pokedex pokedex = new Pokedex();

    static {
        try {
            Files.createDirectories(Paths.get(PASTA_SAVES));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // salvar o jogador em um arquivo de texto
    public static boolean salvarJogador(Jogador jogador, String nomeArquivo) {

        String caminho = PASTA_SAVES + File.separator + nomeArquivo + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {

            writer.write("[JOGADOR]\n");
            writer.write("Nome=" + jogador.getNome() + "\n");
            writer.write("Idade=" + jogador.getIdade() + "\n");
            writer.write("Genero=" + jogador.getGenero() + "\n");
            writer.write("Cidade=" + jogador.getCidadeOrigem() + "\n");
            writer.write("Dinheiro=" + jogador.getDinheiro() + "\n");
            writer.write("PokemonsCapturados=" + jogador.getPokemonsCapturados() + "\n");

            writer.write("[RANKING]\n");
            writer.write("Score=" + jogador.getScore() + "\n");
            writer.write("Vitorias=" + jogador.getVitorias() + "\n");

            writer.write("[TIME_POKEMON]\n");
            for (Pokemon p : jogador.getTimePokemon()) {
                writer.write(p.getNumeroPokedex() + "," + p.getNivel() + "\n");
            }

            writer.write("[PC_BOX]\n");
            for (Pokemon p : jogador.getPcBox()) {
                writer.write(p.getNumeroPokedex() + "," + p.getNivel() + "\n");
            }

            writer.write("[COLECIONAVEIS]\n");
            Set<Integer> colecionaveis = jogador.getColecionaveisComprados();
            if (colecionaveis != null && !colecionaveis.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (Integer i : colecionaveis) {
                    if (sb.length() > 0) sb.append(",");
                    sb.append(i);
                }
                writer.write(sb.toString() + "\n");
            }

            System.out.println("✅ Save salvo com sucesso!");
            return true;

        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar: " + e.getMessage());
            return false;
        }
    }

    public static Jogador carregarJogador(String caminho) {

        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {

            String linha;
            String secao = "";
            Jogador jogador = null;

            int pokemonsCapturados = 0;
            int score = 0;
            int vitorias = 0;

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

                    switch (p[0]) {
                        case "Nome":
                            jogador = new Jogador(p[1]);
                            break;
                        case "Idade":
                            if (jogador != null) jogador.setIdade(Integer.parseInt(p[1]));
                            break;
                        case "Genero":
                            if (jogador != null) jogador.setGenero(p[1]);
                            break;
                        case "Cidade":
                            if (jogador != null) jogador.setCidadeOrigem(p[1]);
                            break;
                        case "Dinheiro":
                            if (jogador != null) {
                                jogador.gastarDinheiro(jogador.getDinheiro());
                                jogador.ganharDinheiro(Integer.parseInt(p[1]));
                            }
                            break;
                        case "PokemonsCapturados":
                            pokemonsCapturados = Integer.parseInt(p[1]);
                            break;
                    }
                }

                else if (secao.equals("[RANKING]")) {

                    String[] p = linha.split("=", 2);
                    if (p.length < 2) continue;

                    if (p[0].equals("Score")) score = Integer.parseInt(p[1]);
                    if (p[0].equals("Vitorias")) vitorias = Integer.parseInt(p[1]);
                }

                else if (secao.equals("[TIME_POKEMON]") || secao.equals("[PC_BOX]")) {

                    String[] p = linha.split(",");
                    if (p.length != 2 || jogador == null) continue;

                    int num = Integer.parseInt(p[0]);
                    int lvl = Integer.parseInt(p[1]);

                    Pokemon pokemon = buscarNaPokedex(num, lvl);
                    if (pokemon != null) {
                        if (secao.equals("[TIME_POKEMON]")) {
                            jogador.getTimePokemon().add(pokemon);
                        } else {
                            jogador.getPcBox().add(pokemon);
                        }
                    }
                }

                // ---------------- COLECIONÁVEIS ----------------
                else if (secao.equals("[COLECIONAVEIS]")) {

                    if (jogador != null && !linha.isEmpty()) {
                        String[] indices = linha.split(",");
                        for (String s : indices) {
                            try {
                                jogador.adicionarColecionavel(Integer.parseInt(s.trim()));
                            } catch (NumberFormatException ignored) {}
                        }
                    }
                }
            }

            // Aplicar valores finais
            if (jogador != null) {
                jogador.setPokemonsCapturados(pokemonsCapturados);
                jogador.setScore(score);
                jogador.setVitorias(vitorias);
            }

            System.out.println("✅ Save carregado com sucesso!");
            return jogador;

        } catch (Exception e) {
            System.err.println("❌ Erro ao carregar: " + e.getMessage());
            return null;
        }
    }

    // =====================================================
    // BUSCAR POKÉMON NA POKEDEX
    // =====================================================
    private static Pokemon buscarNaPokedex(int num, int lvl) {

        for (Pokemon p : pokedex.listarTodos()) {
            if (p.getNumeroPokedex() == num) {
                return new Pokemon(
                        p.getNome(),
                        p.getNumeroPokedex(),
                        lvl,
                        p.getTipo1(),
                        p.getTipo2(),
                        p.getSomCaracteristico(),
                        p.getHp(),
                        p.getAtaque(),
                        p.getDefesa(),
                        p.getSpAtaque(),
                        p.getSpDefesa(),
                        p.getVelocidade(),
                        p.getDescricao(),
                        p.getHabilidade()
                );
            }
        }
        return null;
    }

    // =====================================================
    // EXISTEM SAVES?
    // =====================================================
    public static boolean existemSaves() {
        File f = new File(PASTA_SAVES);
        File[] arquivos = f.listFiles((dir, name) -> name.endsWith(".txt"));
        return arquivos != null && arquivos.length > 0;
    }
}
