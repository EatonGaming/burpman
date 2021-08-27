package extension.request;

import java.util.Objects;

public class QueryParameter {
    private boolean enabled;
    private String name;
    private String value;
    private final Runnable queryParamUpdatedAction;

    public static QueryParameter queryParamForModel(String name, String value, Runnable queryParamUpdatedAction)
    {
        return new QueryParameter(true, name, value, queryParamUpdatedAction);
    }

    public static QueryParameter queryParam(String name, String value)
    {
        return new QueryParameter(true, name, value, () -> {});
    }

    public static QueryParameter disabledQueryParam(String name, String value)
    {
        return new QueryParameter(false, name, value, () -> {});
    }

    private QueryParameter(boolean enabled, String name, String value, Runnable queryParamUpdatedAction) {
        this.enabled = enabled;
        this.name = name;
        this.value = value;
        this.queryParamUpdatedAction = queryParamUpdatedAction;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;

        queryParamUpdatedAction.run();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

        queryParamUpdatedAction.run();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;

        queryParamUpdatedAction.run();
    }

    public String toQueryStringFormat()
    {
        return String.format("%s=%s", name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryParameter that = (QueryParameter) o;
        return enabled == that.enabled && Objects.equals(name, that.name) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enabled, name, value);
    }
}
