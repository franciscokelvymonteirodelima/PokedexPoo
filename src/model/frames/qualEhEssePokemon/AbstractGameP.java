package model.frames.qualEhEssePokemon;

public abstract class AbstractGameP {
    private int acertos; // Continua privado para segurança
    
    public abstract boolean verificarResposta(String resposta);
    
    public abstract boolean rodadaJaRespondida(int index);

    public int getAcertos() {
        return acertos;
    }

    public void setAcertos() {
        this.acertos++;
    }
}