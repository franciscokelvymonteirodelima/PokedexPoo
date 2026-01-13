# Relatório do Projeto: Pokédex POO

## Universidade Federal do Ceará – Campus Quixadá
**Curso de Ciência da Computação**  
**Disciplina: Programação Orientada a Objetos**  
**Professor: Enyo Gonçalves**  
**Período: 2025.2**  
**Trabalho Final**  

---

## 1. Introdução

Este relatório descreve o desenvolvimento do sistema "Pokédex POO", um projeto acadêmico implementado em Java como prática dos conceitos de Programação Orientada a Objetos (POO). O sistema simula uma Pokédex interativa, permitindo aos usuários gerenciar Pokémon, realizar batalhas, navegar em um dicionário de Pokémon e participar de mini-jogos, tudo enquanto demonstra os princípios fundamentais da POO.

O projeto foi desenvolvido por uma equipe de estudantes, com o objetivo de aplicar todos os conceitos abordados na disciplina, incluindo herança, classes abstratas, polimorfismo, encapsulamento, tratamento de exceções, manipulação de arquivos e interface gráfica.

## 2. Objetivos do Projeto

- Implementar um sistema orientado a objetos que utilize todos os conceitos de POO exigidos pela disciplina.
- Criar uma aplicação funcional e interativa que simule uma Pokédex real.
- Demonstrar boas práticas de programação, como modularidade, reutilização de código e tratamento de erros.
- Desenvolver uma interface gráfica amigável para interação com o usuário.

## 3. Análise dos Requisitos de POO

O sistema foi desenvolvido atendendo a todos os requisitos especificados no enunciado da disciplina. A seguir, detalhamos como cada conceito foi implementado:

### 3.1 Hierarquia com Herança

O projeto utiliza herança para criar uma hierarquia de classes relacionadas. Por exemplo:

- **Classe Abstrata `RegrasComparacao`**: Define métodos abstratos e concretos para cálculos de batalhas.
- **Classe `Comparacao`**: Herda de `RegrasComparacao` e implementa a lógica específica de comparação entre Pokémon.

```java
public abstract class RegrasComparacao {
    public double calcularMediaStatus(Pokemon pokemon) {
        // Implementação do cálculo
    }
}

public class Comparacao extends RegrasComparacao {
    // Implementação específica
}
```

### 3.2 Classes Abstratas

A classe `RegrasComparacao` é definida como abstrata, fornecendo uma base para implementações específicas de regras de batalha, promovendo reutilização de código e definindo contratos para subclasses.

### 3.3 Sobrescrita de Método

Métodos são sobrescritos em subclasses para fornecer comportamentos específicos. Por exemplo, em classes de interface gráfica, métodos como `actionPerformed` são sobrescritos para lidar com eventos de usuário.

### 3.4 Sobrecarga de Método

Construtores e métodos são sobrecarregados para permitir diferentes formas de inicialização. Na classe `Pokemon`, há múltiplos construtores para diferentes cenários de criação.

```java
public Pokemon(String nome, int numeroPokedex, int nivel, String tipo1, String tipo2, ...) {
    // Construtor completo
}

public Pokemon(String nome, int numeroPokedex, String tipo1, ...) {
    // Construtor sobrecarregado
}
```

### 3.5 Construtores

Cada classe possui construtores apropriados para inicializar objetos. Por exemplo, o construtor da classe `Jogador` inicializa atributos básicos e listas vazias.

### 3.6 Encapsulamento

O encapsulamento é implementado através de modificadores de acesso. Atributos são privados e acessados via getters e setters. Por exemplo, na classe `Pokemon`:

```java
private String nome;
private int hp;

public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}
```

### 3.7 Atributo de Classe

Atributos estáticos são utilizados para valores compartilhados. Por exemplo, constantes como tipos de Pokémon ou efeitos de status são definidos como atributos de classe.

### 3.8 Polimorfismo

O polimorfismo é demonstrado através de referências de superclasses e interfaces. Por exemplo, listas de `Pokemon` podem conter diferentes tipos de Pokémon, e métodos são chamados polimorficamente.

### 3.9 Manipulação de Vetor ou Coleções

O projeto utiliza coleções do Java Collections Framework extensivamente:

- `ArrayList<Pokemon>` para listas de Pokémon.
- `HashSet<Integer>` para colecionáveis únicos.
- Métodos como `add()`, `remove()`, `contains()` são utilizados para manipulação.

### 3.10 Interface Gráfica com o Usuário

