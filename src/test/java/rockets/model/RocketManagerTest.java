package rockets.model;

import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class RocketManagerTest {
    private RocketManager target;
    private Rocket rocket;


        @BeforeEach
        public void setUp() {
            LaunchServiceProvider launchServiceProvider = new LaunchServiceProvider("JessieLaunch",2000,"AU");
            rocket = new Rocket("name","AU", launchServiceProvider);
            target = new RocketManager("Yueyi","Zhao","abc@gmail.com","ThisIsAPassword");
        }

    @DisplayName("should throw exception when pass invalid value to constructor")
    @Test
    public void shouldThrowExceptionWhenPassInvalidToUserConstructor() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new RocketManager("YueyiYueyiYueyiYueyiYueyiYueyiYueyi","Zhao","abc@gmail.com","ThisIsAPassword"));
        assertEquals("Value should be valid", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a empty last name to setLastName function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetLastNameToEmpty(String lastName) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setLastName(lastName));
        assertEquals("Last name cannot be null or empty.", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setEmail function")
    @Test
    public void shouldThrowExceptionWhenSetLastNameToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setLastName(null));
        assertEquals("Last name cannot be null or empty.", exception.getMessage());
    }

    @DisplayName("should not throw exception when pass valid name to setLastName function")
    @Test
    public void shouldNotThrowExceptionWhenSetValidLastName() {

        String name = "AValidName";
        target.setLastName(name);
        assertEquals(target.getLastName(),name);
    }

    @DisplayName("should throw exception when pass invalid lastName to setLastName function")
    @Test
    public void shouldThrowExceptionWhenSetInvalidLastName() {
        String lastName = "FengFengFengFengFengFeng";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setLastName(lastName));
        assertEquals("LastName should be letters and not exceed 20", exception.getMessage());
        String lastName1 = "Feng!";
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> target.setLastName(lastName1));
        assertEquals("LastName should be letters and not exceed 20", exception.getMessage());
        String lastName2 = "Feng8";
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> target.setLastName(lastName2));
        assertEquals("LastName should be letters and not exceed 20", exception.getMessage());
    }

    @DisplayName("should return true when the last name is valid")
    @Test
    public void shouldReturnTrueWhenLastNameLengthIsValid() {
        String lastName = "Feng";
        assertTrue(target.isValidLastName(lastName));
    }

    @DisplayName("should return false when the length of last name is invalid")
    @Test
    public void shouldReturnFalseWhenLastNameLengthIsInValid() {
        String lastName = "FengFengFengFengFengFeng";
        assertFalse(target.isValidLastName(lastName));
    }

    @DisplayName("should return false when the last name contains special letters")
    @Test
    public void shouldReturnFalseWhenLastNameContainsSpecialLetters() {
        String lastName = "Feng!";
        assertFalse(target.isValidLastName(lastName));
    }

    @DisplayName("should return false when the last name contains number")
    @Test
    public void shouldReturnFalseWhenLastNameContainsNumber() {
        String lastName = "Feng8";
        assertFalse(target.isValidLastName(lastName));
    }

    @DisplayName("should throw exception when pass null to setFirstName function")
    @Test
    public void shouldThrowExceptionWhenSetFirstNameToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setFirstName(null));
        assertEquals("First name cannot be null or empty.", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a empty last name to setLastName function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetFirstNameToEmpty(String firstName) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setFirstName(firstName));
        assertEquals("First name cannot be null or empty.", exception.getMessage());
    }

    @DisplayName("should not throw exception when pass valid name to setFirstName function")
    @Test
    public void shouldNotThrowExceptionWhenSetValidFirstName() {

        String name = "AValidName";
        target.setFirstName(name);
        assertEquals(target.getFirstName(),name);
    }

    @DisplayName("should throw exception when pass invalid firstName to setFirstName function")
    @Test
    public void shouldThrowExceptionWhenSetInvalidFirstName() {
        String firstName = "FengFengFengFengFengFeng";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setFirstName(firstName));
        assertEquals("FirstName should be letters and not exceed 20", exception.getMessage());
        String firstName1 = "Xinyi!";
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> target.setFirstName(firstName1));
        assertEquals("FirstName should be letters and not exceed 20", exception.getMessage());
        String firstName2 = "Xinyi8";
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> target.setFirstName(firstName2));
        assertEquals("FirstName should be letters and not exceed 20", exception.getMessage());
    }

    @DisplayName("should return true when the first name is valid")
    @Test
    public void shouldReturnTrueWhenFirstNameLengthIsValid() {
        String firstName = "Xinyi";
        assertTrue(target.isValidFirstName(firstName));
    }

    @DisplayName("should return false when the length of first name is invalid")
    @Test
    public void shouldReturnFalseWhenFirstNameLengthIsInValid() {
        String firstName = "XinyiXinyiXinyiXinyiXinyiXinyi";
        assertFalse(target.isValidFirstName(firstName));
    }

    @DisplayName("should return false when the first name contains special letters")
    @Test
    public void shouldReturnFalseWhenFirstNameContainsSpecialLetters() {
        String firstName = "Xinyi!";
        assertFalse(target.isValidFirstName(firstName));
    }

    @DisplayName("should return false when the first name contains number")
    @Test
    public void shouldReturnFalseWhenFirstNameContainsNumber() {
        String firstName = "Xinyi8";
        assertFalse(target.isValidFirstName(firstName));
    }

    @DisplayName("should throw exceptions when pass a null password to setPassword function")
    @Test
    public void shouldThrowExceptionWhenSetPasswordToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> target.setPassword(null));
        assertEquals("password cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exceptions when pass a empty password to setPassword function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetPasswordToEmpty() {
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> target.setPassword(null));
        assertEquals("password cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should not throw exception when pass valid password to setPassword function")
    @Test
    public void shouldNotThrowExceptionWhenSetValidPassword() {

        String password = "AsecretPassword";
        target.setPassword(password);
        assertEquals(target.getPassword(),password);
    }

    @DisplayName("should throw exception when pass invalid password to setPassword function")
    @Test
    public void shouldThrowExceptionWhenSetInvalidPassword() {
        String password = "aninvalidpassword";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setPassword(password));
        assertEquals("Password should be 6 to 20 characters and contain at least one upper and lower case", exception.getMessage());
        String password1 = "Woshishen8!";
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> target.setPassword(password1));
        assertEquals("Password should be 6 to 20 characters and contain at least one upper and lower case", exception.getMessage());
        String password2 = "WOSHISHEN8";
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> target.setPassword(password2));
        assertEquals("Password should be 6 to 20 characters and contain at least one upper and lower case", exception.getMessage());
        String password3 = "Woshi";
        IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class, () -> target.setPassword(password3));
        assertEquals("Password should be 6 to 20 characters and contain at least one upper and lower case", exception.getMessage());
        String password4 = "WoshishenWoshishenWoshishen";
        IllegalArgumentException exception4 = assertThrows(IllegalArgumentException.class, () -> target.setPassword(password4));
        assertEquals("Password should be 6 to 20 characters and contain at least one upper and lower case", exception.getMessage());
    }

    @DisplayName("should return true when the password is valid")
    @Test
    public void shouldReturnTrueWhenPasswordIsValid() {
        String password = "Woshishen8";
        assertTrue(target.isValidPassword(password));
    }

    @DisplayName("should return false when the password contains special letters")
    @Test
    public void shouldReturnFalseWhenPasswordContainsSpecialLetters() {
        String password = "Woshishen8!";
        assertFalse(target.isValidPassword(password));
    }

    @DisplayName("should return false when the password doesn't contains uppercase letters")
    @Test
    public void shouldReturnFalseWhenPasswordContainsNoUppercaseLetter() {
        String password = "woshishen8";
        assertFalse(target.isValidPassword(password));
    }

    @DisplayName("should return false when the password doesn't contains lower letters")
    @Test
    public void shouldReturnFalseWhenPasswordContainsNoLowercaseLetter() {
        String password = "WOSHISHEN8";
        assertFalse(target.isValidPassword(password));
    }

    @DisplayName("should return false when the length of password is less than 6 letters")
    @Test
    public void shouldReturnFalseWhenPasswordLengthLessThan6Letters() {
        String password = "Woshi";
        assertFalse(target.isValidPassword(password));
    }

    @DisplayName("should return false when the length of password is larger than 20 letters")
    @Test
    public void shouldReturnFalseWhenPasswordLengthLargerThan20Letters() {
        String password = "WoshishenWoshishenWoshishen";
        assertFalse(target.isValidPassword(password));
    }

    @DisplayName("should throw exception when pass a empty email address to setEmail function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetEmailToEmpty(String email) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setEmail(email));
        assertEquals("email cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setEmail function")
    @Test
    public void shouldThrowExceptionWhenSetEmailToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setEmail(null));
        assertEquals("email cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should not throw exception when pass valid email to setPassword function")
    @Test
    public void shouldNotThrowExceptionWhenSetValidEmail() {

        String email = "abc@example.com";
        target.setEmail(email);
        assertEquals(target.getEmail(),email);
    }

    @DisplayName("should throw exception when pass invalid email to setEmail function")
    @Test
    public void shouldThrowExceptionWhenSetInvalidEmail() {
        String email = "abc1example.com";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setEmail(email));
        assertEquals("Email should be a valid email address", exception.getMessage());
    }

    @DisplayName("should return true when email address is valid")
    @Test
    public void shouldReturnTrueWhenEmailAddressIsValid() {
        String email = "abc@example.com";
        assertTrue(target.isValidEmailAddress(email));
    }

    @DisplayName("should return false when email address is invalid")
    @Test
    public void shouldReturnFalseWhenEmailAddressIsInvalid() {
        String email = "abc1example.com";
        assertFalse(target.isValidEmailAddress(email));
    }

    @DisplayName("should not throw exceptions when pass an rocket object .")
    @Test
    public void shouldNotThrowExceptionWhenRocketIsNotExistBefore() {
        ArrayList<Rocket> rocketList = new ArrayList<Rocket>();
        rocketList.add(rocket);
        assertEquals(rocketList.get(0), rocket);
    }

    @DisplayName("should throw exceptions when pass an already exists rocket object.")
    @Test
    public void shouldThrowExceptionWhenRocketIsAlreadyExist() {
        ArrayList<Rocket> rocketList = new ArrayList<Rocket>();
        rocketList.add(rocket);
        target.setRocketList(rocketList);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.addRocket(rocket));
        assertEquals("This rocket is already exists.", exception.getMessage());
    }

    @DisplayName("should throw exceptions when delete a rocket object that is not exist.")
    @Test
    public void shouldThrowExceptionWhenRocketIsNotExist() {
        LaunchServiceProvider launchServiceProvider = new LaunchServiceProvider("JessieLaunch",2000,"AU");
        Rocket rocket1 = new Rocket("jessie","AU", launchServiceProvider);
        Rocket rocket2 = new Rocket("veka","AU", launchServiceProvider);
        ArrayList<Rocket> rocketList = new ArrayList<Rocket>();
        rocketList.add(rocket1);
        target.setRocketList(rocketList);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.deleteRocket(rocket2));
        assertEquals("This rocket is not exist.", exception.getMessage());
    }

    @DisplayName("should not throw exceptions when delete a rocket object that is exist.")
    @Test
    public void shouldNotThrowExceptionWhenRocketIsExist() {
        LaunchServiceProvider launchServiceProvider = new LaunchServiceProvider("JessieLaunch",2000,"AU");
        Rocket rocket1 = new Rocket("jessie","AU", launchServiceProvider);
        ArrayList<Rocket> rocketList = new ArrayList<Rocket>();
        rocketList.add(rocket1);
        target.setRocketList(rocketList);
        target.deleteRocket(rocket1);
        assertEquals(0, rocketList.size());
    }

    @DisplayName("should throw exception when the rocket list is null or empty.")
    @Test
    public void ShouldThrowExceptionWhenTheRocketListIsNullOrEmpty(){
        ArrayList<Rocket> rocketList = new ArrayList<Rocket>();
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setRocketList(rocketList));
        assertEquals("Rocket list cannot be null or empty.", exception.getMessage());
    }

    @DisplayName("should not throw exception when the rocket list is not null or empty.")
    @Test
    public void ShouldNotThrowExceptionWhenTheRocketListIsNotNullOrEmpty(){
        ArrayList<Rocket> rocketList = new ArrayList<Rocket>();
        rocketList.add(rocket);
        target.setRocketList(rocketList);
        assertEquals(target.getRocketList(), rocketList);
    }

}




