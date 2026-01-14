package model.pokemon;

import model.ataques.Ataque;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    // Atributos bases
    private String nome;
    private int numeroPokedex;    
    private int nivel;
    // Tipos de cada pokemon, podendo ser 1 ou 2
    private String tipo1;  
    private String tipo2;  
    
    private String somCaracteristico;
    private String descricao; 
    private String habilidade; 
    private String caminhoImagem; 
    
    // Atributos de batalha
    private int hp;
    private int ataque;
    private int defesa;
    private int spAtaque;
    private int spDefesa;
    private int velocidade;
    private int experiencia;
    
    // Estados do pokemon
    private boolean desmaiado;
    private boolean paralisado;
    
    // Ataques do pokemon
    private List<Ataque> ataques;
    
    // Construtor completo (Com Nível)
    public Pokemon(String nome, int numeroPokedex, int nivel, String tipo1, String tipo2,
                   String somCaracteristico, int hp, int ataque, int defesa, 
                   int spAtaque, int spDefesa, int velocidade, 
                   String descricao, String habilidade) {
        this.nome = nome;
        this.numeroPokedex = numeroPokedex;
        this.nivel = nivel;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2 != null ? tipo2 : "-";
        this.somCaracteristico = somCaracteristico;
        this.hp = hp;
        this.ataque = ataque;
        this.defesa = defesa;
        this.spAtaque = spAtaque;
        this.spDefesa = spDefesa;
        this.velocidade = velocidade;
        this.descricao = descricao;
        this.habilidade = habilidade;
        this.experiencia = 0;
        this.desmaiado = false;
        this.paralisado = false;
        this.ataques = new ArrayList<>();
    }

    // Construtor sem Nível (Assume Nível 1 por padrão)
    public Pokemon(String nome, int numeroPokedex, String tipo1, String tipo2,
                   String somCaracteristico, int hp, int ataque, int defesa, 
                   int spAtaque, int spDefesa, int velocidade, 
                   String descricao, String habilidade) {
        this(nome, numeroPokedex, 1, tipo1, tipo2, somCaracteristico, hp, ataque, defesa, spAtaque, spDefesa, velocidade, descricao, habilidade);
    }
    
    // Construtor simples
    public Pokemon(String nome, int numeroPokedex, String tipo1, String somCaracteristico) {
        this.nome = nome;
        this.numeroPokedex = numeroPokedex;        
        this.tipo1 = tipo1;
        this.tipo2 = "-";
        this.somCaracteristico = somCaracteristico;
        this.hp = 0;
        this.ataque = 0;
        this.defesa = 0;
        this.spAtaque = 0;
        this.spDefesa = 0;
        this.velocidade = 0;
        this.nivel = 1;
        this.descricao = "";
        this.habilidade = "";
        this.ataques = new ArrayList<>();
    }
    
    // Construtor com Imagem (O que você usa no carregamento da 1ª Geração)
    public Pokemon(String nome, int numeroPokedex, String tipo1, String tipo2,
                   String somCaracteristico, int hp, int ataque, int defesa, 
                   int spAtaque, int spDefesa, int velocidade, 
                   String descricao, String habilidade, String caminhoImagem) {
        this.nome = nome;
        this.numeroPokedex = numeroPokedex;
        this.nivel = 1;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2 != null ? tipo2 : "-";
        this.somCaracteristico = somCaracteristico;
        this.hp = hp;
        this.ataque = ataque;
        this.defesa = defesa;
        this.spAtaque = spAtaque;
        this.spDefesa = spDefesa;
        this.velocidade = velocidade;
        this.descricao = descricao;
        this.habilidade = habilidade;
        this.experiencia = 0;
        this.desmaiado = false;
        this.paralisado = false;
        this.ataques = new ArrayList<>();
        this.caminhoImagem = caminhoImagem;
    }

    // Métodos de ataques
    public void adicionarAtaque(Ataque ataque) {
        ataques.add(ataque);
    }
    
    public List<Ataque> getAtaques() {
        return ataques;
    }
    
    public Ataque getAtaque(int indice) {
        if (indice >= 0 && indice < ataques.size()) {
            return ataques.get(indice);
        }
        return null;
    }
    
    public int usarAtaque(Pokemon alvo, int indiceAtaque) {
        if (indiceAtaque < 0 || indiceAtaque >= ataques.size()) {
            return 0;
        }
        
        Ataque ataque = ataques.get(indiceAtaque);
        if (!ataque.podeUsar()) {
            return 0;
        }
        
        ataque.usarAtaque();
        
        int dano = ataque.getPoder() - alvo.getDefesa();
        if (dano < 0) {
            dano = 1; 
        }
        
        int novoHp = alvo.getHp() - dano;
        if (novoHp < 0) {
            novoHp = 0;
            alvo.setDesmaiado(true);
        }
        alvo.setHp(novoHp);
        
        return dano;
    }
    
    // Getters
    public String getNome() { return nome; }
    public int getNumeroPokedex() { return numeroPokedex; }
    public int getNivel() { return nivel; }
    public String getTipo1() { return tipo1; }
    public String getTipo2() { return tipo2; }
    public String getSomCaracteristico() { return somCaracteristico; }
    public String getDescricao() { return descricao; }
    public String getHabilidade() { return habilidade; }
    public int getHp() { return hp; }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public int getSpAtaque() { return spAtaque; }
    public int getSpDefesa() { return spDefesa; }
    public int getVelocidade() { return velocidade; }
    public int getExperiencia() { return experiencia; }
    public String getCaminhoImagem() { return caminhoImagem; }
    
    // Setters
    public void setNivel(int nivel) { this.nivel = nivel; }
    public void setHp(int hp) { this.hp = hp; }
    public void setDesmaiado(boolean desmaiado) { this.desmaiado = desmaiado; }
    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }
    
    @Override
    public String toString() {
        return nome + " (#" + getNumeroFormatado() + ")";
    }
    
    // Resolvido: agora formata o número sem disparar erro
    private String getNumeroFormatado() {
        return String.format("%03d", numeroPokedex);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pokemon pokemon = (Pokemon) obj;
        return numeroPokedex == pokemon.numeroPokedex;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(numeroPokedex);
    }
}