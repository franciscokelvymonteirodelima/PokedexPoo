
package model.frames.jogador;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        // Forçar carregamento da classe SistemaDeArquivos
        SistemaDeArquivos.existemSaves();

        SwingUtilities.invokeLater(() -> {
            TelaMenu telaMenu = new TelaMenu();
            telaMenu.setVisible(true);
        });
    }
}
