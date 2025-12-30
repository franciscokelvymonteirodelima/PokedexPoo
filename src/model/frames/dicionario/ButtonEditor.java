package model.frames.dicionario;

import model.pokemon.Pokemon;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends AbstractCellEditor
        implements TableCellEditor, ActionListener {

    private JButton button;
    private JTable table;

    public ButtonEditor(JTable table) {
        this.table = table;
        button = new JButton("Info");
        button.addActionListener(this);
    }

    @Override
    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column) {
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "Info";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int row = table.convertRowIndexToModel(table.getSelectedRow());
        PokedexTableModel model = (PokedexTableModel) table.getModel();
        Pokemon p = model.getPokemon(row);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(table);
        TelaDetalhesPokemon dialog = new TelaDetalhesPokemon(frame, p);
        dialog.setVisible(true);

        fireEditingStopped(); // CHAMAR UMA ÚNICA VEZ
    }
}
