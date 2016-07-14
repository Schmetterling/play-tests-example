package providers;

import beans.User;
import java.sql.SQLException;
import java.util.Optional;

public interface UserProvider {
    Optional<User> getUser(Integer id) throws SQLException;
}