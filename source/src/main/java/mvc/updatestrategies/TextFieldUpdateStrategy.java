package mvc.updatestrategies;

import javax.swing.*;

public class TextFieldUpdateStrategy extends AbstractComponentUpdateStrategy<JTextField> {
    public TextFieldUpdateStrategy(String boundPropertyName, JTextField component) {
        super(boundPropertyName, component);
    }

    @Override
    public void handleUpdate(Object newValue) {
        component.setText((String) newValue);
    }
}
