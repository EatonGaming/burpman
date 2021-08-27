package extension.editor.tabs.queryparams;

import extension.request.QueryParameter;
import extension.request.QueryParameters;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Optional;

import static extension.editor.tabs.queryparams.QueryParamsTableColumn.UNKNOWN;
import static extension.editor.tabs.queryparams.QueryParamsTableColumn.queryParamsTableColumnsInOrder;
import static javax.swing.SwingUtilities.invokeLater;

public class QueryParamsTableModel extends AbstractTableModel {
    private static final List<QueryParamsTableColumn> COLUMNS_IN_ORDER = queryParamsTableColumnsInOrder();

    private final QueryParameters queryParameters;

    public QueryParamsTableModel(QueryParameters queryParameters) {
        this.queryParameters = queryParameters;
    }

    void addParameter(String name, String value)
    {
        int insertedRowIndex = queryParameters.getNumberOfParameters();

        queryParameters.addParameter(name, value);

        fireTableRowsInserted(insertedRowIndex, insertedRowIndex);
    }

    void removeParameters(int[] rowIndicesToRemove)
    {
        List<Integer> rowIndicesRemoved = queryParameters.remove(rowIndicesToRemove);

        if (!rowIndicesRemoved.isEmpty())
        {
            int startRowIndexDeleted = rowIndicesRemoved.stream().min(Integer::compareTo).orElse(-1);
            int endRowIndexDeleted = rowIndicesRemoved.stream().max(Integer::compareTo).orElse(-1);

            invokeLater(() -> fireTableRowsDeleted(startRowIndexDeleted, endRowIndexDeleted));
        }
    }

    @Override
    public int getRowCount() {
        return queryParameters.getNumberOfParameters();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS_IN_ORDER.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        QueryParamsTableColumn tableColumn = getColumnAtIndex(columnIndex);

        return tableColumn.columnName;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        QueryParamsTableColumn tableColumn = getColumnAtIndex(columnIndex);

        return tableColumn.columnClass;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        QueryParamsTableColumn tableColumn = getColumnAtIndex(columnIndex);

        return tableColumn.columnEditable;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        QueryParamsTableColumn tableColumn = getColumnAtIndex(columnIndex);
        QueryParameter queryParameter = queryParameters.getQueryParameter(rowIndex);

        return tableColumn.getColumnValueFunction.apply(queryParameter);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        QueryParamsTableColumn tableColumn = getColumnAtIndex(columnIndex);
        QueryParameter queryParameter = queryParameters.getQueryParameter(rowIndex);

        tableColumn.setColumnValueConsumer.accept(queryParameter, aValue);
    }

    private static QueryParamsTableColumn getColumnAtIndex(int columnIndex)
    {
        QueryParamsTableColumn tableColumn = COLUMNS_IN_ORDER.get(columnIndex);

        return tableColumn == null
                ? UNKNOWN
                : tableColumn;
    }
}
