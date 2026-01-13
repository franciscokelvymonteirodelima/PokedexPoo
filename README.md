# Pokédex POO

## Descrição

Projeto acadêmico desenvolvido como prática de Programação Orientada a Objetos em Java. O sistema simula uma Pokédex completa, incluindo modelos de Pokémon, tipos, atributos, ataques, batalhas, regras de combate e armazenamento de dados. O projeto demonstra conceitos fundamentais de POO como encapsulamento, herança, polimorfismo e abstração através de uma aplicação interativa.

## Funcionalidades Principais

- **Gerenciamento de Pokémon**: Criação e manipulação de Pokémon com atributos como HP, ataque, defesa, velocidade, etc.
- **Sistema de Tipos**: Implementação de tipos de Pokémon com vantagens e desvantagens.
- **Ataques e Efeitos**: Sistema de ataques com efeitos de status.
- **Batalhas**: Simulação de batalhas entre Pokémon com regras de comparação e logs.
- **Interface Gráfica**: Telas interativas para navegação na Pokédex, batalhas, gerenciamento de jogador, loja e jogos.
- **Sistema de Salvamento**: Persistência de dados de jogadores e progressão.
- **Mini-jogos**: Funcionalidades como "Qual é Esse Pokémon?" e ranking.

## Estrutura do Projeto

```
src/
├── model/
│   ├── arquivo/          # Gerenciamento de arquivos e saves
│   ├── ataques/          # Classes relacionadas a ataques
│   ├── batalha/          # Lógica de batalhas e comparações
│   ├── exceptions/       # Exceções personalizadas
│   ├── frames/           # Interfaces gráficas (Swing)
│   │   ├── batalha/      # Telas de batalha
│   │   ├── dicionario/   # Pokédex e detalhes de Pokémon
│   │   ├── images/       # Recursos visuais
│   │   ├── inicio/       # Tela inicial e menu
│   │   ├── jogador/      # Gerenciamento de perfil do jogador
│   │   ├── loja/         # Sistema de loja
│   │   ├── qualEhEssePokemon/  # Mini-jogo
│   │   └── ranking/      # Sistema de ranking
│   ├── jogador/          # Classes do jogador e inventário
│   ├── pokedex/          # Classe principal da Pokédex
│   └── pokemon/          # Classes de Pokémon, atributos e tipos
```

## Tecnologias Utilizadas

- **Linguagem**: Java
- **Interface Gráfica**: Swing
- **Paradigma**: Programação Orientada a Objetos
- **IDE**: IntelliJ IDEA (baseado no arquivo .iml)

## Pré-requisitos

- Java Development Kit (JDK) 8 ou superior
- Ambiente de desenvolvimento Java configurado

## Como Executar

1. **Clonar o repositório** (se aplicável):
   ```
   git clone <url-do-repositorio>
   cd PokedexPoo
   ```

2. **Compilar o projeto**:
   ```
   javac -d bin -cp src src/model/frames/inicio/*.java src/model/frames/dicionario/*.java src/model/frames/jogador/*.java src/model/frames/loja/*.java src/model/frames/batalha/*.java src/model/frames/qualEhEssePokemon/*.java src/model/frames/ranking/*.java src/model/**/*.java
   ```

3. **Executar a aplicação**:
   ```
   java -cp bin model.frames.inicio.mainMenu
   ```
   *(A classe principal é mainMenu.java que inicializa a interface FInicio)*

## Arquivos de Configuração

- `PokedexPoo.iml`: Arquivo de configuração do IntelliJ IDEA
- `saves/`: Diretório para arquivos de salvamento de jogos

## Contribuição

Este é um projeto acadêmico para fins educacionais. Para sugestões ou melhorias, entre em contato com o desenvolvedor.

## Licença

Este projeto é distribuído sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.

## Autor

Desenvolvido como parte de um projeto acadêmico em Programação Orientada a Objetos.