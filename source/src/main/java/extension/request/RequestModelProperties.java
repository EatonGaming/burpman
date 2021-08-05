package extension.request;

import mvc.ModelProperty;

public enum RequestModelProperties implements ModelProperty {
    METHOD("method"),
    PATH("path");

    private final String propertyName;

    RequestModelProperties(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public String getPropertyName() {
        return propertyName;
    }
}
