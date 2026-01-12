package model.frames.jogador;

import model.jogador.Jogador;
import model.pokemon.Pokemon;
import model.pokedex.Pokedex;

import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.util.Set;

public class SistemaDeArquivos {

    private static final String PASTA_SAVES = "saves";
    private static Pokedex pokedex = new Pokedex();

    // ================= INICIALIZAÇÃO =================
    static {
        try {
            Files.createDirectories(Paths.get(PASTA_SAVES));
            System.out.println("Pasta saves pronta: " + PASTA_SAVES);
        } catch (IOException e) {
            System.err.println("Erro ao criar pasta de saves: " + e.getMessage());
        }
    }

    // ================= SALVAR =================
    public static boolean salvarJogador(Jogador jogador, String nomeArquivo) {

        String caminho = PASTA_SAVES + File.separator + nomeArquivo + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {

            // ---------- JOGADOR ----------
            writer.write("[JOGADOR]\n");
            writer.write("Nome=" + jogador.getNome() + "\n");
            writer.write("Idade=" + jogador.getIdade() + "\n");
            writer.write("Genero=" + jogador.getGenero() + "\n");
            writer.write("Cidade=" + jogador.getCidadeOrigem() + "\n");
            writer.write("Dinheiro=" + jogador.getDinheiro() + "\n");
            writer.write("PokemonsCapturados=" + jogador.getPokemonsCapturados() + "\n");

            // ---------- RANKING ----------
            writer.write("[RANKING]\n");
            writer.write("Score=" + jogador.getScore() + "\n");
            writer.write("Vitorias=" + jogador.getVitorias() + "\n");

            // ---------- TIME ----------
            writer.write("[TIME_POKEMON]\n");
            for (Pokemon p : jogador.getTimePokemon()) {
                writer.write(p.getNumeroPokedex() + "," + p.getNivel() + "\n");
            }

            // ---------- PC ----------
            writer.write("[PC_BOX]\n");
            for (Pokemon p : jogador.getPcBox()) {
                writer.write(p.getNumeroPokedex() + "," + p.getNivel() + "\n");
            }

            // ---------- COLECIONÁVEIS ----------
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

            System.out.println("✅ Jogo salvo: " + caminho);
            return true;

        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar: " + e.getMessage());
            return false;
        }
    }

    // ================= CARREGAR =================
    public static Jogador carregarJogador(String caminhoArquivo) {

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {

            String linha;
            String secao = "";
            Jogador jogador = null;

            int pokemonsCapturados = 0;
            int score = 0;
            int vitorias = 0;

            while ((linha = reader.readLine()) != null) {

                linha = linha.trim();
                if (linha.isEmpty()) continue;

                if (linha.startsWith("[") && linha.endsWith("]")) {
                    secao = linha;
                    continue;
                }

                // ---------- JOGADOR ----------
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

                // ---------- RANKING ----------
                else if (secao.equals("[RANKING]")) {

                    String[] p = linha.split("=", 2);
                    if (p.length < 2) continue;

                    if (p[0].equals("Score")) score = Integer.parseInt(p[1]);
                    if (p[0].equals("Vitorias")) vitorias = Integer.parseInt(p[1]);
                }

                // ---------- TIME / PC ----------
                else if (secao.equals("[TIME_POKEMON]") || secao.equals("[PC_BOX]")) {

                    String[] p = linha.split(",");
                    if (p.length != 2 || jogador == null) continue;

                    int num = Integer.parseInt(p[0]);
                    int lvl = Integer.parseInt(p[1]);

                    Pokemon base = buscarPokemonNaPokedex(num);
                    if (base != null) {
                        Pokemon copia = criarCopiaPokemon(base, lvl);
                        if (secao.equals("[TIME_POKEMON]")) {
                            jogador.getTimePokemon().add(copia);
                        } else {
                            jogador.getPcBox().add(copia);
                        }
                    }
                }

                // ---------- COLECIONÁVEIS ----------
                else if (secao.equals("[COLECIONAVEIS]")) {
                    if (jogador != null && !linha.isEmpty()) {
                        String[] ids = linha.split(",");
                        for (String s : ids) {
                            try {
                                jogador.adicionarColecionavel(Integer.parseInt(s.trim()));
                            } catch (NumberFormatException ignored) {}
                        }
                    }
                }
            }

            if (jogador != null) {
                jogador.setPokemonsCapturados(pokemonsCapturados);
                jogador.setScore(score);
                jogador.setVitorias(vitorias);
            }

            System.out.println("✅ Jogo carregado: " + (jogador != null ? jogador.getNome() : "erro"));
            return jogador;

        } catch (Exception e) {
            System.err.println("❌ Erro ao carregar: " + e.getMessage());
            return null;
        }
    }

    // ================= AUXILIARES =================
    private static Pokemon buscarPokemonNaPokedex(int numero) {
        for (Pokemon p : pokedex.listarTodos()) {
            if (p.getNumeroPokedex() == numero) return p;
        }
        return null;
    }

    private static Pokemon criarCopiaPokemon(Pokemon o, int nivel) {
        return new Pokemon(
            o.getNome(),
            o.getNumeroPokedex(),
            nivel,
            o.getTipo1(),
            o.getTipo2(),
            o.getSomCaracteristico(),
            o.getHp(),
            o.getAtaque(),
            o.getDefesa(),
            o.getSpAtaque(),
            o.getSpDefesa(),
            o.getVelocidade(),
            o.getDescricao(),
            o.getHabilidade()
        );
    }

    // ================= DIÁLOGOS =================
    public static boolean salvarComDialogo(Jogador jogador) {

        JFileChooser chooser = new JFileChooser(PASTA_SAVES);
        chooser.setSelectedFile(new File(jogador.getNome() + ".txt"));

        int r = chooser.showSaveDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            String nome = f.getName().replace(".txt", "");
            return salvarJogador(jogador, nome);
        }
        return false;
    }

    public static Jogador carregarComDialogo() {

        JFileChooser chooser = new JFileChooser(PASTA_SAVES);
        int r = chooser.showOpenDialog(null);

        if (r == JFileChooser.APPROVE_OPTION) {
            return carregarJogador(chooser.getSelectedFile().getAbsolutePath());
        }
        return null;
    }

    // ================= UTIL =================
    public static File[] listarSaves() {
        File f = new File(PASTA_SAVES);
        return f.listFiles((dir, name) -> name.endsWith(".txt"));
    }

    public static boolean existemSaves() {
        File[] s = listarSaves();
        return s != null && s.length > 0;
    }
}
