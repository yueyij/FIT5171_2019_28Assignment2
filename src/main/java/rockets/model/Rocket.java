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

    private ArrayList<LaunchServiceProvider> manufacturerList;

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
        manufacturerList = FileManager.readManufacturerInfo("src/main/java/manufacturers.txt");
        if (isManufacturerValid(manufacturer) == true)
            this.manufacturer = manufacturer;
        else
            throw new IllegalArgumentException("manufacturer should be valid");
        if (isCountryValid(country) == true )
            this.country = country;
        else
            throw new IllegalArgumentException("Country should be valid");

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

    public ArrayList<LaunchServiceProvider> getManufacturerList(){
        return manufacturerList;
    }

    public void setManufacturer(LaunchServiceProvider manufacturer)
    {
        if (manufacturer != null && isManufacturerValid(manufacturer) == true)
            this.manufacturer = manufacturer;
        else
            throw new NullPointerException("Manufacturer is not valid");
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

    public void setManufacturerList(ArrayList<LaunchServiceProvider> manufacturerList){

        if (manufacturerList.size() ==0 )
            throw new NullPointerException("Manufacturer List cannot be null");
        else
        {
            this.manufacturerList = manufacturerList;
        }
    }

    public boolean isManufacturerValid(LaunchServiceProvider manufacturer)
    {
        if (manufacturerList.isEmpty())
            throw new NullPointerException("Manufacturer List cannot be null");
        for (LaunchServiceProvider aManufacturer: manufacturerList)
        {
            if (aManufacturer.getName().equals(manufacturer.getName()))
            {
                return true;
            }
        }
        return false;
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
