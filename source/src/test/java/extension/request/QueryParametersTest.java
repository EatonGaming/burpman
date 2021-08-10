package extension.request;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static extension.request.QueryParameter.disabledQueryParam;
import static extension.request.QueryParameter.queryParam;
import static extension.request.QueryParameters.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
        assertThat(queryParameter.getName()).isEqualTo("foo");
        assertThat(queryParameter.getValue()).isEqualTo("bar");
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

    @Test
    void given3QueryParameters_whenGetQueryParameterAtIndex1_thenCorrectParameterReturned()
    {
        QueryParameters queryParameters = queryParameters(List.of(
                queryParam("a", "b"),
                queryParam("c", "d"),
                queryParam("e", "f")
        ));

        QueryParameter queryParameter = queryParameters.getQueryParameter(1);

        assertThat(queryParameter).isEqualTo(queryParam("c", "d"));
    }

    @Test
    void givenNoQueryParameters_whenGetQueryParameterAtIndex1_thenIllegalArgumentExceptionThrown()
    {
        QueryParameters queryParameters = emptyQueryParameters();

        assertThrows(IllegalArgumentException.class, () -> queryParameters.getQueryParameter(1));
    }

    @Test
    void givenNoQueryParameters_whenGetQueryString_thenEmptyStringReturned()
    {
        QueryParameters queryParameters = emptyQueryParameters();

        String queryParametersForPath = queryParameters.getQueryString();

        assertThat(queryParametersForPath).isEqualTo("");
    }

    @Test
    void givenOneQueryParameter_whenGetQueryString_thenCorrectQueryStringReturned()
    {
        QueryParameters queryParameters = queryParameters(List.of(
                queryParam("foo", "bar")
        ));

        String queryParametersForPath = queryParameters.getQueryString();

        assertThat(queryParametersForPath).isEqualTo("?foo=bar");
    }

    @Test
    void givenThreeQueryParameters_whenGetQueryString_thenCorrectQueryStringReturned()
    {
        QueryParameters queryParameters = queryParameters(List.of(
                queryParam("a", "b"),
                queryParam("c", "d"),
                queryParam("e", "f")
        ));

        String queryParametersForPath = queryParameters.getQueryString();

        assertThat(queryParametersForPath).isEqualTo("?a=b&c=d&e=f");
    }

    @Test
    void givenOneDisabledQueryParameter_whenGetQueryString_thenEmptyStringReturned()
    {
        QueryParameters queryParameters = queryParameters(List.of(
                disabledQueryParam("a", "b")
        ));

        String queryParametersForPath = queryParameters.getQueryString();

        assertThat(queryParametersForPath).isEqualTo("");
    }

    @Test
    void givenOneDisabledQueryParameter_inbetweenTwoEnabledQueryParams_whenGetQueryString_thenCorrectStringReturned()
    {
        QueryParameters queryParameters = queryParameters(List.of(
                queryParam("a", "b"),
                disabledQueryParam("c", "d"),
                queryParam("e", "f")
        ));

        String queryParametersForPath = queryParameters.getQueryString();

        assertThat(queryParametersForPath).isEqualTo("?a=b&e=f");
    }

    @Test
    void givenMockAction_whenAddQueryParameter_thenActionInvoked()
    {
        Runnable mockAction = mock(Runnable.class);
        QueryParameters queryParameters = queryParametersForNewRequestModel(mockAction);

        queryParameters.addParameter("foo", "bar");

        verify(mockAction, times(1)).run();
    }
}