package beans;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode( of = {"id", "email"})
@ToString
public final class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    public User(Integer id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}