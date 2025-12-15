package model.pokemon;

public class Pokemon {
    // Atributos bases
    private String nome;
    private int numeroPokedex;    
    private int nivel;
    // Tipos de cada pokemon, podendo ser 1 ou 2 fiz isso para facilitar a implementaçao futura de pokemons com 2 tipos
    private String tipo1;  
    private String tipo2;  
    
    private String somCaracteristico;
    private String descricao; 
    private String habilidade; // talvez util futuramente mas tenho la minhas duvidas ...
    
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
    
    // Construtor completo
    public Pokemon(String nome, int numeroPokedex, int nivel, String tipo1, String tipo2,
                   String somCaracteristico, int hp, int ataque, int defesa, 
                   int spAtaque, int spDefesa, int velocidade, 
                   String descricao, String habilidade) {
        this.nome = nome;
        this.numeroPokedex = numeroPokedex;
        this.nivel = nivel;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2 != null ? tipo2 : "-"; // Se não tiver tipo2, usa "-" , rever essa logica depois ...
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
    }

    public Pokemon(String nome, int numeroPokedex, String tipo1, String tipo2,
                   String somCaracteristico, int hp, int ataque, int defesa, 
                   int spAtaque, int spDefesa, int velocidade, 
                   String descricao, String habilidade) {
        this.nome = nome;
        this.numeroPokedex = numeroPokedex;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2 != null ? tipo2 : "-"; // Se não tiver tipo2, usa "-" , rever essa logica depois ...
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
    }
    
    // GETTERS (ESSENCIAIS para a tabela!)
    public String getNome() { return nome; }
    public int getNumeroPokedex() { return numeroPokedex; }
    public String getNumeroFormatado() { 
        return String.format("%03d", numeroPokedex); // Retorna "001", "025", etc
    }
    public int getNivel() { return nivel; }
    public String getTipo1() { return tipo1; }
    public String getTipo2() { return tipo2; }
    public String getSomCaracteristico() { return somCaracteristico; }
    public int getHp() { return hp; }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public int getSpAtaque() { return spAtaque; }
    public int getSpDefesa() { return spDefesa; }
    public int getVelocidade() { return velocidade; }
    public int getExperiencia() { return experiencia; }
    public String getDescricao() { return descricao; }
    public String getHabilidade() { return habilidade; }
    public boolean isDesmaiado() { return desmaiado; }
    public boolean isParalisado() { return paralisado; }
    
    // SETTERS (caso precise modificar depois)
    public void setNivel(int nivel) { this.nivel = nivel; }
    public void setHp(int hp) { this.hp = hp; }
    public void setDesmaiado(boolean desmaiado) { this.desmaiado = desmaiado; }
    
    // Método útil para exibir na tabela , depois rever isso ... 
    @Override
    public String toString() {
        return nome + " (#" + getNumeroFormatado() + ")";
    }
    
    // Método equals para comparação (útil no contains()) , revisar se é o suficiente mas creio que sim ...
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