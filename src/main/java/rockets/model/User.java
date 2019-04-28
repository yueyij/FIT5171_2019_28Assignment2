package rockets.model;

import com.google.common.collect.Sets;

import java.util.Objects;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

public class User extends Entity {
    private String firstName;

    private String lastName;

    private String email;

    private String password;


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
        }
        else
            throw new IllegalArgumentException("Value should be valid");
    }

    //default constructor
    public User()
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

    public boolean isValidEmailAddress(String email)
    {
        boolean emailValidation = false;
        if(email.matches("^(.+)@(.+)$"))
            emailValidation = true;
        else
            System.out.println("The email address is invalid");
        return emailValidation;
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

    public boolean isValidFirstName(String firstName)
    {
        boolean firstNameValidation = false;
        if(firstName.matches("[a-zA-Z]*"))
        {
            if (firstName.length() <= 20)
                firstNameValidation = true;
            else
                System.out.println("The length of last name shouldn't exceed 20 letters");
        }
        else
            System.out.println("The last name should only contain letters");
        return firstNameValidation;
    }

    public boolean isValidPassword(String password)
    {
        boolean passwordValidation = false;
        if(password.matches("([A-Z]+[a-z]|[a-z]+[A-Z])[A-Za-z0-9]*"))
        {
            if (password.length() <= 20 && password.length() >= 6)
                passwordValidation = true;
            else
                System.out.println("The length of password should be between 6 and 20 characters");
        }
        else
            System.out.println("The password shouldn't contain special letters and it should contain at least 1 uppercase letter and 1 lowercase letter");
        return passwordValidation;
    }

    // match the given password against user's password and return the result
    public boolean isPasswordMatch(String password) {
        return this.password.equals(password.trim());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
