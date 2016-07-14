package units;

import beans.User;
import controllers.UserController;
import org.junit.*;
import play.mvc.Result;
import providers.*;
import java.sql.SQLException;
import java.util.Optional;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class UserTest {
    private static final String EXPECTED_RESULT = "{" +
            "\"id\":1," +
            "\"firstName\":\"testFirstName\"," +
            "\"lastName\":\"testLastName\"," +
            "\"email\":\"testEmail@gmail.com\"}";

    private static final String VALID_ID = "1";
    private static final String INVALID_ID = "INVALID";

    private UserProvider userProvider;
    private User user = null;

    @Before
    public void init() throws SQLException {
        user = new User(
                1,
                "testFirstName",
                "testLastName",
                "testEmail@gmail.com"
        );

        userProvider = mock(UserProviderImpl.class);
        UserController.setUserProvider(userProvider);
        when(userProvider.getUser(anyInt())).thenReturn(Optional.of(user));
    }

    @Test
    public void testGetUserById_ValidID() {
        Result result = UserController.get(VALID_ID);

        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("application/json");
        assertThat(contentAsString(result)).isEqualTo(EXPECTED_RESULT);
    }

    @Test
    public void testGetUserById_InvalidID() throws SQLException {
        Result result = UserController.get(INVALID_ID);

        assertThat(status(result)).isEqualTo(BAD_REQUEST);
    }
}