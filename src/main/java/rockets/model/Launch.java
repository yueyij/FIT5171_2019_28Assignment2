package rockets.model;

import org.omg.PortableInterceptor.SUCCESSFUL;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.Iterator;

import static org.apache.commons.lang3.Validate.notBlank;

public class Launch extends Entity {
    public enum LaunchOutcome {
        FAILED, SUCCESSFUL
    }

    private LocalDate launchDate;

    private Rocket launchVehicle;

    private LaunchServiceProvider launchServiceProvider;

    private Set<String> payload;

    private String launchSite;

    private String orbit;

    private String function;

    private BigDecimal price;

    private LaunchOutcome launchOutcome;

    public Launch(LocalDate launchDate,Rocket launchVehicle,LaunchServiceProvider launchServiceProvider, Set<String> payload,
                  String launchSite,String orbit, String function,BigDecimal price,LaunchOutcome launchOutcome)
    {
        this.launchDate=launchDate;
        this.launchVehicle=launchVehicle;
        this.launchServiceProvider=launchServiceProvider;
        this.payload=payload;
        this.launchSite=launchSite;
        this.orbit=orbit;
        this.function=function;
        this.price=price;
        this.launchOutcome=launchOutcome;
    }

    public Launch()
    {}


    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {

            if (launchDate == null)
            { throw new NullPointerException("LaunchDate cannot be null or empty");}
            else {
                if (isLaunchDateValid(launchDate) == true)
                    this.launchDate = launchDate;
                else
                    throw new IllegalArgumentException("Launch date must not be a date after today");
            }
    }

    public Rocket getLaunchVehicle() {
        return launchVehicle;
    }

    public void setLaunchVehicle(Rocket launchVehicle) {
        if (launchVehicle == null)
            {
               throw new NullPointerException("LaunchVehicle cannot be null or empty");
            }
        else
            {this.launchVehicle = launchVehicle;}
    }

    public LaunchServiceProvider getLaunchServiceProvider() {
        return launchServiceProvider;
    }

    public void setLaunchServiceProvider(LaunchServiceProvider launchServiceProvider) {
        if (launchServiceProvider == null)
        {
            throw new NullPointerException("LaunchServiceProvider cannot be null or empty");
        }
        else
            {this.launchServiceProvider = launchServiceProvider;}
    }

    public Set<String> getPayload()
    {
        return payload;
    }

    public void setPayload(Set<String> payload)
    {
       if (payload == null)
           throw new NullPointerException("Payload cannot be null or empty");
       else
           {
             if (isPayloadValid(payload) == true)
                 this.payload = payload;
             else
                 throw new IllegalArgumentException("Payload should be letter only");

           }
    }

    public String getLaunchSite()
    {
        return launchSite;
    }

    public void setLaunchSite(String launchSite) {
        notBlank(launchSite, "LaunchSite cannot be null or empty");
        if (isLaunchSiteValid(launchSite) == true)
            this.launchSite = launchSite;
        else
            throw new IllegalArgumentException("LaunchSite should includes no special character");
    }

    public String getOrbit() {
        return orbit;
    }

    public void setOrbit(String orbit) {
        notBlank(orbit, "Orbit cannot be null or empty");
        if (isOrbitValid(orbit) == true)
            this.orbit = orbit;
        else
            throw new IllegalArgumentException("Orbit should be LEO/GTO/OTHER");

    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        notBlank(function, "Function cannot be null or empty");
        this.function = function;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price == null)
            throw new NullPointerException("Price cannot be null or empty");
        else {

            if (isPriceValid(price) == true)
                this.price = price;
            else
                throw new IllegalArgumentException("Price should be number and positive");
        }
    }

    public LaunchOutcome getLaunchOutcome() {
        return launchOutcome;
    }

    public void setLaunchOutcome(LaunchOutcome launchOutcome) {
        if (launchOutcome == null)
            {throw new NullPointerException("LaunchOutcome cannot be null or empty");}
        else
            this.launchOutcome = launchOutcome;
    }

    public boolean isPriceValid(BigDecimal price)
    {
        if (price.compareTo(BigDecimal.ZERO) == 1)
            return true;
        else
        {
            return false;
        }
    }

    public boolean isOrbitValid(String orbit)
    {
        if (orbit.compareToIgnoreCase("LEO") == 0 || orbit.compareToIgnoreCase("GTO") == 0 ||
                orbit.compareToIgnoreCase("OTHER") == 0)
            return true;
        else
        {
            return false;
        }
    }

    public boolean isLaunchDateValid(LocalDate launchDate)
    {
         LocalDate currentDate = LocalDate.now();
                if (launchDate.isAfter(currentDate) == true)
                    return false;
                else
                    return true;

    }

    public boolean isPayloadValid(Set<String> set)
    {

            Iterator<String> it = set.iterator();
            while (it.hasNext())
            {
                String value = it.next();
                for (int c = 0; c < value.length(); c ++)
                {
                    if (Character.isLetter(value.charAt(c)) == false)
                    {
                        return false;
                    }
                }
            }

            return true;
    }

    public boolean isLaunchSiteValid(String launchSite)
    {
        if(launchSite.matches("[0-9a-zA-Z]*"))
            return true;
        else
            return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Launch launch = (Launch) o;
        return Objects.equals(launchDate, launch.launchDate) &&
                Objects.equals(launchVehicle, launch.launchVehicle) &&
                Objects.equals(launchServiceProvider, launch.launchServiceProvider) &&
                Objects.equals(orbit, launch.orbit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(launchDate, launchVehicle, launchServiceProvider, orbit);
    }
}
