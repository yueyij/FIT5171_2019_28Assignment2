package rockets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RocketTest {

    private Rocket rocket;

    @BeforeEach
    public void setUp() {

        LaunchServiceProvider launchServiceProvider = new LaunchServiceProvider("JessieLaunch",2000,"AU");
        rocket = new Rocket("name","AU", launchServiceProvider);
    }

    @DisplayName("should throw exception when pass a empty name to setName function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetNameToEmpty(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> rocket.setName(name));
        assertEquals("name cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setName function")
    @Test
    public void shouldThrowExceptionWhenSetNameToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> rocket.setName(null));
        assertEquals("name cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass invalid value to constructor")
    @Test
    public void shouldThrowExceptionWhenPassInvalidCountryToRocketConstructor() {
        LaunchServiceProvider launchServiceProvider = new LaunchServiceProvider("JessieLaunch",2000,"AU");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Rocket("Alpha","NotACountry", launchServiceProvider));
        assertEquals("Country should be valid", exception.getMessage());
    }

    @DisplayName("should throw exception when pass invalid value to constructor")
    @Test
    public void shouldThrowExceptionWhenPassNullNameToRocketConstructor() {
        LaunchServiceProvider launchServiceProvider = new LaunchServiceProvider("JessieLaunch",2000,"AU");
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new Rocket(null,"AU", launchServiceProvider));
        assertEquals("The validated object is null", exception.getMessage());
    }

    @DisplayName("should throw exception when pass invalid value to constructor")
    @Test
    public void shouldThrowExceptionWhenPassNullLaunchServiceProviderToRocketConstructor() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new Rocket("Alpha","AU", null));
        assertEquals("The validated object is null", exception.getMessage());
    }


    @DisplayName("should not throw exception when pass valid name to setName function")
    @Test
    public void shouldNotThrowExceptionWhenSetValidName() {

        String name = "AValidName";
        rocket.setName(name);
        assertEquals(rocket.getName(),name);
    }

    @DisplayName("should throw exception when pass a empty country to set function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetCountryToEmpty(String country) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> rocket.setCountry(country));
        assertEquals("country cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setCountry function")
    @Test
    public void shouldThrowExceptionWhenSetCountryToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> rocket.setCountry(null));
        assertEquals("country cannot be null or empty", exception.getMessage());
    }



    @DisplayName("should throw exception when pass null to setManufacturer function")
    @Test
    public void shouldThrowExceptionWhenSetManufacturerToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> rocket.setManufacturer(null));
        assertEquals("Manufacturer cannot be null", exception.getMessage());
    }

    @DisplayName("should not throw exception when pass valid manufacturer to setManufacturer function")
    @Test
    public void shouldNotThrowExceptionWhenSetValidManufacturer() {

        LaunchServiceProvider launchServiceProvider = new LaunchServiceProvider("JessieLaunch",2000,"AU");
        rocket.setManufacturer(launchServiceProvider);
        assertEquals(rocket.getManufacturer(),launchServiceProvider);
    }

    @DisplayName("should throw exception when pass null to setMass function")
    @Test
    public void shouldThrowExceptionWhenSetMassToNull() {
        ArrayList<String> mass = new ArrayList<>();
        NullPointerException exception = assertThrows(NullPointerException.class, () -> rocket.setMass(mass));
        assertEquals("LEO/GTO/OTHER cannot be null", exception.getMessage());
    }

    @DisplayName("should not throw exception when pass valid mass to setMass function")
    @Test
    public void shouldNotThrowExceptionWhenSetValidMass() {

        ArrayList<String> testMass = new ArrayList<>();
        testMass.add("1000");
        testMass.add(null);
        testMass.add("600 to SSO");
        rocket.setMass(testMass);
        assertEquals(testMass,rocket.getMass());
    }

    @DisplayName("should return false when set country name is not valid")
    @Test
    public void shouldReturnFalseWhenSetCountryNameIsNotValid() {

        assertFalse(rocket.isCountryValid("NotACountry"));
    }

    @DisplayName("should return true when set country name is valid")
    @Test
    public void shouldReturnTrueWhenSetCountryNameIsValid() {

        assertTrue(rocket.isCountryValid("AU"));
    }


    @DisplayName("should throw exception when pass invalid Country to setCountry function")
    @Test
    public void shouldThrowExceptionWhenSetInvalidCountry() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> rocket.setCountry("NotACountry"));
        assertEquals("Country should be a valid country in country code (AU for Australia)", exception.getMessage());
    }

    @DisplayName("should not throw exception when pass valid country to setCountry function")
    @Test
    public void shouldNotThrowExceptionWhenSetValidCountry() {

       String country = "AU";
       rocket.setCountry(country);
       assertEquals(rocket.getCountry(),country);
    }

}