package extension.request;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static extension.request.QueryParameter.queryParam;
import static extension.request.QueryParameters.emptyQueryParameters;
import static extension.request.QueryParameters.queryParameters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QueryParametersTest {

    @Test
    void givenEmptyQueryParameters_whenGetNumberOfParameters_then0IsReturned()
    {
        QueryParameters queryParameters = emptyQueryParameters();

        int actual = queryParameters.getNumberOfParameters();

        assertThat(actual).isZero();
    }

    @Test
    void givenOneQueryParameter_whenGetNumberOfParameters_then1IsReturned()
    {
        QueryParameters queryParameters = queryParameters(List.of(
                queryParam("x", "y")
        ));

        int actual = queryParameters.getNumberOfParameters();

        assertThat(actual).isEqualTo(1);
    }

    @Test
    void givenThreeQueryParameters_whenGetNumberOfParameters_then3IsReturned()
    {
        QueryParameters queryParameters = queryParameters(List.of(
                queryParam("a", "b"),
                queryParam("c", "d"),
                queryParam("e", "f")
        ));

        int actual = queryParameters.getNumberOfParameters();

        assertThat(actual).isEqualTo(3);
    }

    @Test
    void givenQueryParameters_withNullCollection_whenGetNumberOfParameters_then0IsReturned()
    {
        QueryParameters queryParameters = queryParameters(null);

        int actual = queryParameters.getNumberOfParameters();

        assertThat(actual).isZero();
    }

    @Test
    void givenPrePopulatedQueryParameters_whenGetQueryParameter_withNameThatExistsInContainer_thenQueryParameterIsReturned()
    {
        QueryParameter queryParamToFind = queryParam("c", "d");
        QueryParameters queryParameters = queryParameters(List.of(
                queryParam("a", "b"),
                queryParamToFind,
                queryParam("e", "f")
        ));

        Optional<QueryParameter> queryParameter = queryParameters.getQueryParameter("c");

        assertThat(queryParameter).isNotEmpty();
        assertThat(queryParameter).contains(queryParamToFind);
    }

    @Test
    void givenPrePopulatedQueryParameters_whenGetQueryParameter_withNameThatDoesNotExistInContainer_thenQueryParameterIsNotReturned()
    {
        QueryParameters queryParameters = queryParameters(List.of(
                queryParam("a", "b"),
                queryParam("c", "d"),
                queryParam("e", "f")
        ));

        Optional<QueryParameter> queryParameter = queryParameters.getQueryParameter("j");

        assertThat(queryParameter).isEmpty();
    }

    @Test
    void givenQueryParameters_whenGetQueryParameter_withNullName_thenIllegalArgumentExceptionIsThrown()
    {
        QueryParameters queryParameters = emptyQueryParameters();

        assertThrows(IllegalArgumentException.class, () -> queryParameters.getQueryParameter(null));
    }

    @Test
    void givenQueryParameters_whenAddQueryParameter_thenParameterIsAddedToParameters()
    {
        List<QueryParameter> parameterArrayList = new ArrayList<>();
        QueryParameters queryParameters = queryParameters(parameterArrayList);

        queryParameters.addParameter("foo", "bar");

        QueryParameter queryParameter = parameterArrayList.get(0);
        assertThat(queryParameter).isNotNull();
        assertThat(queryParameter.name).isEqualTo("foo");
        assertThat(queryParameter.value).isEqualTo("bar");
    }

    @Test
    void givenQueryParameters_whenAddQueryParameter_withNullName_thenIllegalArgumentExceptionThrown()
    {
        QueryParameters queryParameters = emptyQueryParameters();

        assertThrows(IllegalArgumentException.class, () -> queryParameters.addParameter(null, "bar"));
    }

    @Test
    void givenQueryParameters_whenAddQueryParameter_withNullValue_thenIllegalArgumentExceptionThrown()
    {
        QueryParameters queryParameters = emptyQueryParameters();

        assertThrows(IllegalArgumentException.class, () -> queryParameters.addParameter("foo", null));
    }
}