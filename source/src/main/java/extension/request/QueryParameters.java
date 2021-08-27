package extension.request;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static extension.request.QueryParameter.queryParamForModel;
import static java.util.stream.Collectors.toList;

public class QueryParameters {
    private final List<QueryParameter> queryParameters;
    private final Runnable notifyQueryParametersUpdatedAction;

    public static QueryParameters queryParametersForNewRequestModel(Runnable notifyQueryParametersUpdatedAction)
    {
        return new QueryParameters(new ArrayList<>(), notifyQueryParametersUpdatedAction);
    }

    public static QueryParameters emptyQueryParameters()
    {
        return new QueryParameters(new ArrayList<>(), () -> {});
    }

    public static QueryParameters queryParameters(List<QueryParameter> queryParameters)
    {
        List<QueryParameter> startingParams = queryParameters == null
                ? new ArrayList<>()
                : queryParameters;

        return new QueryParameters(startingParams, () -> {});
    }

    private QueryParameters(List<QueryParameter> queryParameters, Runnable notifyQueryParametersUpdatedAction)
    {
        this.queryParameters = queryParameters;
        this.notifyQueryParametersUpdatedAction = notifyQueryParametersUpdatedAction;
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

        QueryParameter queryParameter = queryParamForModel(name, value, notifyQueryParametersUpdatedAction);
        queryParameters.add(queryParameter);

        notifyQueryParametersUpdatedAction.run();
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

    public String getQueryString() {
        List<QueryParameter> enabledQueryParameters = queryParameters.stream()
                .filter(QueryParameter::isEnabled)
                .collect(toList());

        if (enabledQueryParameters.isEmpty())
        {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("?");

        for (Iterator<QueryParameter> iterator = enabledQueryParameters.iterator(); iterator.hasNext();)
        {
            QueryParameter queryParameter = iterator.next();

            stringBuilder.append(queryParameter.toQueryStringFormat());

            if (iterator.hasNext())
            {
                stringBuilder.append("&");
            }
        }

        return stringBuilder.toString();
    }

    public List<Integer> remove(int[] queryParameterIndicesToRemove)
    {
        List<QueryParameter> lookupQueryParameters = new ArrayList<>(queryParameters);
        ArrayList<Integer> parametersRemoved = new ArrayList<>();

        for (int j : queryParameterIndicesToRemove)
        {
            if (lookupQueryParameters.size() - 1 < j || j < 0)
            {
                continue;
            }

            QueryParameter queryParameterToRemove = lookupQueryParameters.get(j);
            queryParameters.remove(queryParameterToRemove);
            parametersRemoved.add(j);
        }

        if (!parametersRemoved.isEmpty())
        {
            notifyQueryParametersUpdatedAction.run();
        }

        return parametersRemoved;
    }
}
