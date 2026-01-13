#!/bin/bash
# Script de build para compilar o projeto PokedexPoo

set -e

echo "=== Compilando PokedexPoo ==="

# Limpar compilações anteriores
echo "Limpando compilações anteriores..."
rm -rf bin
rm -rf build
mkdir -p bin
mkdir -p build

# Compilar todos os arquivos Java
echo "Compilando arquivos Java..."
find src -name "*.java" > sources.txt
javac -d bin -encoding UTF-8 @sources.txt
rm sources.txt

# Copiar recursos (imagens, etc)
echo "Copiando recursos..."
mkdir -p bin/model/frames/images
cp -r src/model/frames/images/* bin/model/frames/images/ 2>/dev/null || true

# Criar MANIFEST.MF se não existir
if [ ! -f MANIFEST.MF ]; then
    cat > MANIFEST.MF <<EOF
Manifest-Version: 1.0
Main-Class: model.frames.inicio.mainMenu
Class-Path: .
EOF
fi

# Criar JAR
echo "Criando JAR..."
jar cfm build/pokedexpoo.jar MANIFEST.MF -C bin .

echo "=== Build concluído! ==="
echo "Arquivos compilados em: bin/"
echo "JAR criado em: build/pokedexpoo.jar"
