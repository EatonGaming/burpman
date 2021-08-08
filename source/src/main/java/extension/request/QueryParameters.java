package extension.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static extension.request.QueryParameter.queryParam;

public class QueryParameters {
    private final List<QueryParameter> queryParameters;

    public static QueryParameters emptyQueryParameters()
    {
        return new QueryParameters(new ArrayList<>());
    }

    public static QueryParameters queryParameters(List<QueryParameter> queryParameters)
    {
        List<QueryParameter> startingParams = queryParameters == null
                ? new ArrayList<>()
                : queryParameters;

        return new QueryParameters(startingParams);
    }

    private QueryParameters(List<QueryParameter> queryParameters)
    {
        this.queryParameters = queryParameters;
    }

    public int getNumberOfParameters()
    {
        return queryParameters.size();
    }

    public Optional<QueryParameter> getQueryParameter(String name)
    {
        if (name == null)
        {
            throw new IllegalArgumentException("You must provide a value to find a specific query parameter.");
        }

        return queryParameters.stream()
                .filter(x -> name.equals(x.getName()))
                .findFirst();
    }

    public void addParameter(String name, String value)
    {
        assertValuesProvidedAreNotNull(name, value);

        QueryParameter queryParameter = queryParam(name, value);
        queryParameters.add(queryParameter);
    }

    private void assertValuesProvidedAreNotNull(String name, String value) {
        if (name == null)
        {
            throw new IllegalArgumentException("You must provide a name when adding a query parameter.");
        }
        else if (value == null)
        {
            throw new IllegalArgumentException("You must provide a value when adding a query parameter.");
        }
    }

    public QueryParameter getQueryParameter(int index) {
        if (index < 0 || index > queryParameters.size())
        {
            throw new IllegalArgumentException("You must provide a valid index to retrieve a query parameter.");
        }

        return queryParameters.get(index);
    }
}
