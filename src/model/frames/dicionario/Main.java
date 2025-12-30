package model.frames.dicionario;

import model.frames.dicionario.TelaPokedex;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaPokedex().setVisible(true);
        });
    }
}
