package model.frames.dicionario;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends JButton implements TableCellRenderer {
    
    public ButtonRenderer() {
        setOpaque(true);
        setFont(new Font("Arial", Font.PLAIN, 11)); // Fonte menor
        setPreferredSize(new Dimension(50, 25)); // Botão menor
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        setText("Info");
        return this;
    }
}