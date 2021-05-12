package sber.kairachka;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MainTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    List<City> testList = new ArrayList<>();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
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
    public void scannerCities() {
        assertEquals(testList, Main.scannerCities("testCities.txt"));

        Main.scannerCities("incorrectLineCities.txt");
        assertEquals("Some lines are incorrect",
                outputStream.toString().trim(),
                "Bad file should be handled");
        outputStream.reset();

        Main.scannerCities("NonExistingFile.txt");
        assertEquals("There is no such file"
                , outputStream.toString().trim()
                , "No such file should be handled");
        outputStream.reset();
    }

    @Test
    public void getAllCities() {
        Main.getAllCities(testList);
        assertEquals("All cities: \n" +
                        "City{name='Адыгейск', region='Адыгея', district='Южный', population=12248, foundation=1973}\n" +
                        "City{name='Абакан', region='Хакасия', district='Сибирский', population=165183, foundation=1931}\n" +
                        "City{name='Майкоп', region='Адыгея', district='Южный', population=144246, foundation=1857}\n" +
                        "City{name='Горно-Алтайск', region='Алтай', district='Сибирский', population=56928, foundation=1830}"
                , outputStream.toString().trim());
        outputStream.reset();
    }

    @Test
    public void getSortedCitiesByName() {
        Main.getSortedCitiesByName(testList);
        assertEquals("Sorting all cities by name: \n" +
                        "City{name='Абакан', region='Хакасия', district='Сибирский', population=165183, foundation=1931}\n" +
                        "City{name='Адыгейск', region='Адыгея', district='Южный', population=12248, foundation=1973}\n" +
                        "City{name='Горно-Алтайск', region='Алтай', district='Сибирский', population=56928, foundation=1830}\n" +
                        "City{name='Майкоп', region='Адыгея', district='Южный', population=144246, foundation=1857}"
                , outputStream.toString().trim());
        outputStream.reset();
    }

    @Test
    public void getSortedCitiesByDistrictAndName() {
        Main.getSortedCitiesByDistrictAndName(testList);
        assertEquals("Sorting all cities by district and name: \n" +
                        "City{name='Абакан', region='Хакасия', district='Сибирский', population=165183, foundation=1931}\n" +
                        "City{name='Горно-Алтайск', region='Алтай', district='Сибирский', population=56928, foundation=1830}\n" +
                        "City{name='Адыгейск', region='Адыгея', district='Южный', population=12248, foundation=1973}\n" +
                        "City{name='Майкоп', region='Адыгея', district='Южный', population=144246, foundation=1857}"
                , outputStream.toString().trim());
        outputStream.reset();
    }

    @Test
    public void getMaxPopulation() {
        Main.getMaxPopulation(testList);
        assertEquals("The city with the most population: \n" +
                        "[1] = 165183"
                , outputStream.toString().trim());
        outputStream.reset();
    }

    @Test
    public void getRegionsCount() {
        Main.getRegionsCount(testList);
        assertEquals("Number of cities by region: \n" +
                        "Адыгея - 2\n" +
                        "Алтай - 1\n" +
                        "Хакасия - 1"
                , outputStream.toString().trim());
        outputStream.reset();
    }
}