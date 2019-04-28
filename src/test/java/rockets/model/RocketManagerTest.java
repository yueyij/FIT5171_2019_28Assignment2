package rockets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class RocketManagerTest {
    private RocketManager target;


        @BeforeEach
        public void setUp () {
        rocketManager = new RocketManager();
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




}




