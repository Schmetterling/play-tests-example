package providers;

import beans.User;
import play.db.DB;
import java.sql.*;
import java.util.Optional;

public class UserProviderImpl implements UserProvider {

    @Override
    public Optional<User> getUser(Integer id) throws SQLException {
        User user = null;
        Connection connection = null;

        try {
            connection = DB.getDataSource("default").getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement("select *from tblUser where id = ?");
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();

            if(resultSet.next())
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("email")
                );
        } finally {
            if(connection != null && !connection.isClosed())
                connection.close();
        }

        return user != null ? Optional.of(user) : Optional.empty();
    }
}