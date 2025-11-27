public class Pokemon {
    
    // Atributos bases
    protected String nome;
    protected int numeroPokedex;    protected int nivel;
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

    public Pokemon(String nome, int numeroPokedex, String tipo) {
        this.nome = nome;
        this.numeroPokedex = numeroPokedex;
        this.tipo = tipo;
        this.nivel = nivel;
        this.experiencia = experiencia;
        this.desmaiado = false;
        this.paralisado = false;
        this.somCaracteristico = somCaracteristico;
    }
}
