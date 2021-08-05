package mvc.updatestrategies;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class ComponentUpdateStrategies {
    private final List<ComponentUpdateStrategy> componentUpdateStrategies;

    public ComponentUpdateStrategies() {
        componentUpdateStrategies = new ArrayList<>();
    }

    public void addStrategy(ComponentUpdateStrategy componentUpdateStrategy)
    {
        componentUpdateStrategies.add(componentUpdateStrategy);
    }

    public void handle(PropertyChangeEvent evt) {
        componentUpdateStrategies.stream()
                .filter(strategy -> strategy.isBoundToProperty(evt.getPropertyName()))
                .forEach(strategy -> strategy.handleUpdate(evt.getNewValue()));
    }
}
