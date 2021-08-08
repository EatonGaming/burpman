package extension.request;

import java.util.Objects;

public class QueryParameter {
    public final String name;
    public final String value;

    public static QueryParameter queryParam(String name, String value)
    {
        return new QueryParameter(name, value);
    }

    private QueryParameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryParameter that = (QueryParameter) o;
        return Objects.equals(name, that.name) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
