package rockets.model;

import com.google.common.collect.Sets;

import java.util.*;

import static org.apache.commons.lang3.Validate.*;
import static org.apache.commons.lang3.Validate.notNull;

public class LaunchServiceProvider extends Entity {
    private String name;

    private int yearFounded;

    private String country;

    private String headquarters;

    private Set<Rocket> rockets;

    public LaunchServiceProvider(String name, int yearFounded, String country) {
        notNull(name);
        notNull(yearFounded);
        notNull(country);
     if (isValidName(name) && isValidYearFounded(yearFounded) && isValidCountry(country)) {
         this.name = name;
         this.yearFounded = yearFounded;
         this.country = country;
         rockets = Sets.newLinkedHashSet();
     }
     else
        throw new IllegalArgumentException("Value should be valid");
    }

    public String getName() {
        return name;
    }

    public int getYearFounded() {
        return yearFounded;
    }

    public String getCountry() { return country; }

    public String getHeadquarters() {
        return headquarters;
    }

    public Set<Rocket> getRockets() {
        return rockets;
    }

    public void setName(String name) {
        notBlank(name, "The launch service provider name cannot be null or empty");
        if (!isValidName(name))
            throw new IllegalArgumentException("The launch service provider name should only contain letters and number");
        else
            this.name = name;
    }

    public void setYearFounded(int year) {
        notNaN(year,"year cannot be null or empty");
        if (isValidYearFounded(year))
            this.yearFounded = year;
        else
            throw new IllegalArgumentException("year should be between 1800 and 2019.");
    }

    public void setCountry(String country) {
        notBlank(country, "country cannot be null or empty");
        if (isValidCountry(country))
            this.country = country;
        else
            throw new IllegalArgumentException("Please enter valid country.");
    }

    public void setHeadquarters(String headquarters) {
        notBlank(headquarters, "headquarters cannot be null or empty");
        if (isValidHeadquarters(headquarters))
            this.headquarters = headquarters;
        else
            throw new IllegalArgumentException("Headquarters should only contain letters.");
    }

    public void setRockets(Set<Rocket> rockets) {
        this.rockets = rockets;
    }

    public boolean isValidName(String name)
    {
        if(name.matches("[0-9a-zA-Z]*"))
            return true;
        else
            return false;
    }

    public boolean isValidYearFounded(int yearFounded)
    {
        if (yearFounded>1800 && yearFounded<2019)
            return true;
        else
            return false;
    }

    public boolean isValidCountry(String country)
    {
        Set<String> isoCountries = new HashSet<String>(Arrays.asList(Locale.getISOCountries()));
        return isoCountries.contains(country
        );
    }

    public boolean isValidHeadquarters(String headquarters)
    {
        if(headquarters.matches("[a-zA-Z]*"))
            return true;
        else
            return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaunchServiceProvider that = (LaunchServiceProvider) o;
        return yearFounded == that.yearFounded &&
                Objects.equals(name, that.name) &&
                Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, yearFounded, country);
    }
}
