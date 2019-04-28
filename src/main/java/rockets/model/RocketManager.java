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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        notBlank(password, "password cannot be null or empty");
        if (isValidPassword(password) == true)
            this.password = password;
        else
            throw new IllegalArgumentException("Password should be 6 to 20 characters and contain at least one upper and lower case");
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        notBlank(email, "email cannot be null or empty");
        if (isValidEmailAddress(email) == true)
            this.email = email;
        else
            throw new IllegalArgumentException("Email should be a valid email address");
    }

    public boolean isValidEmailAddress(String email)
    {
        boolean emailValidation = false;
        if(email.matches("^(.+)@(.+)$"))
            emailValidation = true;
        else
            System.out.println("The email address is invalid");
        return emailValidation;
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

    public boolean isValidLastName(String lastName)
    {
        boolean lastNameValidation = false;
        if(lastName.matches("[a-zA-Z]*"))
        {
            if (lastName.length() <= 20)
                lastNameValidation = true;
            else
                System.out.println("The length of last name shouldn't exceed 20 letters");
        }
        else
            System.out.println("The last name should only contain letters");
        return lastNameValidation;
    }


}
