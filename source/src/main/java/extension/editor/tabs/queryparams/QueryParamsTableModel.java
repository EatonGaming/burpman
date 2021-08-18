package extension.editor.tabs.queryparams;

import extension.request.QueryParameter;
import extension.request.QueryParameters;

import javax.swing.table.AbstractTableModel;
import java.util.List;

import static extension.editor.tabs.queryparams.QueryParamsTableColumn.UNKNOWN;
import static extension.editor.tabs.queryparams.QueryParamsTableColumn.queryParamsTableColumnsInOrder;

public class QueryParamsTableModel extends AbstractTableModel {
    private static final List<QueryParamsTableColumn> COLUMNS_IN_ORDER = queryParamsTableColumnsInOrder();

    private final QueryParameters queryParameters;

    public QueryParamsTableModel(QueryParameters queryParameters) {
        this.queryParameters = queryParameters;
    }

    void addParameter(String name, String value)
    {
        int insertedRowIndex = queryParameters.getNumberOfParameters();

        queryParameters.addParameter(name, value, () -> fireTableRowsDeleted(insertedRowIndex, insertedRowIndex));

        fireTableRowsInserted(insertedRowIndex, insertedRowIndex);
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
