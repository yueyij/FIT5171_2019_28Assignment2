package rockets.model;

import java.util.*;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

public class Rocket extends Entity {
    private String name;

    private String country;

    private LaunchServiceProvider manufacturer;

    private String massToLEO;

    private String massToGTO;

    private String massToOther;

    private ArrayList<String> mass;

    /**
     * All parameters shouldn't be null.
     *
     * @param name
     * @param country
     * @param manufacturer
     */
    public Rocket(String name, String country, LaunchServiceProvider manufacturer) {
        notNull(name);
        notNull(country);
        notNull(manufacturer);
        this.name = name;
        this.manufacturer = manufacturer;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        notBlank(name, "name cannot be null or empty");
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country)
    {
        notBlank(country, "country cannot be null or empty");
        if (isCountryValid(country) == true)
            this.country = country;
        else
            throw new IllegalArgumentException("Country should be a valid country in country code (AU for Australia)");
    }

    public LaunchServiceProvider getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(LaunchServiceProvider manufacturer)
    {
        if (manufacturer == null){throw new NullPointerException("Manufacturer cannot be null");}
        this.manufacturer = manufacturer;
    }

    public String getMassToLEO() { return massToLEO; }

    public String getMassToGTO() { return massToGTO; }

    public String getMassToOther() { return massToOther; }

    public ArrayList<String> getMass() { return mass; }

    public void setMassToLEO(String massToLEO) {
        this.massToLEO = massToLEO;}

    public void setMassToGTO(String massToGTO) { this.massToGTO = massToGTO; }

    public void setMassToOther(String massToOther) { this.massToOther = massToOther; }

    public void setMass(ArrayList<String> mass){

        if (mass.size()==0)
            throw new NullPointerException("LEO/GTO/OTHER cannot be null");
        else
        {
            this.mass = mass;

        }
    }

    public boolean isCountryValid(String country){

        Set<String> isoCountries = new HashSet<String>(Arrays.asList(Locale.getISOCountries()));
        return isoCountries.contains(country);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rocket rocket = (Rocket) o;
        return Objects.equals(name, rocket.name) &&
                Objects.equals(country, rocket.country) &&
                Objects.equals(manufacturer, rocket.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, manufacturer);
    }

    @Override
    public String toString() {
        return "Rocket{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", massToLEO='" + massToLEO + '\'' +
                ", massToGTO='" + massToGTO + '\'' +
                ", massToOther='" + massToOther + '\'' +
                '}';
    }
    
}
