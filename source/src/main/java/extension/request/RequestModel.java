package extension.request;

import mvc.AbstractModel;

import static extension.request.Method.GET;
import static extension.request.QueryParameters.emptyQueryParameters;
import static extension.request.RequestModelProperties.METHOD;
import static extension.request.RequestModelProperties.PATH;

public class RequestModel extends AbstractModel {
    private final QueryParameters queryParameters;

    private Method method;
    private String path;

    public RequestModel() {
        this.queryParameters = emptyQueryParameters();
        this.method = GET;
        this.path = "/";
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method, boolean notifyValueChanged) {
        notifyValueChanged(notifyValueChanged, METHOD.getPropertyName(), this.method, method);

        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path, boolean notifyValueChanged) {
        notifyValueChanged(notifyValueChanged, PATH.getPropertyName(), this.path, path);

        this.path = path;
    }

    @Override
    public void notifyAllProperties() {
        propertyChangeSupport.firePropertyChange(METHOD.getPropertyName(), null, method);
        propertyChangeSupport.firePropertyChange(PATH.getPropertyName(), null, path);
    }
}
