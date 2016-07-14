package integrations;

import org.junit.*;
import play.mvc.Result;
import play.test.*;
import java.sql.SQLException;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

public class UserTest extends WithApplication {
    private static final String EXPECTED_RESULT = "{" +
            "\"id\":1," +
            "\"firstName\":\"testFirstName_1\"," +
            "\"lastName\":\"testLastName_1\"," +
            "\"email\":\"test_1@gmail.com\"}";

    private static final String VALID_URL = "/users/1";
    private static final String VALID_URL_WITH_NONEXISTENT_USER = "/users/10";
    private static final String INVALID_URL = "/users/INVALID";

    @Test
    public void testGetUserById_ValidID() throws SQLException {
        Result result = route(new FakeRequest(GET, VALID_URL));

        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("application/json");
        assertThat(contentAsString(result)).isEqualTo(EXPECTED_RESULT);
    }

    @Test
    public void testGetUserById_InvalidID() throws SQLException {
        Result result = route(new FakeRequest(GET, INVALID_URL));

        assertThat(status(result)).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void testGetUserById_NotFound() throws SQLException {
        Result result = route(new FakeRequest(GET, VALID_URL_WITH_NONEXISTENT_USER));

        assertThat(status(result)).isEqualTo(NOT_FOUND);
    }
}