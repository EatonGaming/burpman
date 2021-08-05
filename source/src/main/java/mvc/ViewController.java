package mvc;

import mvc.updatestrategies.ComponentUpdateStrategies;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface ViewController extends PropertyChangeListener {
    ComponentUpdateStrategies getComponentUpdateStrategies();

    @Override
    default void propertyChange(PropertyChangeEvent evt) {
        ComponentUpdateStrategies componentUpdateStrategies = getComponentUpdateStrategies();

        if (componentUpdateStrategies != null)
        {
            componentUpdateStrategies.handle(evt);
        }
    }
}
