package model.frames.dicionario;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TipoRenderer extends DefaultTableCellRenderer {
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
                                                   boolean isSelected, boolean hasFocus, 
                                                   int row, int column) {
        
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        if (value != null) {
            String tipo = value.toString();
            Color corFundo = getCorPorTipo(tipo);
            Color corTexto = Color.WHITE;
            
            // Se o tipo for vazio ("-"), usar fundo cinza claro e texto cinza
            if (tipo.equals("-") || tipo.trim().isEmpty()) {
                corFundo = new Color(240, 240, 240);
                corTexto = Color.GRAY;
            }
            
            cell.setBackground(corFundo);
            cell.setForeground(corTexto);
            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(new Font("Arial", Font.BOLD, 12));
        }
        
        return cell;
    }
    
    private Color getCorPorTipo(String tipo) {
        switch (tipo.toLowerCase()) {
            case "normal":
                return new Color(168, 168, 120);
            case "fire":
                return new Color(240, 128, 48);
            case "water":
                return new Color(104, 144, 240);
            case "electric":
                return new Color(248, 208, 48);
            case "grass":
                return new Color(120, 200, 80);
            case "ice":
                return new Color(152, 216, 216);
            case "fighting":
                return new Color(192, 48, 40);
            case "poison":
                return new Color(160, 64, 160);
            case "ground":
                return new Color(224, 192, 104);
            case "flying":
                return new Color(168, 144, 240);
            case "psychic":
                return new Color(248, 88, 136);
            case "bug":
                return new Color(168, 184, 32);
            case "rock":
                return new Color(184, 160, 56);
            case "ghost":
                return new Color(112, 88, 152);
            case "dragon":
                return new Color(112, 56, 248);
            case "dark":
                return new Color(112, 88, 72);
            case "steel":
                return new Color(184, 184, 208);
            case "fairy":
                return new Color(238, 153, 172);
            default:
                return new Color(200, 200, 200);
        }
    }
}