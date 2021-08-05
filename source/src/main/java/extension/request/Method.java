package extension.request;

public enum Method {
    GET("GET");

    private final String displayValue;

    Method(String displayValue) {
        this.displayValue = displayValue;
    }

    @Override
    public String toString() {
        return displayValue;
    }
}