A interface gráfica é implementada utilizando Swing. Classes como `FInicio`, `FPokedex`, `FBatalha` criam janelas, botões, tabelas e painéis interativos.

### 3.11 Tratamento de Exceção

Exceções personalizadas são definidas e tratadas:

- `BatalhaException`: Para erros durante batalhas.
- `PokemonNaoExisteExceptions`: Para Pokémon não encontrados.
- `SaveException`: Para erros de salvamento.

Blocos try-catch são utilizados em operações críticas, como leitura de arquivos.

### 3.12 Manipulação de Arquivos

O sistema inclui classes para persistência de dados:

- `GerenciadorArquivos`: Para operações básicas de arquivo.
- `LeitorArquivosSaveGame`: Para carregar e salvar estados de jogo.
- Arquivos são salvos em formato texto no diretório `saves/`.

## 4. Estrutura do Sistema

### 4.1 Diagrama de Classes

```
model/
├── pokemon/
│   ├── Pokemon.java
│   ├── TipoPokemon.java
│   └── Atributos.java
├── ataques/
│   ├── Ataque.java
│   └── EfeitoStatus.java (enum)
├── batalha/
│   ├── RegrasComparacao.java (abstrata)
│   ├── Comparacao.java
│   ├── LogsComparacao.java
│   └── BatalhaException.java
├── jogador/
│   ├── Jogador.java
│   └── Inventario.java
├── pokedex/
│   └── Pokedex.java
├── arquivo/
│   ├── GerenciadorArquivos.java
│   ├── LeitorArquivosSaveGame.java
│   └── SaveException.java
└── frames/ (Interface Gráfica)
    ├── inicio/
    ├── dicionario/
    ├── batalha/
    ├── jogador/
    ├── loja/
    ├── qualEhEssePokemon/
    └── ranking/
```

### 4.2 Classes Principais

- **Pokemon**: Representa um Pokémon com atributos, tipos e ataques.
- **Pokedex**: Gerencia a coleção de Pokémon, favoritos e capturados.
- **Jogador**: Representa o usuário, com time de Pokémon, inventário e estatísticas.
- **Ataque**: Define ataques com poder, precisão e efeitos.
- **Comparacao**: Implementa lógica de batalha entre Pokémon.

## 5. Funcionalidades Implementadas

1. **Navegação na Pokédex**: Visualização de todos os Pokémon, detalhes individuais, busca e filtros.
2. **Sistema de Jogador**: Criação de perfil, gerenciamento de time e inventário.
3. **Batalhas**: Comparação automática entre Pokémon baseada em atributos.
4. **Sistema de Salvamento**: Persistência de progresso em arquivos.
5. **Mini-jogos**: "Qual é Esse Pokémon?" e sistema de ranking.
6. **Loja**: Compra de itens colecionáveis.
7. **Interface Gráfica Completa**: Navegação intuitiva entre todas as funcionalidades.

## 6. Tecnologias e Ferramentas Utilizadas

- **Linguagem**: Java 8+
- **Paradigma**: Programação Orientada a Objetos
- **Interface Gráfica**: Java Swing
- **Coleções**: Java Collections Framework
- **IDE**: IntelliJ IDEA
- **Controle de Versão**: Git (recomendado)

## 7. Testes e Validação

O sistema foi testado para garantir que todos os conceitos de POO fossem corretamente implementados e que a aplicação funcionasse sem erros críticos. Casos de teste incluíram:

- Criação e manipulação de objetos Pokémon.
- Simulação de batalhas.
- Salvamento e carregamento de jogos.
- Navegação na interface gráfica.

## 8. Conclusão

O projeto "Pokédex POO" demonstrou com sucesso a aplicação prática de todos os conceitos de Programação Orientada a Objetos exigidos pela disciplina. Através da implementação de um sistema complexo e funcional, os estudantes consolidaram o conhecimento teórico adquirido, desenvolvendo habilidades em design orientado a objetos, modularidade e manutenção de código.

O sistema não apenas atende aos requisitos acadêmicos, mas também resulta em uma aplicação completa e utilizável, demonstrando o potencial da POO para desenvolvimento de software real.

## 9. Referências

- Documentação Oficial do Java: https://docs.oracle.com/javase/
- Oracle. (2023). The Java™ Tutorials. Disponível em: https://docs.oracle.com/javase/tutorial/
- Deitel, P. J., & Deitel, H. M. (2018). Java: Como Programar. Pearson.

---

**Equipe de Desenvolvimento:**  
[Kelvy lima, João Eudes, Luiz Guilherme]  

**Data de Entrega:** 14 de janeiro de 2026