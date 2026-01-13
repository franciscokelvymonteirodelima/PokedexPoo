package model.jogador;

import model.pokemon.Pokemon;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Jogador {

    // ================= BÁSICO =================
    private String nome;
    private int idade;
    private String genero;
    private String cidadeOrigem;

    // ================= JOGO =================
    private int dinheiro;
    private int pokemonsCapturados;

    // ================= NOVO: RANKING =================
    private int score;
    private int vitorias;

    // ================= COLECIONÁVEIS =================
    private Set<Integer> colecionaveisComprados = new HashSet<>();

    // ================= RELACIONAMENTOS =================
    private List<Pokemon> timePokemon;
    private List<Pokemon> pcBox;
    private Inventario inventario;

    // ================= CONSTRUTORES =================
    public Jogador(String nome, int idade, String genero, String cidadeOrigem) {
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.cidadeOrigem = cidadeOrigem;

        this.dinheiro = 3000;
        this.pokemonsCapturados = 0;

        // Ranking inicia zerado
        this.score = 0;
        this.vitorias = 0;

        this.timePokemon = new ArrayList<>();
        this.pcBox = new ArrayList<>();
        this.inventario = new Inventario();
    }

    public Jogador(String nome) {
        this(nome, 10, "Não informado", "Pallet Town");
    }

    // ================= GETTERS EXISTENTES =================
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getGenero() { return genero; }
    public String getCidadeOrigem() { return cidadeOrigem; }
    public int getDinheiro() { return dinheiro; }
    public int getPokemonsCapturados() { return pokemonsCapturados; }
    public List<Pokemon> getTimePokemon() { return timePokemon; }
    public List<Pokemon> getPcBox() { return pcBox; }
    public Inventario getInventario() { return inventario; }

    // ================= SETTERS EXISTENTES =================
    public void setNome(String nome) { this.nome = nome; }
    public void setIdade(int idade) { this.idade = idade; }
    public void setGenero(String genero) { this.genero = genero; }
    public void setCidadeOrigem(String cidadeOrigem) { this.cidadeOrigem = cidadeOrigem; }
    public void setPokemonsCapturados(int pokemonsCapturados) {
        this.pokemonsCapturados = pokemonsCapturados;
    }

    // ================= DINHEIRO (EXISTENTE) =================
    public void ganharDinheiro(int quantidade) {
        this.dinheiro += quantidade;
    }

    public boolean gastarDinheiro(int quantidade) {
        if (this.dinheiro >= quantidade) {
            this.dinheiro -= quantidade;
            return true;
        }
        return false;
    }

    // ================= POKÉMONS (EXISTENTE) =================
    public void capturarPokemon(Pokemon pokemon) {
        if (timePokemon.size() < 6) {
            timePokemon.add(pokemon);
        } else {
            pcBox.add(pokemon);
        }
        this.pokemonsCapturados++;
    }

    public void moverParaTime(Pokemon pokemon) {
        if (timePokemon.size() < 6 && pcBox.contains(pokemon)) {
            pcBox.remove(pokemon);
            timePokemon.add(pokemon);
        }
    }

    public void moverParaPC(Pokemon pokemon) {
        if (timePokemon.size() > 1 && timePokemon.contains(pokemon)) {
            timePokemon.remove(pokemon);
            pcBox.add(pokemon);
        }
    }

    // ================= COLECIONÁVEIS (EXISTENTE) =================
    public Set<Integer> getColecionaveisComprados() {
        return colecionaveisComprados;
    }

    public void adicionarColecionavel(int indice) {
        colecionaveisComprados.add(indice);
    }

    public boolean temColecionavel(int indice) {
        return colecionaveisComprados.contains(indice);
    }

    public void removerColecionavel(int indice) {
        colecionaveisComprados.remove(indice);
    }

    public int quantidadeColecionaveis() {
        return colecionaveisComprados.size();
    }

    // ================= NOVO: RANKING =================
    public int getScore() {
        return score;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setScore(int score) {
        this.score = Math.max(0, score);
    }

    public void setVitorias(int vitorias) {
        this.vitorias = Math.max(0, vitorias);
    }

    public void adicionarScore(int valor) {
        this.score += valor;
    }

    public void registrarVitoria() {
        this.vitorias++;
        this.score += 100;
    }

    // ================= EXIBIÇÃO (EXISTENTE) =================
    public void mostrarPerfil() {
        System.out.println("Nome: " + nome);
        System.out.println("Score: " + score);
        System.out.println("Vitórias: " + vitorias);
    }

    // ================= SALVAR (EXISTENTE) =================
    public void salvarProgresso() {
        model.frames.jogador.SistemaDeArquivos.salvarComDialogo(this);
    }
}
