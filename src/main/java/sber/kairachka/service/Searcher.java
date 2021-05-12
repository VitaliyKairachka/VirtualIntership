package sber.kairachka.service;

import sber.kairachka.domain.City;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Searcher {
    private final List<City> cities;

    public Searcher(List<City> cities) {
        this.cities = cities;
    }

    public void getAllCities() {
        for (City city : cities) {
            System.out.println(city);
        }
    }

    public void getMaxPopulation() {
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

    public void getRegionsCount() {
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
}
