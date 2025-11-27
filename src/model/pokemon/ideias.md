# 🧬 **1. ATRIBUTOS ESSENCIAIS PARA A CLASSE `Pokemon`**

Esses são praticamente obrigatórios para um jogo, e ajudam muito na POO:

### 🟥 **Atributos básicos**
- `String nome`
- `int numeroPokedex`
- `TipoPokemon tipo1`
- `TipoPokemon tipo2` *(opcional)*

### 🟦 **Atributos de status (stats)**
- `int nivel`
- `int experiencia`
- `int vidaMaxima`
- `int vidaAtual`
- `int ataque`
- `int defesa`
- `int velocidade`

Esses permitem criar mecânica de batalha.

### 🟩 **Atribututos de controle**
- `boolean desmaiado`
- `boolean capturado`

---

# 🧱 **2. ATRIBUTOS PARA HERANÇA (obrigatório para o trabalho)**

> A classe **Pokémon pode ser abstrata**, e as subclasses (`PokemonFogo`, `PokemonAgua`) podem redefinir estes atributos.

- `double multiplicadorDano`
- `String vantagemContra`
- `String fraquezaContra`
- `String somCaracteristico` *(cada tipo pode sobrescrever)*

---

# 🎮 **3. ATRIBUTOS DE COMBATE (ótimo para o jogo)**

- `List<Ataque> ataques`
- `Ataque ataqueEspecial`
- `double chanceCritico`
- `double bonusSTAB` *(tipo do ataque igual ao tipo do Pokémon)*
- `EfeitoStatus statusAtual` → (Paralisado, Envenenado etc.)

---

# 🎒 **4. ATRIBUTOS DE EVOLUÇÃO**

Se quiser uma mecânica legal:

- `int nivelEvolucao`
- `String nomeEvolucao`
- `boolean podeEvoluir`
- `Pokemon evolucao` *(pode ser uma subclasse)*

---

# 🌟 **5. ATRIBUTOS DE PERSONALIDADE / EXTRAS**

Esses são opcionais, mas deixam o jogo mais completo:

- `String descricao`
- `double altura`
- `double peso`
- `String categoria` (ex.: Pokémon Lagarto)
- `String habitat`
- `String cor`
- `String genero` (M/F/Indefinido)

---

# 🗃️ **6. ATRIBUTOS DE CLASSE (static)**

> O professor pediu **atributo de classe**, então você DEVE colocar pelo menos um.

Exemplos:
- `static int quantidadeCriados`
- `static int totalPokemonsVivos`
- `static String criadorDoJogo = "Equipe 13"`

---

# 🔁 **7. MÉTODOS QUE A CLASSE PODE TER (lista completa)**

*(Você não pediu, mas isso te ajuda MUITO.)*

### 📌 Construtores
- construtor vazio  
- construtor com nome + nível  
- construtor completo (nome, tipo, stats)

### 📌 Métodos abstratos (para polimorfismo)
- `public abstract void atacar(Pokemon alvo);`
- `public abstract String getSom();`

### 📌 Métodos comuns
- `subirNivel()`
- `receberDano(int qtd)`
- `curar()`
- `adicionarXP(int qtd)`
- `evoluir()`
- `isDesmaiado()`

### 📌 Sobrecarga (obrigatório)
- `atacar()`
- `atacar(Pokemon alvo)`
- `atacar(Pokemon alvo, Ataque ataqueEspecial)`

### 📌 Sobrescrita (obrigatório)
- `toString()`
- `calcularDano()` (diferente para cada tipo)

---

# ✔️ **8. SUGESTÃO DE CLASSE `Pokemon` PERFEITA PARA O TRABALHO**

Aqui está uma lista final que garante **nota máxima** e cobre tudo exigido pelo professor:

### 🧩 **Atributos recomendados para sua classe `Pokemon`:**
- `String nome`
- `int numeroPokedex`
- `TipoPokemon tipo1`
- `TipoPokemon tipo2`
- `int nivel`
- `int experiencia`
- `int vidaMaxima`
- `int vidaAtual`
- `int ataque`
- `int defesa`
- `int velocidade`
- `List<Ataque> ataques`
- `boolean desmaiado`
- `int nivelEvolucao`
- `String nomeEvolucao`
- `Pokemon evolucao` *(se quiser)*
- `String descricao`
- `static int totalPokemonsCriados`  ← **atributo de classe (obrigatório)**

---

# 🎯 Se quiser, posso fazer:
- o **diagrama UML completo**
- a **estrutura final das classes**
- as **subclasses**: `PokemonFogo`, `PokemonAgua`, `PokemonPlanta`
- o **design da classe Ataque**
- a **estrutura da batalha**
- o **modelo completo do jogo para Eclipse** (zip)

É só dizer até onde quer ir!
