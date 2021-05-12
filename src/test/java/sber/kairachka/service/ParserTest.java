package sber.kairachka.service;

import org.junit.Before;
import org.junit.Test;
import sber.kairachka.domain.City;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParserTest {
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
    public void scannerCitiesCorrectFile() {
        assertEquals(testList, Parser.scannerCities("testCities.txt"));
    }

    @Test
    public void scannerCitiesIncorrectLine() {
        Parser.scannerCities("incorrectLineCities.txt");
        assertEquals("Some lines are incorrect",
                outputStream.toString().trim(),
                "Bad file should be handled");
        outputStream.reset();
    }

    @Test
    public void scannerCitiesNoSuchFile() {
        Parser.scannerCities("NonExistingFile.txt");
        assertEquals("There is no such file"
                , outputStream.toString().trim()
                , "No such file should be handled");
        outputStream.reset();
    }
}