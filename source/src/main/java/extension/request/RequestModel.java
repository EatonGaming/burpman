package extension.request;

import mvc.AbstractModel;

import static extension.request.Method.GET;
import static extension.request.QueryParameters.emptyQueryParameters;
import static extension.request.QueryParameters.queryParametersForNewRequestModel;
import static extension.request.RequestModelProperties.METHOD;
import static extension.request.RequestModelProperties.PATH;
import static java.lang.String.format;

public class RequestModel extends AbstractModel {
    private final QueryParameters queryParameters;

    private Method method;
    private String path;

    public RequestModel() {
        this.queryParameters = queryParametersForNewRequestModel(this::notifyPathChanged);
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

    public void setPath(String path, boolean notifyValueChanged) {
        String oldPath = getPathWithQueryString();

        this.path = path;

        notifyValueChanged(notifyValueChanged, PATH.getPropertyName(), oldPath, getPathWithQueryString());
    }

    public String getPathWithQueryString()
    {
        return format("%s%s", path, queryParameters.getQueryString());
    }

    @Override
    public void notifyAllProperties() {
        propertyChangeSupport.firePropertyChange(METHOD.getPropertyName(), null, method);
        notifyPathChanged();
    }

    public QueryParameters getQueryParameters() {
        return queryParameters;
    }

    private void notifyPathChanged() {
        propertyChangeSupport.firePropertyChange(PATH.getPropertyName(), null, getPathWithQueryString());
    }
}
