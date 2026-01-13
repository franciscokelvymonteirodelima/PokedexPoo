#!/bin/bash
# Script principal para empacotar PokedexPoo

set -e

echo "=== Empacotador PokedexPoo ==="
echo ""
echo "Escolha o formato de pacote:"
echo "1) .deb (Debian/Ubuntu)"
echo "2) .AppImage (Portável)"
echo "3) Ambos"
echo ""
read -p "Digite sua escolha (1-3): " choice

case $choice in
    1)
        echo "Criando pacote .deb..."
        ./package-deb.sh
        ;;
    2)
        echo "Criando AppImage..."
        ./package-appimage.sh
        ;;
    3)
        echo "Criando ambos os pacotes..."
        ./package-deb.sh
        ./package-appimage.sh
        ;;
    *)
        echo "Opção inválida!"
        exit 1
        ;;
esac

echo ""
echo "=== Empacotamento concluído! ==="
