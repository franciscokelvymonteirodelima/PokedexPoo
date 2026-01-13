# Guia de Empacotamento - PokedexPoo

Este guia explica como criar pacotes instaláveis (.deb e .AppImage) para o projeto PokedexPoo.

## Pré-requisitos

### Para .deb:
- `dpkg-deb` (geralmente já instalado no Debian/Ubuntu)
- Java Runtime Environment (JRE) instalado no sistema

### Para .AppImage:
- `wget` (para baixar appimagetool)
- Conexão com internet (na primeira execução)

## Como Usar

### Opção 1: Script Interativo (Recomendado)

Execute o script principal que oferece um menu interativo:

```bash
./package.sh
```

Escolha entre:
1. Criar apenas pacote .deb
2. Criar apenas AppImage
3. Criar ambos

### Opção 2: Scripts Individuais

#### Criar pacote .deb:

```bash
./package-deb.sh
```

Isso criará um arquivo `pokedexpoo_1.0.0_all.deb` que pode ser instalado com:

```bash
sudo dpkg -i pokedexpoo_1.0.0_all.deb
```

Se houver problemas de dependências:

```bash
sudo apt-get install -f
```

#### Criar AppImage:

```bash
./package-appimage.sh
```

Isso criará um arquivo `PokedexPoo-1.0.0-x86_64.AppImage` que pode ser executado diretamente:

```bash
chmod +x PokedexPoo-1.0.0-x86_64.AppImage
./PokedexPoo-1.0.0-x86_64.AppImage
```

## Estrutura dos Pacotes

### Pacote .deb

O pacote .deb instala o aplicativo em:
- **Binário**: `/usr/bin/pokedexpoo`
- **Aplicativo**: `/usr/share/pokedexpoo/pokedexpoo.jar`
- **Dados**: `/opt/pokedexpoo/saves/`
- **Ícone**: `/usr/share/pixmaps/pokedexpoo.png`
- **Menu**: `/usr/share/applications/pokedexpoo.desktop`

### AppImage

O AppImage é um arquivo portável que contém:
- Aplicativo e todos os recursos
- Script de execução (AppRun)
- Arquivo .desktop para integração com o sistema

## Compilação Manual

Se precisar compilar apenas o projeto sem criar pacotes:

```bash
./build.sh
```

Isso criará:
- Classes compiladas em `bin/`
- JAR em `build/pokedexpoo.jar`

## Executar o JAR Manualmente

```bash
java -jar build/pokedexpoo.jar
```

## Desinstalar

### Pacote .deb:

```bash
sudo dpkg -r pokedexpoo
```

### AppImage:

Simplesmente delete o arquivo `.AppImage`.

## Solução de Problemas

### Erro: "Java não encontrado"

Instale o JRE:

```bash
sudo apt install default-jre
```

### Erro: "dpkg-deb: comando não encontrado"

Instale as ferramentas de empacotamento:

```bash
sudo apt install dpkg-dev
```

### Erro ao baixar appimagetool

Baixe manualmente de:
https://github.com/AppImage/AppImageKit/releases

E coloque no diretório do projeto como `appimagetool-x86_64.AppImage`.

### Problemas com permissões

Certifique-se de que os scripts são executáveis:

```bash
chmod +x *.sh
```

## Personalização

### Alterar versão

Edite os scripts `package-deb.sh` e `package-appimage.sh` e altere a variável `VERSION`.

### Alterar ícone

Coloque seu ícone em `src/model/frames/images/FundosSimbolos/LogoInicio_400x.png` ou edite os scripts para apontar para outro arquivo.

### Alterar dependências

Edite o arquivo `deb-package/DEBIAN/control` antes de construir o pacote, ou modifique o script `package-deb.sh`.

## Notas

- O AppImage requer Java instalado no sistema do usuário
- O pacote .deb instala o aplicativo no sistema
- Ambos os formatos preservam os saves em diretórios apropriados
- O AppImage é portável e não requer instalação
