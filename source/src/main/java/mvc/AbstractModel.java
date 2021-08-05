package mvc;

import javax.swing.event.SwingPropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class AbstractModel implements Model {
    protected final PropertyChangeSupport propertyChangeSupport;

    protected AbstractModel() {
        propertyChangeSupport = new SwingPropertyChangeSupport(this, true);
    }

    @Override
    public void bindTo(PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }

    protected <T> void notifyValueChanged(boolean notifyValueChanged, String propertyName, T oldValue, T newValue) {
        if (notifyValueChanged)
        {
            propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
        }
    }
}
