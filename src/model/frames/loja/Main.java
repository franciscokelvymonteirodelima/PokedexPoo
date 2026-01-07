package model.frames.loja;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Sem definir LookAndFeel, o Java usa o padrão "Metal" ou o padrão do sistema
        SwingUtilities.invokeLater(() -> {
            new TelaMenu().setVisible(true);
        });
    }
}