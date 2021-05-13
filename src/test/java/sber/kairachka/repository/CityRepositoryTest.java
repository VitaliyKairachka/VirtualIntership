package sber.kairachka.repository;

import org.junit.Before;
import org.junit.Test;
import sber.kairachka.domain.City;
import sber.kairachka.service.Printer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CityRepositoryTest {
    CityRepository cityRepository = new CityRepository(
            "jdbc:h2:/Users/a19189097/IdeaProjects/VirtualIntership/src/test/resources/citiesTest;MV_STORE=false");
    List<City> testList = new ArrayList<>();

    @Before
    public void setUp() {
        testList.add(new City(
                1,
                "Адыгейск",
                "Адыгея",
                "Южный",
                12248,
                1973)
        );
        testList.add(new City(
                2,
                "Абакан",
                "Хакасия",
                "Сибирский",
                165183,
                1931)
        );
        testList.add(new City(
                3,
                "Майкоп",
                "Адыгея",
                "Южный",
                144246,
                1857)
        );
        testList.add(new City(
                4,
                "Горно-Алтайск",
                "Алтай",
                "Сибирский",
                56928,
                1830)
        );
    }


    @Test
    public void findAll() {
        assertTrue(cityRepository.findAll().size() > 0);
    }

    @Test
    public void addCity() {
        assertTrue(cityRepository.addCity(new City(
                "TestCity1", "SPB", "SPB", 1, 2)));
        cityRepository.deleteCity("TestCity1");
    }

    @Test
    public void deleteCity() {
        cityRepository.addCity(new City("TestCity2", "SPB", "SPB", 1, 2));
        assertFalse(cityRepository.deleteCity("TestCity2"));
    }

    @Test
    public void editCity() {
        cityRepository.addCity(new City("TestCity3", "SPB", "SPB", 1, 2));
        assertTrue(cityRepository.editCity("TestCity3",
                new City("TestCity3", "SPB1", "SPB1", 2, 3)));
        cityRepository.deleteCity("TestCity3");
    }

    @Test
    public void findCity() {
        cityRepository.addCity(new City("TestCity4", "SPB", "SPB", 1, 2));
        assertNotNull(cityRepository.findCity("TestCity4"));
        cityRepository.deleteCity("TestCity4");
    }

    @Test
    public void findAllCitiesSortedByName() {
        assertTrue(cityRepository.findAllCitiesSortedByName().size() > 0);
    }

    @Test
    public void findAllCitiesSortedByDistrictAndName() {
        assertTrue(cityRepository.findAllCitiesSortedByDistrictAndName().size() > 0);
    }

    @Test
    public void getMaxPopulation() {
        assertNotNull(cityRepository.getMaxPopulation());
    }

    @Test
    public void getCityCountByRegions() {
        assertNotNull(cityRepository.getMaxPopulation());
    }

    @Test
    public void addListCitiesInDB() {
        assertFalse(cityRepository.addListCitiesInDB(testList));
    }
}