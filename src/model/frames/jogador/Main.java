

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            TelaMenu telaMenu = new TelaMenu();
            telaMenu.setVisible(true);
        });
    }
}
