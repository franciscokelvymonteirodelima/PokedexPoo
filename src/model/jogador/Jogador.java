package model.jogador;

import model.pokemon.Pokemon;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Jogador {
    
    // Atributos básicos
    private String nome;
    private int idade;
    private String genero;
    private String cidadeOrigem;
    
    // Atributos do jogo
    private int dinheiro;
    private int pokemonsCapturados;
    
    // ============ NOVO: Colecionáveis ============
    private Set<Integer> colecionaveisComprados = new HashSet<>();
    
    // Relacionamentos
    private List<Pokemon> timePokemon; // Time atual (até 6 Pokémons)
    private List<Pokemon> pcBox; // Pokémons no PC
    private Inventario inventario;
    
    // Construtor completo
    public Jogador(String nome, int idade, String genero, String cidadeOrigem) {
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.cidadeOrigem = cidadeOrigem;
        this.dinheiro = 3000; // Dinheiro inicial
        this.pokemonsCapturados = 0;
        this.timePokemon = new ArrayList<>();
        this.pcBox = new ArrayList<>();
        this.inventario = new Inventario();
    }
    
    // Construtor simples
    public Jogador(String nome) {
        this(nome, 10, "Não informado", "Pallet Town");
    }
    
    // GETTERS
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getGenero() { return genero; }
    public String getCidadeOrigem() { return cidadeOrigem; }
    public int getDinheiro() { return dinheiro; }
    public int getPokemonsCapturados() { return pokemonsCapturados; }
    public List<Pokemon> getTimePokemon() { return timePokemon; }
    public List<Pokemon> getPcBox() { return pcBox; }
    public Inventario getInventario() { return inventario; }
    
    // SETTERS
    public void setNome(String nome) { this.nome = nome; }
    public void setIdade(int idade) { this.idade = idade; }
    public void setGenero(String genero) { this.genero = genero; }
    public void setCidadeOrigem(String cidadeOrigem) { this.cidadeOrigem = cidadeOrigem; }
    public void setPokemonsCapturados(int pokemonsCapturados) { this.pokemonsCapturados = pokemonsCapturados; }
    
    // MÉTODOS DE GERENCIAMENTO DE DINHEIRO
    public void ganharDinheiro(int quantidade) {
        this.dinheiro += quantidade;
        System.out.println("Você ganhou $" + quantidade + "! Total: $" + this.dinheiro);
    }
    
    public boolean gastarDinheiro(int quantidade) {
        if (this.dinheiro >= quantidade) {
            this.dinheiro -= quantidade;
            System.out.println("Você gastou $" + quantidade + ". Restam: $" + this.dinheiro);
            return true;
        } else {
            System.out.println("Dinheiro insuficiente!");
            return false;
        }
    }
    
    // MÉTODOS DE GERENCIAMENTO DE POKÉMONS
    public void capturarPokemon(Pokemon pokemon) {
        if (timePokemon.size() < 6) {
            timePokemon.add(pokemon);
        } else {
            pcBox.add(pokemon);
            System.out.println(pokemon.getNome() + " foi enviado para o PC!");
        }
        this.pokemonsCapturados++;
        System.out.println("Parabéns! Você capturou " + pokemon.getNome() + "!");
    }
    
    public void moverParaTime(Pokemon pokemon) {
        if (timePokemon.size() < 6 && pcBox.contains(pokemon)) {
            pcBox.remove(pokemon);
            timePokemon.add(pokemon);
            System.out.println(pokemon.getNome() + " foi movido para o time!");
        } else {
            System.out.println("Não foi possível mover o Pokémon!");
        }
    }
    
    public void moverParaPC(Pokemon pokemon) {
        if (timePokemon.size() > 1 && timePokemon.contains(pokemon)) {
            timePokemon.remove(pokemon);
            pcBox.add(pokemon);
            System.out.println(pokemon.getNome() + " foi movido para o PC!");
        } else {
            System.out.println("Não foi possível mover o Pokémon!");
        }
    }
    
    // ============ NOVOS MÉTODOS: COLECIONÁVEIS ============
    
    /**
     * Retorna o conjunto de índices dos colecionáveis comprados
     */
    public Set<Integer> getColecionaveisComprados() {
        return colecionaveisComprados;
    }

    /**
     * Adiciona um colecionável à coleção do jogador
     * @param indice O índice do item colecionável (0-9)
     */
    public void adicionarColecionavel(int indice) {
        colecionaveisComprados.add(indice);
    }

    /**
     * Verifica se um colecionável já foi comprado
     * @param indice O índice do item colecionável (0-9)
     * @return true se já foi comprado, false caso contrário
     */
    public boolean temColecionavel(int indice) {
        return colecionaveisComprados.contains(indice);
    }

    /**
     * Remove um colecionável (caso precise)
     * @param indice O índice do item colecionável (0-9)
     */
    public void removerColecionavel(int indice) {
        colecionaveisComprados.remove(indice);
    }

    /**
     * Retorna a quantidade de colecionáveis comprados
     */
    public int quantidadeColecionaveis() {
        return colecionaveisComprados.size();
    }
    
    // ============ FIM DOS MÉTODOS DE COLECIONÁVEIS ============
    
    // MÉTODOS DE EXIBIÇÃO
    // isso talvez seja substituido mas serve pra testar ...
    public void mostrarPerfil() {
        System.out.println("=== PERFIL DO JOGADOR ===");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Gênero: " + genero);
        System.out.println("Cidade: " + cidadeOrigem);
        System.out.println("Dinheiro: $" + dinheiro);
        System.out.println("Pokémons capturados: " + pokemonsCapturados);
        
    }
    
    public void mostrarTime() {
        System.out.println("=== TIME ATUAL ===");
        if (timePokemon.isEmpty()) {
            System.out.println("Seu time está vazio!");
            return;
        }
        for (int i = 0; i < timePokemon.size(); i++) {
            Pokemon p = timePokemon.get(i);
            System.out.println((i + 1) + ". " + p.getNome() + " (Lv." + p.getNivel() + ")");
        }
    }
    
    public void mostrarPC() {
        System.out.println("=== PC BOX ===");
        if (pcBox.isEmpty()) {
            System.out.println("Seu PC está vazio!");
            return;
        }
        for (Pokemon p : pcBox) {
            System.out.println(p.getNome() + " (Lv." + p.getNivel() + ")");
        }
    }
    
    // MÉTODO PARA SALVAR PROGRESSO (futuramente)
    public void salvarProgresso() {
        // Chamar o sistema de arquivos para salvar
        System.out.println("Chamando salvar...");
        model.frames.jogador.SistemaDeArquivos.salvarComDialogo(this);
    }
    
    // MÉTODO PARA CARREGAR PROGRESSO (futuramente)
    public void carregarProgresso() {
        // Implementação futura com arquivo
        System.out.println("Progresso carregado!");
    }

    // Aumentar dinheiro (ex: vender item ou vencer batalha)
    public void adicionarDinheiro(double valor) {
        this.dinheiro += valor;
    }

    // Diminuir dinheiro (ex: comprar Pokébola)
    public boolean removerDinheiro(double valor) {
        if (this.dinheiro >= valor) {
            this.dinheiro -= valor;
            return true; // Compra realizada
        }
        return false; // Saldo insuficiente
    }
}