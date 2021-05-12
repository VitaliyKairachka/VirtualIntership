package sber.kairachka;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static List<City> scannerCities(String file) {
        List<City> cities = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(Paths.get(
                    Objects.requireNonNull(Main.class.getClassLoader().getResource(file).getPath())));
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static void getAllCities(List<City> cities) {
        System.out.println("All cities: ");
        for (City city : cities) {
            System.out.println(city);
        }
    }

    public static void getSortedCitiesByName(List<City> cities) {
        System.out.println("Sorting all cities by name: ");
        cities.sort(Comparator.comparing(City::getName));
        for (City city : cities) {
            System.out.println(city);
        }
    }

    public static void getSortedCitiesByDistrictAndName(List<City> cities) {
        System.out.println("Sorting all cities by district and name: ");
        cities.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
        for (City city : cities) {
            System.out.println(city);
        }
    }

    public static void getMaxPopulation(List<City> cities) {
        System.out.println("The city with the most population: ");
        City[] cityArray = cities.toArray(City[]::new);
        long populationMax = 0;
        int indexMax = 0;
        for (int i = 0; i < cityArray.length; i++) {
            if (cityArray[i].getPopulation() > populationMax) {
                populationMax = cityArray[i].getPopulation();
                indexMax = i;
            }
        }
        System.out.println("[" + indexMax + "]" + " = " + populationMax);
    }

    public static void getRegionsCount(List<City> cities) {
        System.out.println("Number of cities by region: ");
        Map<String, Integer> regions = new TreeMap<>();
        for (City city : cities) {
            if (!regions.containsKey(city.getRegion())) {
                regions.put(city.getRegion(), 1);
            } else if (regions.containsKey(city.getRegion())) {
                regions.put(city.getRegion(), regions.get(city.getRegion()) + 1);
            }
        }
        for (Map.Entry<String, Integer> entry : regions.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        List<City> cities = scannerCities("cities.txt");
        getAllCities(cities);
        getSortedCitiesByName(cities);
        getSortedCitiesByDistrictAndName(cities);
        getMaxPopulation(cities);
        getRegionsCount(cities);
    }
}
