package sber.kairachka.service;

import sber.kairachka.domain.City;

import java.util.Comparator;
import java.util.List;

public class Sorter {
    private final List<City> cities;

    public Sorter(List<City> cities) {
        this.cities = cities;
    }

    public List<City> getSortedCitiesByName() {
        cities.sort(Comparator.comparing(City::getName));
        return cities;
    }

    public List<City> getSortedCitiesByDistrictAndName() {
        cities.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
        return cities;
    }
}
