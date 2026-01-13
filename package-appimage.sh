#!/bin/bash
# Script para criar AppImage do PokedexPoo

set -e

VERSION="1.0.0"
APP_NAME="PokedexPoo"
APP_DIR="AppDir"
BUILD_DIR="build"

echo "=== Criando AppImage ==="

# Limpar diretório anterior
rm -rf "$APP_DIR"

# Criar estrutura do AppDir
mkdir -p "$APP_DIR/usr/bin"
mkdir -p "$APP_DIR/usr/share/pokedexpoo"
mkdir -p "$APP_DIR/usr/share/applications"
mkdir -p "$APP_DIR/usr/share/pixmaps"
mkdir -p "$APP_DIR/opt/pokedexpoo/saves"

# Compilar o projeto se necessário
if [ ! -f "$BUILD_DIR/pokedexpoo.jar" ]; then
    echo "Compilando projeto..."
    ./build.sh
fi

# Copiar arquivos
echo "Copiando arquivos do aplicativo..."
cp "$BUILD_DIR/pokedexpoo.jar" "$APP_DIR/usr/share/pokedexpoo/"
cp -r src/model/frames/images "$APP_DIR/usr/share/pokedexpoo/" 2>/dev/null || true
cp -r saves "$APP_DIR/opt/pokedexpoo/" 2>/dev/null || true

# Criar script AppRun
cat > "$APP_DIR/AppRun" <<'EOF'
#!/bin/bash
HERE="$(dirname "$(readlink -f "${0}")")"
cd "$HERE/opt/pokedexpoo"

# Tentar usar Java do sistema
JAVA_CMD="java"

# Verificar se Java está disponível
if ! command -v java &> /dev/null; then
    # Tentar usar Java incluído no AppImage (se houver)
    if [ -f "$HERE/usr/bin/java" ]; then
        JAVA_CMD="$HERE/usr/bin/java"
    else
        # Mostrar erro e tentar abrir xdg-open para instalar Java
        zenity --error --text="Java não encontrado!\n\nPor favor, instale o Java Runtime Environment (JRE):\nsudo apt install default-jre" 2>/dev/null || \
        xmessage "Java não encontrado! Por favor, instale o JRE." 2>/dev/null || \
        echo "ERRO: Java não encontrado. Instale o JRE: sudo apt install default-jre"
        exit 1
    fi
fi

cd "$HERE/opt/pokedexpoo"
exec "$JAVA_CMD" -jar "$HERE/usr/share/pokedexpoo/pokedexpoo.jar" "$@"
EOF
chmod +x "$APP_DIR/AppRun"

# Criar arquivo .desktop
cat > "$APP_DIR/usr/share/applications/pokedexpoo.desktop" <<EOF
[Desktop Entry]
Version=1.0
Type=Application
Name=Pokedex POO
Comment=Sistema de Pokédex desenvolvido em Java
Exec=AppRun
Icon=pokedexpoo
Terminal=false
Categories=Game;Education;
EOF

# Copiar ícone
if [ -f "src/model/frames/images/FundosSimbolos/LogoInicio_400x.png" ]; then
    cp "src/model/frames/images/FundosSimbolos/LogoInicio_400x.png" "$APP_DIR/usr/share/pixmaps/pokedexpoo.png"
    cp "src/model/frames/images/FundosSimbolos/LogoInicio_400x.png" "$APP_DIR/pokedexpoo.png"
else
    echo "Aviso: Ícone não encontrado"
fi

# Baixar appimagetool se não existir
APPIMAGETOOL="appimagetool-x86_64.AppImage"
if [ ! -f "$APPIMAGETOOL" ]; then
    echo "Baixando appimagetool..."
    wget -q "https://github.com/AppImage/AppImageKit/releases/download/continuous/appimagetool-x86_64.AppImage" || {
        echo "Erro ao baixar appimagetool. Baixe manualmente de:"
        echo "https://github.com/AppImage/AppImageKit/releases"
        exit 1
    }
    chmod +x "$APPIMAGETOOL"
fi

# Criar AppImage
echo "Criando AppImage..."
ARCH=x86_64 ./"$APPIMAGETOOL" "$APP_DIR" "${APP_NAME}-${VERSION}-x86_64.AppImage"

echo "=== AppImage criado: ${APP_NAME}-${VERSION}-x86_64.AppImage ==="
echo "Para executar: ./${APP_NAME}-${VERSION}-x86_64.AppImage"
echo "Para tornar executável: chmod +x ${APP_NAME}-${VERSION}-x86_64.AppImage"
