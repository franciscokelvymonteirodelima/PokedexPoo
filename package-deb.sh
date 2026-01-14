#!/bin/bash
# Script para criar pacote .deb do PokedexPoo

set -e

VERSION="1.0.0"
PACKAGE_NAME="pokedexpoo"
DEB_DIR="deb-package"
BUILD_DIR="build"

echo "=== Criando pacote .deb ==="

# Limpar diretório anterior
rm -rf "$DEB_DIR"

# Criar estrutura do pacote
mkdir -p "$DEB_DIR/DEBIAN"
mkdir -p "$DEB_DIR/usr/bin"
mkdir -p "$DEB_DIR/usr/share/pokedexpoo"
mkdir -p "$DEB_DIR/usr/share/applications"
mkdir -p "$DEB_DIR/usr/share/pixmaps"
mkdir -p "$DEB_DIR/opt/pokedexpoo/saves"

# Compilar o projeto se necessário
if [ ! -f "$BUILD_DIR/pokedexpoo.jar" ]; then
    echo "Compilando projeto..."
    ./build.sh
fi

# Copiar arquivos
echo "Copiando arquivos do aplicativo..."
cp "$BUILD_DIR/pokedexpoo.jar" "$DEB_DIR/usr/share/pokedexpoo/"
cp -r src/model/frames/images "$DEB_DIR/usr/share/pokedexpoo/" 2>/dev/null || true
cp -r saves "$DEB_DIR/opt/pokedexpoo/" 2>/dev/null || true

# Criar script de execução
cat > "$DEB_DIR/usr/bin/pokedexpoo" <<'EOF'
#!/bin/bash
cd /opt/pokedexpoo
export JAVA_HOME="${JAVA_HOME:-/usr/lib/jvm/default-java}"
java -jar /usr/share/pokedexpoo/pokedexpoo.jar "$@"
EOF
chmod +x "$DEB_DIR/usr/bin/pokedexpoo"

# Criar arquivo .desktop
cat > "$DEB_DIR/usr/share/applications/pokedexpoo.desktop" <<EOF
[Desktop Entry]
Version=1.0
Type=Application
Name=Pokedex POO
Comment=Sistema de Pokédex desenvolvido em Java
Exec=/usr/bin/pokedexpoo
Icon=pokedexpoo
Terminal=false
Categories=Game;Education;
EOF

# Copiar ícone se existir (ou criar um placeholder)
if [ -f "src/model/frames/images/FundosSimbolos/LogoInicio_400x.png" ]; then
    cp "src/model/frames/images/FundosSimbolos/LogoInicio_400x.png" "$DEB_DIR/usr/share/pixmaps/pokedexpoo.png"
else
    # Criar ícone placeholder simples
    echo "Ícone não encontrado, usando placeholder"
fi

# Criar arquivo de controle
cat > "$DEB_DIR/DEBIAN/control" <<EOF
Package: $PACKAGE_NAME
Version: $VERSION
Section: games
Priority: optional
Architecture: all
Depends: default-jre | openjdk-11-jre | openjdk-17-jre | openjdk-21-jre
Maintainer: PokedexPoo Team <pokedexpoo@example.com>
Description: Sistema de Pokédex desenvolvido em Java
 Sistema interativo de Pokédex desenvolvido como projeto acadêmico
 em Programação Orientada a Objetos. Inclui gerenciamento de Pokémon,
 batalhas, mini-jogos e interface gráfica completa.
EOF

# Criar script postinst
cat > "$DEB_DIR/DEBIAN/postinst" <<'EOF'
#!/bin/bash
set -e
chmod +x /usr/bin/pokedexpoo
chmod -R 755 /opt/pokedexpoo
update-desktop-database 2>/dev/null || true
exit 0
EOF
chmod +x "$DEB_DIR/DEBIAN/postinst"

# Criar script prerm
cat > "$DEB_DIR/DEBIAN/prerm" <<'EOF'
#!/bin/bash
set -e
exit 0
EOF
chmod +x "$DEB_DIR/DEBIAN/prerm"

# Criar script postrm
cat > "$DEB_DIR/DEBIAN/postrm" <<'EOF'
#!/bin/bash
set -e
update-desktop-database 2>/dev/null || true
exit 0
EOF
chmod +x "$DEB_DIR/DEBIAN/postrm"

# Construir o pacote
echo "Construindo pacote .deb..."
dpkg-deb --build "$DEB_DIR" "${PACKAGE_NAME}_${VERSION}_all.deb"

echo "=== Pacote .deb criado: ${PACKAGE_NAME}_${VERSION}_all.deb ==="
echo "Para instalar: sudo dpkg -i ${PACKAGE_NAME}_${VERSION}_all.deb"
echo "Para corrigir dependências: sudo apt-get install -f"
