package controllers;

import beans.User;
import play.Logger;
import play.libs.Json;
import play.mvc.*;
import providers.UserProvider;
import providers.UserProviderImpl;
import java.sql.SQLException;
import java.util.Optional;

public class UserController extends Controller {
    static UserProvider userProvider = new UserProviderImpl();

    public static Result get(String id) {
        try {
            Optional<User> user = userProvider.getUser(Integer.parseInt(id));

            if(user.isPresent()) {
                Logger.info("{} user has been found with the specified id: {}", user.get(), id);
                return status(OK, Json.toJson(user.get()));
            } else {
                Logger.warn("No user has been found with the specified id: {}", id);
                return status(NOT_FOUND);
            }
        } catch (NumberFormatException exception) {
            Logger.warn("Invalid query parameter value for id. The specified 'id' value is ", id);
            return status(BAD_REQUEST, "Invalid query parameter value for id. The specified 'id' value is ", id);
        } catch (SQLException exception) {
            Logger.error("Exception occurred while finding user by id: ", exception.getMessage());
            return status(SERVICE_UNAVAILABLE, exception.getMessage());
        }
    }

    // just for test (Mock)
    public static void setUserProvider(UserProvider uProvider) {
        userProvider = uProvider;
    }
}