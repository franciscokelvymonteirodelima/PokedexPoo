package model.jogador;

import java.util.HashMap;
import java.util.Map;

public class Inventario {

    // Mapa para armazenar itens e suas quantidades
    private Map<String, Integer> itens;

    // Construtor
    public Inventario() {
        this.itens = new HashMap<>();
        inicializarItensBasicos();
    }

    // Inicializa com alguns itens básicos
    private void inicializarItensBasicos() {
        itens.put("Poké Ball", 10);
        itens.put("Potion", 5);
        itens.put("Antidote", 2);
        itens.put("Paralyze Heal", 1);
    }

    // Adicionar item ao inventário
    public void adicionarItem(String item, int quantidade) {
        itens.put(item, itens.getOrDefault(item, 0) + quantidade);
        System.out.println("Adicionado: " + quantidade + "x " + item);
    }

    // Remover item do inventário
    public boolean removerItem(String item, int quantidade) {
        int quantidadeAtual = itens.getOrDefault(item, 0);
        if (quantidadeAtual >= quantidade) {
            itens.put(item, quantidadeAtual - quantidade);
            System.out.println("Removido: " + quantidade + "x " + item);
            return true;
        } else {
            System.out.println("Quantidade insuficiente de " + item + "!");
            return false;
        }
    }

    // Verificar se tem item suficiente
    public boolean temItem(String item, int quantidade) {
        return itens.getOrDefault(item, 0) >= quantidade;
    }

    // Obter quantidade de um item
    public int getQuantidade(String item) {
        return itens.getOrDefault(item, 0);
    }

    // Listar todos os itens
    public void listarItens() {
        System.out.println("=== INVENTÁRIO ===");
        if (itens.isEmpty()) {
            System.out.println("Inventário vazio!");
            return;
        }

        for (Map.Entry<String, Integer> entry : itens.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    // Usar item (diminui quantidade)
    public boolean usarItem(String item) {
        return removerItem(item, 1);
    }

    // Obter mapa completo (para salvar/carregar)
    public Map<String, Integer> getItens() {
        return new HashMap<>(itens);
    }
}
