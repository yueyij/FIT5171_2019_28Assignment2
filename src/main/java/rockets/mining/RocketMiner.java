package rockets.mining;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rockets.dataaccess.DAO;
import rockets.model.Launch;
import rockets.model.LaunchServiceProvider;
import rockets.model.Rocket;

import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class RocketMiner {
    private static Logger logger = LoggerFactory.getLogger(RocketMiner.class);

    private DAO dao;

    public RocketMiner(DAO dao) {
        this.dao = dao;
    }

    /**
     * TODO: to be implemented & tested!
     * Returns the top-k most active rockets, as measured by number of completed launches.
     *
     * @param k the number of rockets to be returned.
     * @return the list of k most active rockets.
     */
    public List<Rocket> mostLaunchedRockets(int k) {

        logger.info("find most active " + k + " rockets");
        Collection<Launch> launches = dao.loadAll(Launch.class);
        if (k > launches.size())
            throw new IllegalArgumentException("k should less than the total number of launches");
        List<Rocket> rocketList = new ArrayList<>();
        // Create a hashMap with rocket and it's number of launches
        HashMap<Rocket,Long> map = new HashMap<>();
        for (Launch launch: launches)
        {
            map.put(launch.getLaunchVehicle(),launches.stream().filter((s) -> s.equals(launch.getLaunchVehicle().getName())).count());
        }
        System.out.println(map);
        // Ordered HashMap with K elements by the number of launches
        Comparator<Entry<Rocket,Long>> rocketNumberComparator = (a, b) -> -a.getValue().compareTo(b.getValue());
        HashMap<Rocket,Long> sorted = map.entrySet().stream().sorted(rocketNumberComparator).limit(k)
                .collect(Collectors.toMap(e -> e.getKey(),e -> e.getValue(), (e1,e2) -> e2, LinkedHashMap::new));
        //add k elements in rocketsList
        for (Entry<Rocket,Long> en : sorted.entrySet())
        {
            rocketList.add(en.getKey());
        }
        return rocketList;
    }

    /**
     * TODO: to be implemented & tested!
     * <p>
     * Returns the top-k most reliable launch service providers as measured
     * by percentage of successful launches.
     *
     * @param k the number of launch service providers to be returned.
     * @return the list of k most reliable ones.
     */
    public List<LaunchServiceProvider> mostReliableLaunchServiceProviders(int k)
    {
        logger.info("find most reliable " + k + " launch service providers");
        Collection<Launch> launches = dao.loadAll(Launch.class);
        if (k > launches.size())
            throw new IllegalArgumentException("k should less than the total number of launches");
        // find all the providers
        List<LaunchServiceProvider> allProviders = new ArrayList<>();
        for (Launch launch: launches)
        {
           LaunchServiceProvider provider = launch.getLaunchServiceProvider();
           allProviders.add(provider);
        }
        List<LaunchServiceProvider> distinctProviders = allProviders.stream().distinct().collect(Collectors.toList());
        //create hashMap of provider and it's successful rate then add value in it
        HashMap<LaunchServiceProvider,Double> map = new HashMap<>();
        for (LaunchServiceProvider provider: distinctProviders)
        {
            int success = 0;
            int fail = 0;
            for (Launch launch: launches)
            {
                if (provider.getName() == launch.getLaunchServiceProvider().getName())
                {
                    switch(launch.getLaunchOutcome())
                    {
                        case FAILED:
                            fail++;
                        case SUCCESSFUL:
                            success++;
                    }
                }
            }
            double successfulRate = success/(success+fail);
            map.put(provider,successfulRate);
        }
        // Sort HashMap with K elements by successful rate
        Comparator<Entry<LaunchServiceProvider,Double>> successfulRateComparator = (a, b) -> -a.getValue().compareTo(b.getValue());
        HashMap<LaunchServiceProvider,Double> sorted = map.entrySet().stream().sorted(successfulRateComparator).limit(k)
                .collect(Collectors.toMap(e -> e.getKey(),e -> e.getValue(), (e1,e2) -> e2, LinkedHashMap::new));
        // Create a list for sorted providers
        List<LaunchServiceProvider> sortedlaunchProviderList = new ArrayList<>();
        for (Entry<LaunchServiceProvider,Double> en : sorted.entrySet())
        {
            sortedlaunchProviderList.add(en.getKey());
        }
        return sortedlaunchProviderList;
    }

    /**
     * <p>
     * Returns the top-k most recent launches.
     *
     * @param k the number of launches to be returned.
     * @return the list of k most recent launches.
     */
    public List<Launch> mostRecentLaunches(int k) {
        logger.info("find most recent " + k + " launches");
        Collection<Launch> launches = dao.loadAll(Launch.class);
        if (k > launches.size())
            throw new IllegalArgumentException("k should less than the total number of launches");
        Comparator<Launch> launchDateComparator = (a, b) -> -a.getLaunchDate().compareTo(b.getLaunchDate());
        return launches.stream().sorted(launchDateComparator).limit(k).collect(Collectors.toList());
    }

    /**
     * TODO: to be implemented & tested!
     * <p>
     * Returns the dominant country who has the most launched rockets in an orbit.
     *
     * @param orbit the orbit
     * @return the country who has the most launched rockets in an orbit.
     */
    public String dominantCountry(String orbit)
    {
        logger.info("find the dominant country who has the most launched rockets in the orbit " + orbit );
        Collection<Launch> launches = dao.loadAll(Launch.class);
        // find all the rockets in the specific orbit
        List<Rocket> allRockets = new ArrayList<>();
        for (Launch launch: launches)
        {
            if (launch.getOrbit().equals(orbit))
            {
                Rocket rocket = launch.getLaunchVehicle();
                allRockets.add(rocket);
            }
        }
        if (allRockets.isEmpty())
            throw new IllegalArgumentException("There is no rocket in this orbit");
        List<Rocket> distinctRockets = allRockets.stream().distinct().collect(Collectors.toList());
        // Create a hashMap with distinctRockets and it's number of launches
        HashMap<Rocket,Integer> map = new HashMap<>();
        for (Rocket rocket: distinctRockets)
        {
            int number = 0;
            for (Launch launch: launches)
            {
                if (rocket.getName() == launch.getLaunchVehicle().getName())
                {
                    number++;
                }
            }
            map.put(rocket,number);
        }
        // Sort HashMap by the number of launches
        Comparator<Entry<Rocket,Integer>> numberComparator = (a, b) -> -a.getValue().compareTo(b.getValue());
        HashMap<Rocket,Integer> sorted = map.entrySet().stream().sorted(numberComparator).limit(1)
                .collect(Collectors.toMap(e -> e.getKey(),e -> e.getValue(), (e1,e2) -> e2, LinkedHashMap::new));
        Map.Entry<Rocket,Integer> entry = sorted.entrySet().iterator().next();
        String country = entry.getKey().getCountry();
        return country;
    }

    /**
     * TODO: to be implemented & tested!
     * <p>
     * Returns the top-k most expensive launches.
     *
     * @param k the number of launches to be returned.
     * @return the list of k most expensive launches.
     */
    public List<Launch> mostExpensiveLaunches(int k) {
        logger.info("find most expensive " + k + " launches");
        Collection<Launch> launches = dao.loadAll(Launch.class);
        if (k > launches.size())
            throw new IllegalArgumentException("k should less than the total number of launches");
        Comparator<Launch> launchDateComparator = (a, b) -> -a.getPrice().compareTo(b.getPrice());
        return launches.stream().sorted(launchDateComparator).limit(k).collect(Collectors.toList());
    }

    /**
     * TODO: to be implemented & tested!
     * <p>
     * Returns a list of launch service provider that has the top-k highest
     * sales revenue in a year.
     *
     * @param k the number of launch service provider.
     * @param year the year in request
     * @return the list of k launch service providers who has the highest sales revenue.
     */
    public List<LaunchServiceProvider> highestRevenueLaunchServiceProviders(int k, int year) {
        logger.info("find most highest sales revenue " + k + " launch service providers in " + year);
        Collection<Launch> launches = dao.loadAll(Launch.class);
        if (k > launches.size())
            throw new IllegalArgumentException("k should less than the total number of launches");
        // find all the providers in a specific year
        List<LaunchServiceProvider> allProviders = new ArrayList<>();
        for (Launch launch: launches)
        {
            if (launch.getLaunchDate().getYear() == year)
            {
                LaunchServiceProvider provider = launch.getLaunchServiceProvider();
                allProviders.add(provider);
            }
        }
        if (allProviders.isEmpty())
            throw new IllegalArgumentException("There is no launch in this year");
        List<LaunchServiceProvider> distinctProviders = allProviders.stream().distinct().collect(Collectors.toList());
        //create hashMap of provider and it's revenue and add value in it
        HashMap<LaunchServiceProvider,BigDecimal> map = new HashMap<>();
        for (LaunchServiceProvider provider: distinctProviders)
        {
            BigDecimal price = new BigDecimal(0);
            for (Launch launch: launches)
            {
                if (provider.getName() == launch.getLaunchServiceProvider().getName())
                {
                    price.add(launch.getPrice());
                }
            }
            map.put(provider,price);
        }
        // Sort HashMap with K elements by the revenue
        Comparator<Entry<LaunchServiceProvider,BigDecimal>> priceComparator = (a, b) -> -a.getValue().compareTo(b.getValue());
        HashMap<LaunchServiceProvider,BigDecimal> sorted = map.entrySet().stream().sorted(priceComparator).limit(k)
                .collect(Collectors.toMap(e -> e.getKey(),e -> e.getValue(), (e1,e2) -> e2, LinkedHashMap::new));
        // Create a list for sorted providers
        List<LaunchServiceProvider> sortedlaunchProviderList = new ArrayList<>();
        for (Entry<LaunchServiceProvider,BigDecimal> en : sorted.entrySet())
        {
            sortedlaunchProviderList.add(en.getKey());
        }
        return sortedlaunchProviderList;
    }
}
