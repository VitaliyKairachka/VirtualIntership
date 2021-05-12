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

    public List<City> getAllCities() {
        return cities;
    }

    public long[] getMaxPopulation() {
        City[] cityArray = cities.toArray(City[]::new);
        long populationMax = 0;
        long indexMax = 0;
        for (int i = 0; i < cityArray.length; i++) {
            if (cityArray[i].getPopulation() > populationMax) {
                populationMax = cityArray[i].getPopulation();
                indexMax = i;
            }
        }
        return new long[]{indexMax, populationMax};
    }

    public Map<String, Integer> getRegionsCount() {
        Map<String, Integer> regions = new TreeMap<>();
        for (City city : cities) {
            if (!regions.containsKey(city.getRegion())) {
                regions.put(city.getRegion(), 1);
            } else if (regions.containsKey(city.getRegion())) {
                regions.put(city.getRegion(), regions.get(city.getRegion()) + 1);
            }
        }
        return regions;
    }
}
