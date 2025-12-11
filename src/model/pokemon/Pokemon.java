package model.pokemon;

public class Pokemon {
    
    // Atributos bases
    protected String nome;
    protected int numeroPokedex;    
    protected int nivel;
    protected String tipo;
    protected String somCaracteristico;
    
    //Atributos de batalha
    protected int hp;
    protected int ataque;
    protected int defesa;
    protected int spAtaque;
    protected int spDefesa;
    protected int velocidade;
    protected int experiencia;

    //estados do pokemon
    protected boolean desmaiado;
    protected boolean paralisado; // ao decorrer do desenvolvimento esse atributo pode acabar sendo nao utilizado...

    // Construtor da classe Pokemon
    public Pokemon(String nome, int numeroPokedex, String tipo, String somCaracteristico){
        this.nome = nome;
        this.numeroPokedex = numeroPokedex;        
        this.tipo = tipo;
        this.somCaracteristico = somCaracteristico;
        this.hp = 0;
        this.ataque = 0;
        this.defesa = 0;
        this.spAtaque = 0;
        this.spDefesa = 0;
        this.velocidade = 0;
        this.nivel = 0;
    }

    //sobrecarga de construtor da classe Pokemon, talvez esse seja o mais utilizado no desenvolvimento do projeto
    public Pokemon(String nome, int numeroPokedex, int nivel, String tipo, String somCaracteristico,
               int hp, int ataque, int defesa, int spAtaque, int spDefesa, int velocidade){
        this.nome = nome;
        this.numeroPokedex = numeroPokedex;
        this.nivel = nivel;
        this.tipo = tipo;
        this.somCaracteristico = somCaracteristico;
        this.hp = hp;
        this.ataque = ataque;
        this.defesa = defesa;
        this.spAtaque = spAtaque;
        this.spDefesa = spDefesa;
        this.velocidade = velocidade;
        this.experiencia = 0;
        this.desmaiado = false;
        this.paralisado = false;
    }
}
