package sber.kairachka.service;

import sber.kairachka.domain.City;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Printer {
    public Printer() {
    }

    public void print(List<City> cities) {
        for (City city : cities) {
            System.out.println(city);
        }
    }

    public void print(long[] numbers) {
        System.out.println("[" + numbers[0] + "]" + " = " + numbers[1]);
    }

    public void print(Map<String, Integer> regions) {
        for (Map.Entry<String, Integer> entry : regions.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    public void print (boolean b) {
        if (b = true) {
            System.out.println("Операция выполнена");
        } else System.out.println("Ошибка выполнения операции");
    }

    public void print (Optional<City> city) {
        System.out.println(city);
    }
}
