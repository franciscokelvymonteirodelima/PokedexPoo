package model.frames;

import javax.swing.*;
import java.awt.*;

public class FWhosThatPoke extends JFrame {

	public FWhosThatPoke() {
		setTitle("Who's That Pokémon?");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		initComponents();
	}

	private void initComponents() {
		JLabel label = new JLabel("Who's that Pokémon?");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		// aumentar tamanho e aplicar negrito
		label.setFont(label.getFont().deriveFont(Font.BOLD, 28f));
		add(label, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            FWhosThatPoke frame = new FWhosThatPoke();
			frame.setVisible(true);
		});
	}
}