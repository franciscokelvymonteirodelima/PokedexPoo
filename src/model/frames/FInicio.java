package model.frames;
import javax.swing.*;
import java.awt.*;

public class FInicio extends JFrame{
    public FInicio(String title) {
        super(title);
        JPanel pane = new JPanel();
        JButton botao = new JButton ("Texto botao");
        JTextArea textArea = new JTextArea();
        pane.add(botao);
        pane.add(textArea);

        JRadioButton apcaoA = new JRadioButton("Opção A");
        apcaoA.setSelected(true);
        JRadioButton apcaoB = new JRadioButton("Opção B");
        //Agrupa radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(apcaoA);
        group.add(apcaoB);

        pane.add(apcaoA);
        pane.add(apcaoB);

        setContentPane(pane); // Adiciona o conteudo ao painel

        setVisible(true);
    }
    public static void main(String[] args) {
        FInicio teste = new FInicio("MinhaJanela");
    }
}

