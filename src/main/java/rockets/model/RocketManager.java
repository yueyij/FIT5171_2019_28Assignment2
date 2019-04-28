package rockets.model;

import com.google.common.collect.Sets;

import java.util.Set;
import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

public class RocketManager extends Entity {
    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Set<Rocket> rocket;

    public User(String firstName, String lastName, String email, String password) {
        notNull(firstName);
        notNull(lastName);
        notNull(email);
        notNull(password);

        if (isValidFirstName(firstName)==true && isValidLastName(lastName) == true && isValidEmailAddress(email)==true && isValidPassword(password))
        {   this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password=password;
            rrockets = Sets.newLinkedHashSet();
        }
        else
            throw new IllegalArgumentException("Value should be valid");
    }

    //default constructor
    public RocketManager()
    {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        notBlank(firstName, "First name cannot be null or empty.");
        if (isValidFirstName(firstName) == true)
            this.firstName = firstName;
        else
            throw new IllegalArgumentException("FirstName should be letters and not exceed 20");
    }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName)
    {
        notBlank(lastName, "Last name cannot be null or empty.");
        if (isValidLastName(lastName) == true)
            this.lastName = lastName;
        else
            throw new IllegalArgumentException("LastName should be letters and not exceed 20");
    }
}
