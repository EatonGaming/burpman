package mvc;

import java.beans.PropertyChangeListener;

public interface Model {
    void bindTo(PropertyChangeListener propertyChangeListener);
    void notifyAllProperties();
}
