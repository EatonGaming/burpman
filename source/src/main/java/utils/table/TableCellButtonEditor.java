package utils.table;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class TableCellButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private JButton button;

    @Override
    public Object getCellEditorValue() {
        return button;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        button = ((JButton) value);

        return button;
    }
}
