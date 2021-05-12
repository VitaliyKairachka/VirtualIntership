package sber.kairachka.service;

import sber.kairachka.domain.City;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    public static List<City> scannerCities(String file) {
        List<City> cities = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(Paths.get(
                    ClassLoader.getSystemResource(file).toURI()));
            while (scanner.hasNext()) {
                Scanner scannerLine = new Scanner(scanner.nextLine());
                scannerLine.useDelimiter(";");
                City city = new City(
                        Long.parseLong(scannerLine.next()),
                        scannerLine.next(),
                        scannerLine.next(),
                        scannerLine.next(),
                        Long.parseLong(scannerLine.next()),
                        Integer.parseInt(scannerLine.next())
                );
                cities.add(city);
            }
        } catch (NullPointerException e) {
            System.out.println("No such file should be handled");
        } catch (NumberFormatException e) {
            System.out.println("Bad file should be handled");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
