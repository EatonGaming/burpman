package extension.request;

import java.util.Objects;

public class QueryParameter {
    private boolean enabled;
    private String name;
    private String value;

    public static QueryParameter queryParam(String name, String value)
    {
        return new QueryParameter(true, name, value);
    }

    private QueryParameter(boolean enabled, String name, String value) {
        this.enabled = enabled;
        this.name = name;
        this.value = value;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
