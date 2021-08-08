package extension.editor.tabs.queryparams;

import extension.request.QueryParameter;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;

public enum QueryParamsTableColumn {
    UNKNOWN(-1, "", String.class, false, x -> null, (x, y) -> {}, 0),
    ENABLED(0, "Enabled", Boolean.class, true, QueryParameter::isEnabled, (x, y) -> x.setEnabled((Boolean) y), 10),
    NAME(1, "Name", String.class, true, QueryParameter::getName, (x, y) -> x.setName((String) y), 45),
    VALUE(2, "Value", String.class, true, QueryParameter::getValue, (x, y) -> x.setValue((String) y), 45);

    public final int positionSortIndex;
    public final String columnName;
    public final Class<?> columnClass;
    public final boolean columnEditable;
    public final Function<QueryParameter, Object> getColumnValueFunction;
    public final BiConsumer<QueryParameter, Object> setColumnValueConsumer;
    public final int columnWidthPercentage;

    QueryParamsTableColumn(int positionSortIndex, String columnHeaderText, Class<?> columnClass, boolean columnEditable, Function<QueryParameter, Object> getColumnValueFunction, BiConsumer<QueryParameter, Object> setColumnValueConsumer, int columnWidthPercentage) {
        this.positionSortIndex = positionSortIndex;
        this.columnName = columnHeaderText;
        this.columnClass = columnClass;
        this.columnEditable = columnEditable;
        this.getColumnValueFunction = getColumnValueFunction;
        this.setColumnValueConsumer = setColumnValueConsumer;
        this.columnWidthPercentage = columnWidthPercentage;
    }

    public static List<QueryParamsTableColumn> queryParamsTableColumnsInOrder()
    {
        return Arrays.stream(QueryParamsTableColumn.values())
                .filter(x -> x != UNKNOWN)
                .sorted(comparingInt(x -> x.positionSortIndex))
                .collect(Collectors.toList());
    }
}
