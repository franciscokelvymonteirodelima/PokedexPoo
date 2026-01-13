# 🎁 Guia Rápido de Empacotamento

## ⚡ Início Rápido

Para criar pacotes instaláveis do PokedexPoo, execute:

```bash
./package.sh
```

Escolha a opção desejada e os pacotes serão criados automaticamente!

## 📦 Formatos Disponíveis

### 1. Pacote .deb (Debian/Ubuntu)
- **Instalação no sistema**
- **Integração com menu de aplicativos**
- **Gerenciamento via apt/dpkg**

**Criar:**
```bash
./package-deb.sh
```

**Instalar:**
```bash
sudo dpkg -i pokedexpoo_1.0.0_all.deb
sudo apt-get install -f  # Se houver dependências faltando
```

**Desinstalar:**
```bash
sudo dpkg -r pokedexpoo
```

### 2. AppImage (Portável)
- **Não requer instalação**
- **Portável entre sistemas Linux**
- **Executável diretamente**

**Criar:**
```bash
./package-appimage.sh
```

**Executar:**
```bash
chmod +x PokedexPoo-1.0.0-x86_64.AppImage
./PokedexPoo-1.0.0-x86_64.AppImage
```

## 🔧 Requisitos

- **Java JDK** (para compilar)
- **Java JRE** (para executar - pode ser instalado pelo usuário)
- **dpkg-deb** (para .deb - geralmente já instalado)
- **wget** (para AppImage - baixa appimagetool automaticamente)

## 📋 Estrutura Criada

Após executar os scripts, você terá:

```
PokedexPoo/
├── build/
│   └── pokedexpoo.jar          # JAR compilado
├── bin/                         # Classes compiladas
├── pokedexpoo_1.0.0_all.deb    # Pacote .deb (se criado)
└── PokedexPoo-1.0.0-x86_64.AppImage  # AppImage (se criado)
```

## 🐛 Solução de Problemas

### "Java não encontrado"
```bash
sudo apt install default-jre
```

### "dpkg-deb: comando não encontrado"
```bash
sudo apt install dpkg-dev
```

### Erro ao compilar
Certifique-se de que todos os arquivos Java estão em `src/` e execute:
```bash
./build.sh
```

### Imagens não aparecem
As imagens devem estar em `src/model/frames/images/`. O script de build as copia automaticamente para o JAR.

## 📝 Notas Importantes

1. **Primeira execução do AppImage**: Pode demorar um pouco ao baixar o appimagetool
2. **Permissões**: Os scripts precisam ter permissão de execução (`chmod +x`)
3. **Saves**: Os arquivos de save são preservados em `/opt/pokedexpoo/saves/` (.deb) ou no diretório do AppImage
4. **Versão**: Para alterar a versão, edite as variáveis `VERSION` nos scripts

## 🚀 Próximos Passos

1. Teste o pacote .deb em uma máquina limpa
2. Teste o AppImage em diferentes distribuições Linux
3. Considere adicionar um ícone personalizado
4. Atualize a versão conforme necessário

---

**Dúvidas?** Consulte `README_PACKAGING.md` para informações detalhadas.
