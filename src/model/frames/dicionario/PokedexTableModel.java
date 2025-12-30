package model.frames.dicionario;

import model.pokedex.Pokedex;
import model.pokemon.Pokemon;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PokedexTableModel extends AbstractTableModel {

    private final String[] colunas = {
            "Nº", "Nome", "Tipo 1", "Tipo 2", "Info"
    };

    private List<Pokemon> pokemons;

    public PokedexTableModel(Pokedex pokedex) {
        this.pokemons = pokedex.listarTodos();
    }

    @Override
    public int getRowCount() {
        return pokemons.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        //if (columnIndex == 1) return Object.class; // imagem (vazia)
        if (columnIndex == 4) return JButton.class; // botão
        return String.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pokemon p = pokemons.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> String.valueOf(p.getNumeroPokedex());
            case 1 -> String.valueOf(p.getNome());
            case 2 -> p.getTipo1();
            case 3 -> p.getTipo2();
            case 4 -> "Info";
            default -> null;
        };
    }

    public Pokemon getPokemon(int index) {
        return pokemons.get(index);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 4; // só o botão é clicável
    }
}
