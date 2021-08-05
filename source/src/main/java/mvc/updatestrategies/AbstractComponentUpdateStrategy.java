package mvc.updatestrategies;

import mvc.updatestrategies.ComponentUpdateStrategy;

import java.awt.*;

abstract class AbstractComponentUpdateStrategy <T extends Component> implements ComponentUpdateStrategy {
    private final String boundPropertyName;

    protected final T component;

    protected AbstractComponentUpdateStrategy(String boundPropertyName, T component) {
        this.boundPropertyName = boundPropertyName;
        this.component = component;
    }

    @Override
    public boolean isBoundToProperty(String propertyName) {
        return boundPropertyName.equals(propertyName);
    }
}
