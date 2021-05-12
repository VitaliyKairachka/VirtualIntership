package sber.kairachka.service;

import sber.kairachka.domain.City;

import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void start() {
        List<City> cities = Parser.scannerCities("cities.txt");
        Sorter sorter = new Sorter(cities);
        Searcher searcher = new Searcher(cities);
        Scanner scan = new Scanner(System.in);
        int x;
        String s = "";

        while (!"6".equals(s)) {
            System.out.println("\n1. Для вывода всех городов введите 1");
            System.out.println("2. Для сортировки городов по имени введите 2");
            System.out.println("3. Для сортировки городов по Федеральному округу и имени введите 3");
            System.out.println("4. Для поиска города с наибольшим количеством жителей 4");
            System.out.println("5. Для поиска количества городов в разрезе регионов введите 5");
            System.out.println("6. Для выхода из приложения введие 6");
            s = scan.next();
            try {
                x = Integer.parseInt(s);
                switch (x) {
                    case 1:
                        searcher.getAllCities();
                        break;
                    case 2:
                        sorter.getSortedCitiesByName();
                        break;
                    case 3:
                        sorter.getSortedCitiesByDistrictAndName();
                        break;
                    case 4:
                        searcher.getMaxPopulation();
                        break;
                    case 5:
                        searcher.getRegionsCount();
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод");
            }


        }
        System.out.println("До свидания!");
    }
}
