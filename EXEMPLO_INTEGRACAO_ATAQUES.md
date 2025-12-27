# 🔗 Como os Ataques se Comunicam com as Outras Classes

## 📋 Estrutura da Integração

### **1. Pokemon ↔ Ataque**
- Cada `Pokemon` tem uma **lista de ataques** (`List<Ataque>`)
- Pokemon pode **adicionar ataques** e **usar ataques** contra outros Pokémons

### **2. Batalha ↔ Pokemon ↔ Ataque**
- A classe `Batalha` pode usar os ataques dos Pokémons durante a batalha
- Os ataques causam dano baseado no poder e defesa

---

## 💻 Exemplo Prático de Uso

```java
import model.pokemon.Pokemon;
import model.ataques.Ataque;
import model.batalha.Batalha;

public class ExemploIntegracao {
    public static void main(String[] args) {
        // Criar dois Pokémons
        Pokemon pikachu = new Pokemon("Pikachu", 25, "Electric", "-", "Pika!", 
            35, 55, 40, 50, 50, 90, "Descrição", "Static");
            
        Pokemon charizard = new Pokemon("Charizard", 6, "Fire", "Flying", "Zard!", 
            78, 84, 78, 109, 85, 100, "Descrição", "Blaze");
        
        // Criar ataques para o Pikachu
        Ataque choqueTrovao = new Ataque("Choque do Trovão", "Electric", 90, 100, 15);
        Ataque raio = new Ataque("Raio", "Electric", 120, 70, 10);
        Ataque velocidade = new Ataque("Velocidade", "Normal", 40, 100, 30);
        
        // Adicionar ataques ao Pikachu
        pikachu.adicionarAtaque(choqueTrovao);
        pikachu.adicionarAtaque(raio);
        pikachu.adicionarAtaque(velocidade);
        
        // Criar ataques para o Charizard
        Ataque lancachamas = new Ataque("Lança-chamas", "Fire", 90, 100, 15);
        Ataque explosaoFogo = new Ataque("Explosão de Fogo", "Fire", 110, 85, 5);
        
        charizard.adicionarAtaque(lancachamas);
        charizard.adicionarAtaque(explosaoFogo);
        
        // Usar um ataque do Pikachu contra o Charizard
        int dano = pikachu.usarAtaque(charizard, 0); // usa o primeiro ataque (Choque do Trovão)
        System.out.println("Pikachu usou " + pikachu.getAtaque(0).getNome() + "!");
        System.out.println("Dano causado: " + dano);
        System.out.println("HP do Charizard: " + charizard.getHp());
        
        // Verificar PP do ataque
        System.out.println("PP restante: " + pikachu.getAtaque(0).getPpAtual());
        
        // Usar outro ataque
        int dano2 = charizard.usarAtaque(pikachu, 0); // Charizard usa Lança-chamas
        System.out.println("Charizard usou " + charizard.getAtaque(0).getNome() + "!");
        System.out.println("Dano causado: " + dano2);
        System.out.println("HP do Pikachu: " + pikachu.getHp());
    }
}
```

---

## 🎮 Exemplo de Batalha com Ataques

```java
public class BatalhaComAtaques {
    public static void main(String[] args) {
        // Criar Pokémons
        Pokemon pikachu = new Pokemon("Pikachu", 25, "Electric", "-", "Pika!", 
            35, 55, 40, 50, 50, 90, "Descrição", "Static");
        Pokemon charizard = new Pokemon("Charizard", 6, "Fire", "Flying", "Zard!", 
            78, 84, 78, 109, 85, 100, "Descrição", "Blaze");
        
        // Adicionar ataques
        pikachu.adicionarAtaque(new Ataque("Choque do Trovão", "Electric", 90, 100, 15));
        charizard.adicionarAtaque(new Ataque("Lança-chamas", "Fire", 90, 100, 15));
        
        // Simular batalha por turnos
        while (pikachu.getHp() > 0 && charizard.getHp() > 0) {
            // Pikachu ataca
            if (pikachu.getAtaque(0).podeUsar()) {
                int dano = pikachu.usarAtaque(charizard, 0);
                System.out.println("Pikachu causou " + dano + " de dano!");
            }
            
            if (charizard.getHp() <= 0) {
                System.out.println("Charizard desmaiou! Pikachu venceu!");
                break;
            }
            
            // Charizard ataca
            if (charizard.getAtaque(0).podeUsar()) {
                int dano = charizard.usarAtaque(pikachu, 0);
                System.out.println("Charizard causou " + dano + " de dano!");
            }
            
            if (pikachu.getHp() <= 0) {
                System.out.println("Pikachu desmaiou! Charizard venceu!");
                break;
            }
        }
    }
}
```

---

## 📊 Métodos Disponíveis

### **Na Classe Pokemon:**

#### `adicionarAtaque(Ataque ataque)`
Adiciona um ataque à lista do Pokémon.
```java
pikachu.adicionarAtaque(choqueTrovao);
```

#### `getAtaques()`
Retorna a lista completa de ataques.
```java
List<Ataque> ataques = pikachu.getAtaques();
```

#### `getAtaque(int indice)`
Retorna um ataque específico pelo índice.
```java
Ataque primeiroAtaque = pikachu.getAtaque(0);
```

#### `usarAtaque(Pokemon alvo, int indiceAtaque)`
Usa um ataque contra outro Pokémon e retorna o dano causado.
```java
int dano = pikachu.usarAtaque(charizard, 0);
```

---

## 🔄 Fluxo de Comunicação

```
┌─────────────┐
│   Ataque    │
│  (nome,     │
│   poder,    │
│   tipo)     │
└──────┬──────┘
       │
       │ adicionarAtaque()
       │
       ▼
┌─────────────┐      usarAtaque()      ┌─────────────┐
│   Pokemon   │ ───────────────────────► │   Pokemon   │
│             │                          │   (alvo)    │
│ - ataques[] │                          │             │
│ - hp        │                          │ - hp        │
│ - defesa    │                          │ - defesa    │
└─────────────┘                          └─────────────┘
       │
       │
       ▼
┌─────────────┐
│   Batalha   │
│             │
│ - pokemon1  │
│ - pokemon2  │
│ - vencedor  │
└─────────────┘
```

---

## 💡 Dicas de Uso

1. **Sempre verifique se o ataque pode ser usado:**
   ```java
   if (pokemon.getAtaque(0).podeUsar()) {
       pokemon.usarAtaque(alvo, 0);
   }
   ```

2. **Você pode ter até 4 ataques por Pokémon:**
   ```java
   pokemon.adicionarAtaque(ataque1);
   pokemon.adicionarAtaque(ataque2);
   pokemon.adicionarAtaque(ataque3);
   pokemon.adicionarAtaque(ataque4);
   ```

3. **O dano é calculado automaticamente:**
   - Dano = Poder do Ataque - Defesa do Alvo
   - Dano mínimo sempre é 1

4. **O HP é atualizado automaticamente:**
   - Quando HP chega a 0, o Pokémon fica desmaiado

---

**Agora tudo está conectado! 🎮✨**

