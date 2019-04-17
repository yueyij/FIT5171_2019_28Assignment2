package rockets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.time.LocalDate;
import java.util.*;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class LaunchTest {

    private Launch launch;

    @BeforeEach
    public void setUp() {
        launch = new Launch();
    }

    // launch date

    @DisplayName("should throw exception when pass null to setLaunchDate function")
    @Test
    public void shouldThrowExceptionWhenSetLaunchDateToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> launch.setLaunchDate(null));
        assertEquals("LaunchDate cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass invalid LaunchDate to setLaunchDate function")
    @Test
    public void shouldThrowExceptionWhenSetInvalidLaunchDate() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> launch.setLaunchDate(LocalDate.now().plusDays(1)));
        assertEquals("Launch date must not be a date after today", exception.getMessage());
    }

    @DisplayName("should not throw exception when pass valid launchDate to setLaunchDate function")
    @Test
    public void shouldNotThrowExceptionWhenSetValidLaunchDate() {

        launch.setLaunchDate(LocalDate.now());
        assertEquals(launch.getLaunchDate(),LocalDate.now());
    }


    @DisplayName("should return false when launch date is not valid (after today)")
    @Test
    public void shouldReturnFalseWhenLaunchDateIsNotValid() {
        assertFalse(launch.isLaunchDateValid(LocalDate.now().plusDays(1)));

    }

    @DisplayName("should return true when launch date is valid (before today or today)")
    @Test
    public void shouldReturnFalseWhenLaunchDateIsValid() {
        assertTrue(launch.isLaunchDateValid(LocalDate.now()));
        assertTrue(launch.isLaunchDateValid(LocalDate.now().minusDays(1)));
    }

    // launch vehicle



    @DisplayName("should throw exception when pass null to setLaunchVehicle function")
    @Test
    public void shouldThrowExceptionWhenSetLaunchVehicleToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> launch.setLaunchVehicle(null));
        assertEquals("LaunchVehicle cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should not throw exception when pass valid launch vehicle to setLaunchVehicle function")
    @Test
    public void shouldNotThrowExceptionWhenSetValidLaunchVehicle() {
        LaunchServiceProvider launchServiceProvider = new LaunchServiceProvider("JessieLaunch",2000,"AU");
        Rocket rocket = new Rocket("Alpha","US",launchServiceProvider);
        launch.setLaunchVehicle(rocket);
        assertEquals(launch.getLaunchVehicle(),rocket);
    }



    // launch service provider



    @DisplayName("should throw exception when pass null to setLaunchServiceProvider function")
    @Test
    public void shouldThrowExceptionWhenSetLaunchServiceProviderToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> launch.setLaunchServiceProvider(null));
        assertEquals("LaunchServiceProvider cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should not throw exception when pass valid launch service provider to setLaunchServiceProvider function")
    @Test
    public void shouldNotThrowExceptionWhenSetValidLaunchServiceProvider() {
        LaunchServiceProvider provider = new LaunchServiceProvider("Launch",1990,"AU");
        launch.setLaunchServiceProvider(provider);
        assertEquals(launch.getLaunchServiceProvider(),provider);
    }

    // payload



    @DisplayName("should throw exception when pass null to setPayload function")
    @Test
    public void shouldThrowExceptionWhenSetPayloadToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> launch.setPayload(null));
        assertEquals("Payload cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass invalid payload to setPayload function")
    @Test
    public void shouldThrowExceptionWhenSetInvalidPayload() {
        Set set = new HashSet();
        set.add("123");
        set.add("456");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> launch.setPayload(set));
        assertEquals("Payload should be letter only", exception.getMessage());
    }


    @DisplayName("should return false when payload is not valid")
    @Test
    public void shouldReturnFalseWhenPayloadContainsNumber() {

        Set set = new HashSet();
        set.add("123");
        set.add("456");
        assertFalse(launch.isPayloadValid(set));

    }

    @DisplayName("should return true when payload is valid(letter only)")
    @Test
    public void shouldReturnTrueWhenPayloadIsValid() {

        Set set = new HashSet();
        set.add("satellites");
        set.add("spacecrafts");
        assertTrue(launch.isPayloadValid(set));
    }

    @DisplayName("should not throw exception when pass valid payload to setPayload function")
    @Test
    public void shouldNotThrowExceptionWhenSetValidPayload() {
        Set set = new HashSet();
        set.add("spacecrafts");
        set.add("satellites");
        Launch testLaunch = new Launch();
        testLaunch.setPayload(set);
        assertEquals(set, testLaunch.getPayload());
    }



    //launch site

    @DisplayName("should throw exception when pass a empty launch site to setLaunchSite function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetLaunchSiteToEmpty(String launchSite) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> launch.setLaunchSite(launchSite));
        assertEquals("LaunchSite cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setLaunchSite function")
    @Test
    public void shouldThrowExceptionWhenSetLaunchSiteToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> launch.setLaunchSite(null));
        assertEquals("LaunchSite cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass invalid LaunchSite to setLaunchSite function")
    @Test
    public void shouldThrowExceptionWhenSetInvalidLaunchSite() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> launch.setLaunchSite("-dwn!#(!)"));
        assertEquals("LaunchSite should includes no special character", exception.getMessage());
    }

    @DisplayName("should return false when launchSite is not valid")
    @Test
    public void shouldReturnFalseWhenLaunchSiteIsNotValid() {
        assertFalse(launch.isLaunchSiteValid("-dwn!#(!)"));

    }

    @DisplayName("should return true when launchSite is valid")
    @Test
    public void shouldReturnFalseWhenLaunchSiteIsValid() {
        assertTrue(launch.isLaunchSiteValid("Australia"));
    }

    @DisplayName("should not throw exception when pass valid LaunchSite to setLaunchSite function")
    @Test
    public void shouldNotThrowExceptionWhenSetValidLaunchSite() {
        String site = "Champion1";
        Launch testLaunch = new Launch();
        testLaunch.setLaunchSite(site);
        assertEquals(testLaunch.getLaunchSite(), site);
    }


    //orbit


    @DisplayName("should throw exception when pass a empty orbit to setOrbit function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetOrbitToEmpty(String orbit) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> launch.setOrbit(orbit));
        assertEquals("Orbit cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setOrbit function")
    @Test
    public void shouldThrowExceptionWhenSetOrbitToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> launch.setOrbit(null));
        assertEquals("Orbit cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass invalid orbit to setOrbit function")
    @Test
    public void shouldThrowExceptionWhenSetInvalidOrbit() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> launch.setOrbit("-dwn!#(!)"));
        assertEquals("Orbit should be LEO/GTO/OTHER", exception.getMessage());
    }

    @DisplayName("should return false when Orbit is not valid")
    @Test
    public void shouldReturnFalseWhenOrbitIsNotValid() {
        assertFalse(launch.isOrbitValid("-dwn!#(!)"));

    }

    @DisplayName("should return true when Orbit is valid")
    @Test
    public void shouldReturnFalseWhenOrbitIsValid() {
        assertTrue(launch.isOrbitValid("LEO"));
    }

    @DisplayName("should not throw exception when pass valid orbit to setOrbit function")
    @Test
    public void shouldNotThrowExceptionWhenSetValidOrbit() {
        String orbit = "OTHER";
        Launch testLaunch = new Launch();
        testLaunch.setOrbit(orbit);
        assertEquals(testLaunch.getOrbit(), orbit);

    }

    //function


    @DisplayName("should throw exception when pass a empty function to setFunction function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetFunctionToEmpty(String function) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> launch.setFunction(function));
        assertEquals("Function cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setFunction function")
    @Test
    public void shouldThrowExceptionWhenSetFunctionToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> launch.setFunction(null));
        assertEquals("Function cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should not throw exception when pass valid function to setFunction method")
    @Test
    public void shouldNotThrowExceptionWhenSetValidFunction() {
        String function = "Weather Forecast";
        Launch testLaunch = new Launch();
        testLaunch.setFunction(function);
        assertEquals(testLaunch.getFunction(), function);
    }


    //price



    @DisplayName("should throw exception when pass null to setPrice function")
    @Test
    public void shouldThrowExceptionWhenSetPriceToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> launch.setPrice(null));
        assertEquals("Price cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass invalid price to setOrbit function")
    @Test
    public void shouldThrowExceptionWhenSetInvalidPrice() {

        BigDecimal price = new BigDecimal("-100000");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> launch.setPrice(price));
        assertEquals("Price should be number and positive", exception.getMessage());
    }

    @DisplayName("should return true when price is valid")
    @Test
    public void shouldReturnTrueWhenPriceIsValid() {
        BigDecimal price = new BigDecimal("100000");
        assertTrue(launch.isPriceValid(price));
    }

    @DisplayName("should return false when price is negative")
    @Test
    public void shouldReturnFalseWhenPriceIsNegative() {
        BigDecimal price = new BigDecimal("-100000");
        assertFalse(launch.isPriceValid(price));
    }

    @DisplayName("should not throw exception when pass valid price to setPrice method")
    @Test
    public void shouldNotThrowExceptionWhenSetValidPrice() {
        BigDecimal price = new BigDecimal("25000.99");
        Launch testLaunch = new Launch();
        testLaunch.setPrice(price);
        assertEquals(testLaunch.getPrice(), price);
    }

    //launch outcome


    @DisplayName("should throw exception when pass null to setLaunchOutcome function")
    @Test
    public void shouldThrowExceptionWhenSetLaunchOutcomeToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> launch.setLaunchOutcome(null));
        assertEquals("LaunchOutcome cannot be null or empty", exception.getMessage());
    }


}