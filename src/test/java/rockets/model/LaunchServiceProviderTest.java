package rockets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class LaunchServiceProviderTest {
    private LaunchServiceProvider target;

    @BeforeEach
    public void setUp() {
        target = new LaunchServiceProvider("name",2000,"AU");
    }

    @DisplayName("should throw exception when pass invalid value to constructor")
    @Test
    public void shouldThrowExceptionWhenPassInvalidToServiceProviderConstructor() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new LaunchServiceProvider("AName",1600,"AU"));
        assertEquals("Value should be valid", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a empty name to setName function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetNameToEmpty(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setName(name));
        assertEquals("The launch service provider name cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setName function")
    @Test
    public void shouldThrowExceptionWhenSetNameToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setName(null));
        assertEquals("The launch service provider name cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should return true when launch service provider name is valid")
    @Test
    public void shouldReturnTrueWhenNameIsValid() {
        String name = "8Rocket";
        assertTrue(target.isValidName(name));
    }

    @DisplayName("should not throw any exceptions when pass valid service provider name to setName function.")
    @Test
    public void shouldNotThrowExceptionWhenSetValidProviderName()
    {
        String tempName = "Provider12";
        LaunchServiceProvider tempProvider = new LaunchServiceProvider("tempLaunch", 2000, "AU");
        tempProvider.setName(tempName);
        assertEquals(tempProvider.getName(), tempName);
    }

    @DisplayName("should throw exception when pass invalid service provider name to setName function.")
    @Test
    public void shouldThrowExceptionWhenSetInvalidProviderName()
    {
        String name = "Provider12!!";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setName(name));
        assertEquals("The launch service provider name should only contain letters and number", exception.getMessage());
    }

    @DisplayName("should return false when launch service provider name contains special letter")
    @Test
    public void shouldReturnFalseWhenLaunchServiceProviderNameIsInvalid() {
        String name = "Rocket!";
        assertFalse(target.isValidName(name));
    }

    @DisplayName("should return true when year is valid")
    @Test
    public void shouldReturnTrueWhenYearIsValid() {
        int year = 1995;
        assertTrue(target.isValidYearFounded(year));
    }

    @DisplayName("should return false when year is larger than 2019")
    @Test
    public void shouldReturnFalseWhenYearIsLess() {
        int year = 2020;
        assertFalse(target.isValidYearFounded(year));
    }

    @DisplayName("should return false when year is less than 1800")
    @Test
    public void shouldReturnFalseWhenYearIsLarger() {
        int year = 1600;
        assertFalse(target.isValidYearFounded(year));
    }

    @DisplayName("should throw exception when pass invalid year to setYearFounded function.")
    @Test
    public void shouldThrowExceptionWhenSetInvalidFoundedYear()
    {
        int yearFounded = 1688;
        int yearFounded1 = 2200;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setYearFounded(yearFounded));
        assertEquals("year should be between 1800 and 2019.", exception.getMessage());
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> target.setYearFounded(yearFounded1));
        assertEquals("year should be between 1800 and 2019.", exception.getMessage());
    }

    @DisplayName("should not throw exception when pass valid year to setYearFounded function")
    @Test
    public void shouldNotThrowExceptionWhenSetYearFoundedToValidValue() {
        int year = 2016;
        LaunchServiceProvider tempProvider = new LaunchServiceProvider("tempLaunch", 2000, "AU");
        tempProvider.setYearFounded(year);
        assertEquals(tempProvider.getYearFounded(), year);
    }

    @DisplayName("should throw exception when pass a empty country to setCountry function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetEmptyCountry(String country) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setCountry(country));
        assertEquals("country cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setCountry function")
    @Test
    public void shouldThrowExceptionWhenSetCountryToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setCountry(null));
        assertEquals("country cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should return true when country is valid")
    @Test
    public void shouldReturnTrueWhenCountryIsValid() {
        String country = "US";
        assertTrue(target.isValidCountry(country));
    }

    @DisplayName("should return false when country contains special letter")
    @Test
    public void shouldReturnFalseWhenCountryContainsSpecialLetter() {
        String country = "China!";
        assertFalse(target.isValidCountry(country));
    }

    @DisplayName("should return false when country contains number")
    @Test
    public void shouldReturnFalseWhenCountryContainsNumber() {
        String country = "China8";
        assertFalse(target.isValidCountry(country));
    }

    @DisplayName("should throw exception when pass invalid country name to setCountry function")
    @Test
    public void shouldThrowExceptionWhenSetCountryToInvalidValue() {
        String country = "USA123";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setCountry(country));
        assertEquals("Please enter valid country.", exception.getMessage());
    }

    @DisplayName("should not throw exception when pass valid country name to setCountry function")
    @Test
    public void shouldNotThrowExceptionWhenSetCountryToValidValue() {
        String country = "AU";
        LaunchServiceProvider tempProvider = new LaunchServiceProvider("tempLaunch", 2000, "CN");
        tempProvider.setCountry(country);
        assertEquals(tempProvider.getCountry(), country);
    }

    @DisplayName("should throw exception when pass a empty headquarter to setHeadquarters function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetHeadquartersIsEmpty(String headquarter) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setHeadquarters(headquarter));
        assertEquals("headquarters cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setHeadquarters function")
    @Test
    public void shouldThrowExceptionWhenPassNullToSetHeadquarters() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setHeadquarters(null));
        assertEquals("headquarters cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should return true when headquarter is valid")
    @Test
    public void shouldReturnTrueWhenHeadquartersIsValid() {
        String headquarter = "Headquarters";
        assertTrue(target.isValidHeadquarters(headquarter));
    }

    @DisplayName("should return false when headquarter contains special letter")
    @Test
    public void shouldReturnFalseWhenHeadquartersContainsSpecialLetter() {
        String headquarter = "Headquarters!";
        assertFalse(target.isValidHeadquarters(headquarter));
    }

    @DisplayName("should return false when headquarter contains number")
    @Test
    public void shouldReturnFalseWhenHeadquartersContainsNumber() {
        String headquarter = "Headquarters8";
        assertFalse(target.isValidHeadquarters(headquarter));
    }

    @DisplayName("should throw exception when pass invalid country name to setCountry function")
    @Test
    public void shouldThrowExceptionWhenSetHeadquartersToInvalidValue() {
        String headquarter1 = "Mel666";
        String headquarter2 = "Mel!!!";
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> target.setHeadquarters(headquarter1));
        assertEquals("Headquarters should only contain letters.", exception1.getMessage());
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> target.setHeadquarters(headquarter2));
        assertEquals("Headquarters should only contain letters.", exception2.getMessage());
    }

    @DisplayName("should not throw any exceptions when pass valid headquarters to setHeadquarters function.")
    @Test
    public void shouldNotThrowExceptionWhenSetValidHeadquarters()
    {
        String headQuarters = "Champion";
        LaunchServiceProvider tempProvider = new LaunchServiceProvider("tempLaunch", 2000, "AU");
        tempProvider.setHeadquarters(headQuarters);
        assertEquals(tempProvider.getHeadquarters(), headQuarters);
    }

}