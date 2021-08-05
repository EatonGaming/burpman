package mvc.updatestrategies;

import javax.swing.*;

public class ComboBoxUpdateStrategy<T> extends AbstractComponentUpdateStrategy<JComboBox<T>>{
    public ComboBoxUpdateStrategy(String boundPropertyName, JComboBox<T> component) {
        super(boundPropertyName, component);
    }

    @Override
    public void handleUpdate(Object newValue) {
        component.setSelectedItem(newValue);
    }
}
