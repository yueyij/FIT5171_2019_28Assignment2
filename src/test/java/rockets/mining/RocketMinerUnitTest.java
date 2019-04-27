package rockets.mining;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rockets.dataaccess.DAO;
import rockets.dataaccess.neo4j.Neo4jDAO;
import rockets.model.Launch;
import rockets.model.LaunchServiceProvider;
import rockets.model.Rocket;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RocketMinerUnitTest {
    Logger logger = LoggerFactory.getLogger(RocketMinerUnitTest.class);

    private DAO dao;
    private RocketMiner miner;
    private List<Rocket> rockets;
    private List<LaunchServiceProvider> lsps;
    private List<Launch> launches;

    @BeforeEach
    public void setUp() {
        dao = mock(Neo4jDAO.class);
        miner = new RocketMiner(dao);
        rockets = Lists.newArrayList();

        lsps = Arrays.asList(
                new LaunchServiceProvider("ULA", 1990, "US"),
                new LaunchServiceProvider("SpaceX", 2002, "US"),
                new LaunchServiceProvider("ESA", 1975, "AU")
        );

        // index of lsp of each rocket
        int[] lspIndex = new int[]{0, 0, 0, 1, 1};
        // 5 rockets
        for (int i = 0; i < 5; i++) {
            rockets.add(new Rocket("rocket_" + i, "US", lsps.get(lspIndex[i])));
        }
        // month of each launch
        int[] months = new int[]{1, 6, 4, 3, 4, 11, 6, 5, 12, 5};

        // index of rocket of each launch
        int[] rocketIndex = new int[]{0, 0, 0, 0, 1, 1, 1, 2, 2, 3};

        Launch.LaunchOutcome outcomes[] = Launch.LaunchOutcome.values();
        //index of launchOutcome of each launch
        int[] outcomeIndex = new int[]{0,0,0,1,1,1,1,1,0,1};

        // index of price of each launch
        BigDecimal[] priceIndex = {new BigDecimal(1000),new BigDecimal(1000),new BigDecimal(1000),new BigDecimal(1000)
                                ,new BigDecimal(2000),new BigDecimal(2000),new BigDecimal(2000),new BigDecimal(3000),
                                new BigDecimal(3000),new BigDecimal(4000)};

        BigDecimal price = new BigDecimal("25000.99");

        // 10 launches
        launches = IntStream.range(0, 10).mapToObj(i -> {
            logger.info("create " + i + " launch in month: " + months[i]);
            Launch l = new Launch();
            l.setLaunchDate(LocalDate.of(2017, months[i], 1));
            l.setLaunchVehicle(rockets.get(rocketIndex[i]));
            l.setLaunchServiceProvider(rockets.get(rocketIndex[i]).getManufacturer());
            l.setLaunchSite("VAFB");
            l.setPrice(priceIndex[i]);
            l.setOrbit("LEO");
            l.setLaunchOutcome(outcomes[outcomeIndex[i]]);
            spy(l);
            return l;
        }).collect(Collectors.toList());
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void shouldReturnTopMostRecentLaunches(int k) {
        when(dao.loadAll(Launch.class)).thenReturn(launches);
        List<Launch> sortedLaunches = new ArrayList<>(launches);
        sortedLaunches.sort((a, b) -> -a.getLaunchDate().compareTo(b.getLaunchDate()));
        List<Launch> loadedLaunches = miner.mostRecentLaunches(k);
        assertEquals(k, loadedLaunches.size());
        assertEquals(sortedLaunches.subList(0, k), loadedLaunches);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void shouldReturnTopMostExpensiveLaunches(int k) {
        when(dao.loadAll(Launch.class)).thenReturn(launches);
        List<Launch> sortedLaunches = new ArrayList<>(launches);
        sortedLaunches.sort((a, b) -> -a.getPrice().compareTo(b.getPrice()));
        List<Launch> loadedLaunches = miner.mostExpensiveLaunches(k);
        assertEquals(k, loadedLaunches.size());
        assertEquals(sortedLaunches.subList(0, k), loadedLaunches);
    }

    @ParameterizedTest()
    @ValueSource(strings = "LEO")
    public void shouldReturnTheDominantCountry(String orbit){
        when(dao.loadAll(Launch.class)).thenReturn(launches);
        String country = miner.dominantCountry(orbit);
        assertEquals("US", country);
    }

    @ParameterizedTest
    @CsvSource({"2,2017"})
    public void shouldReturnTheHighestRevenueLaunchServiceProviders(int k, int year){

        when(dao.loadAll(Launch.class)).thenReturn(launches);
        List<LaunchServiceProvider> providers = new ArrayList<>();
        providers.add(new LaunchServiceProvider("ULA", 1990, "US"));
        providers.add(new LaunchServiceProvider("SpaceX", 2002, "US"));

        List<LaunchServiceProvider> loadedProvider = miner.highestRevenueLaunchServiceProviders(k,year);
        assertEquals(providers,loadedProvider);
    }

    @ParameterizedTest
    @ValueSource(ints = 1)
    public void shouldReturnTheMostReliableLaunchServiceProviders(int k){

        when(dao.loadAll(Launch.class)).thenReturn(launches);
        List<LaunchServiceProvider> providers = new ArrayList<>();
        providers.add(new LaunchServiceProvider("SpaceX", 2002, "US"));
        List<LaunchServiceProvider> loadedProvider = miner.mostReliableLaunchServiceProviders(k);
        assertEquals(providers,loadedProvider);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void shouldReturnTopMostLaunchedRockets(int k) {

        when(dao.loadAll(Launch.class)).thenReturn(launches);
        List<Rocket> sortedRockets = new ArrayList<>();

        // A HashMap saves rocket objects and it's number of launches
        HashMap<Rocket,Long> map = new HashMap<>();

        for (Launch launch: launches)
        {
            map.put(launch.getLaunchVehicle(),launches.stream().filter((s) -> s.equals(launch.getLaunchVehicle().getName())).count());
        }
        Comparator<Map.Entry<Rocket,Long>> rocketNumberComparator = (a, b) -> -a.getValue().compareTo(b.getValue());

        // Ordered HashMap with K elements by the number of launches
        HashMap<Rocket,Long> sorted = map.entrySet().stream().sorted(rocketNumberComparator).limit(k)
                .collect(Collectors.toMap(e -> e.getKey(),e -> e.getValue(), (e1,e2) -> e2, LinkedHashMap::new));

        //add k elements in rocketsList
        for (Map.Entry<Rocket,Long> en : sorted.entrySet())
        {
            sortedRockets.add(en.getKey());
        }
        List<Rocket> loadedRockets = miner.mostLaunchedRockets(k);
        assertEquals(k, loadedRockets.size());
        assertEquals(sortedRockets.subList(0, k), loadedRockets);
    }
}