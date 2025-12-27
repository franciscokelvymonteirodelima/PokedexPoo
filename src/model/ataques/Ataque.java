package model.ataques;

public class Ataque {
    
    private String nome;
    private String tipo;
    private int poder;
    private int precisao;
    private int pp; // Power Points - quantas vezes pode usar
    private int ppAtual; // PP atual do ataque
    
    public Ataque(String nome, String tipo, int poder, int precisao, int pp) {
        this.nome = nome;
        this.tipo = tipo;
        this.poder = poder;
        this.precisao = precisao;
        this.pp = pp;
        this.ppAtual = pp;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public int getPoder() {
        return poder;
    }
    
    public int getPrecisao() {
        return precisao;
    }
    
    public int getPp() {
        return pp;
    }
    
    public int getPpAtual() {
        return ppAtual;
    }
    
    public void usarAtaque() {
        if (ppAtual > 0) {
            ppAtual--;
        }
    }
    
    public void restaurarPp() {
        ppAtual = pp;
    }
    
    public boolean podeUsar() {
        return ppAtual > 0;
    }
}
