package sber.kairachka.service;

import sber.kairachka.domain.City;
import sber.kairachka.repository.CityRepository;

import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void start() {
        PropertiesManager propertiesManager = new PropertiesManager();
        List<City> cities = Parser.scannerCities(propertiesManager.getFile());
        Sorter sorter = new Sorter(cities);
        Searcher searcher = new Searcher(cities);
        Printer printer = new Printer();
        CityRepository cityRepository = new CityRepository(propertiesManager.getUrl());
        Scanner scan = new Scanner(System.in);
        int x;
        String s = "";

        while (!"16".equals(s)) {
            System.out.println("\nРабота с файлом: ");
            System.out.println("1. Для вывода всех городов введите 1");
            System.out.println("2. Для сортировки городов по имени введите 2");
            System.out.println("3. Для сортировки городов по Федеральному округу и имени введите 3");
            System.out.println("4. Для поиска города с наибольшим количеством жителей 4");
            System.out.println("5. Для поиска количества городов в разрезе регионов введите 5");
            System.out.println("Работы с базой данных: ");
            System.out.println("6. Для вывода всех городов с базы данных введите 6");
            System.out.println("7. Для добавления города в базу данных введите 7");
            System.out.println("8. Для удаления города в базе данных введите 8");
            System.out.println("9. Для изменения города в базе данных введите 9");
            System.out.println("10. Для поиска города по имени в базе данных введите 10");
            System.out.println("11. Для сортировки городов в базе данных по имени введите 11");
            System.out.println("12. Для сортировки городов в базе данных по Федеральному округу и имени введите 12");
            System.out.println("13. Для поиска города с наибольшим количеством жителей в базе данных введите 13");
            System.out.println("14. Для поиска количества городов в разрезе регионов из базы данных введите 14");
            System.out.println("15. Для добавления городов из файла в базу данных введите 15 ");
            System.out.println("16. Для выхода из программы введите 16");
            s = scan.next();
            try {
                x = Integer.parseInt(s);
                switch (x) {
                    case 1:
                        printer.print(searcher.getAllCities());
                        break;
                    case 2:
                        printer.print(sorter.getSortedCitiesByName());
                        break;
                    case 3:
                        printer.print(sorter.getSortedCitiesByDistrictAndName());
                        break;
                    case 4:
                        printer.print(searcher.getMaxPopulation());
                        break;
                    case 5:
                        printer.print(searcher.getRegionsCount());
                        break;
                    case 6:
                        printer.print(cityRepository.findAll());
                        break;
                    case 7:
                        System.out.println("Введите город: ");
                        City city = new City(
                                scan.next(),
                                scan.next(),
                                scan.next(),
                                Long.parseLong(scan.next()),
                                Integer.parseInt(scan.next())
                        );
                        printer.print(cityRepository.addCity(city));
                        break;
                    case 8:
                        System.out.println("Введите название города: ");
                        String name = scan.next();
                        printer.print(cityRepository.deleteCity(name));
                        break;
                    case 9:
                        System.out.println("Введиие имя города, который надо изменить: ");
                        String nameEdit = scan.next();
                        System.out.println("Введите измененый город: ");
                        City cityEdit = new City(
                                scan.next(),
                                scan.next(),
                                scan.next(),
                                Long.parseLong(scan.next()),
                                Integer.parseInt(scan.next())
                        );
                        printer.print(cityRepository.editCity(nameEdit, cityEdit));
                        break;
                    case 10:
                        System.out.println("Введите имя города: ");
                        String cityFindByName = scan.next();
                        printer.print(cityRepository.findCity(cityFindByName));
                        break;
                    case 11:
                        printer.print(cityRepository.findAllCitiesSortedByName());
                        break;
                    case 12:
                        printer.print(cityRepository.findAllCitiesSortedByDistrictAndName());
                        break;
                    case 13:
                        printer.print(cityRepository.getMaxPopulation());
                        break;
                    case 14:
                        printer.print(cityRepository.getCityCountByRegions());
                        break;
                    case 15:
                        printer.print(cityRepository.addListCitiesInDB(searcher.getAllCities()));
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Введите число");
            }
        }
        System.out.println("До свидания!");
    }
}
