package mvc.updatestrategies;

public interface ComponentUpdateStrategy {
    void handleUpdate(Object newValue);

    boolean isBoundToProperty(String propertyName);
}
