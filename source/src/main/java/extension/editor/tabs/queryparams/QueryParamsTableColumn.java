package extension.editor.tabs.queryparams;

import extension.request.QueryParameter;
import utils.table.TableCellButtonEditor;
import utils.table.TableCellButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;

public enum QueryParamsTableColumn {
    UNKNOWN(0, -1, "", String.class, false, x -> null, (x, y) -> {}, 0, new DefaultTableCellRenderer(), new DefaultCellEditor(new JTextField())),
    ENABLED(1, 0, "Enabled", Boolean.class, true, QueryParameter::isEnabled, (x, y) -> x.setEnabled((Boolean) y), 0.15f, new DefaultTableCellRenderer(), new DefaultCellEditor(new JTextField())),
    NAME(2, 1, "Name", String.class, true, QueryParameter::getName, (x, y) -> x.setName((String) y), 0.35f, new DefaultTableCellRenderer(), new DefaultCellEditor(new JTextField())),
    VALUE(3, 2, "Value", String.class, true, QueryParameter::getValue, (x, y) -> x.setValue((String) y), 0.35f, new DefaultTableCellRenderer(), new DefaultCellEditor(new JTextField())),
    DELETE(4, 3, "Delete", JButton.class, true, QueryParameter::getDeleteLabel, (x, y) -> {}, 0.15f, new TableCellButtonRenderer(), new TableCellButtonEditor());

    public final int id;
    public final int positionSortIndex;
    public final String columnName;
    public final Class<?> columnClass;
    public final boolean columnEditable;
    public final Function<QueryParameter, Object> getColumnValueFunction;
    public final BiConsumer<QueryParameter, Object> setColumnValueConsumer;
    public final float columnWidthPercentage;
    public final TableCellRenderer cellRenderer;
    public final TableCellEditor cellEditor;

    QueryParamsTableColumn(int id, int positionSortIndex, String columnHeaderText, Class<?> columnClass, boolean columnEditable, Function<QueryParameter, Object> getColumnValueFunction, BiConsumer<QueryParameter, Object> setColumnValueConsumer, float columnWidthPercentage, TableCellRenderer cellRenderer, TableCellEditor cellEditor) {
        this.id = id;
        this.positionSortIndex = positionSortIndex;
        this.columnName = columnHeaderText;
        this.columnClass = columnClass;
        this.columnEditable = columnEditable;
        this.getColumnValueFunction = getColumnValueFunction;
        this.setColumnValueConsumer = setColumnValueConsumer;
        this.columnWidthPercentage = columnWidthPercentage;
        this.cellRenderer = cellRenderer;
        this.cellEditor = cellEditor;
    }

    public static List<QueryParamsTableColumn> queryParamsTableColumnsInOrder()
    {
        return Arrays.stream(QueryParamsTableColumn.values())
                .filter(x -> x != UNKNOWN)
                .sorted(comparingInt(x -> x.positionSortIndex))
                .collect(Collectors.toList());
    }
}
