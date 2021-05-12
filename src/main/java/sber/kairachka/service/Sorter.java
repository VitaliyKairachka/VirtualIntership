package sber.kairachka.service;

import sber.kairachka.domain.City;

import java.util.Comparator;
import java.util.List;

public class Sorter {
    private final List<City> cities;

    public Sorter(List<City> cities) {
        this.cities = cities;
    }

    public void getSortedCitiesByName() {
        cities.sort(Comparator.comparing(City::getName));
        for (City city : cities) {
            System.out.println(city);
        }
    }

    public void getSortedCitiesByDistrictAndName() {
        cities.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
        for (City city : cities) {
            System.out.println(city);
        }
    }
}
