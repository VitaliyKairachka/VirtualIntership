package sber.kairachka.repository;

import sber.kairachka.domain.City;

import java.sql.*;
import java.util.*;

public class CityRepository {
    private String url;

    public CityRepository(String url) {
        this.url = url;
    }

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CITIES");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                City city = new City(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getLong(5),
                        resultSet.getInt(6));
                cities.add(city);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cities;
    }

    public boolean addCity(City city) {
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO CITIES(NAME, REGION, DISTRICT, POPULATION, FOUNDATION) VALUES(?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getRegion());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setLong(4, city.getPopulation());
            preparedStatement.setInt(5, city.getFoundation());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteCity(String name) {
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM CITIES WHERE NAME  = ?")) {
            preparedStatement.setString(1, name);
            return (preparedStatement.executeUpdate()) == 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editCity(String name, City city) {
//        String SQL = "UPDATE CITIES\n" +
//                "        SET\n" +
//                "        NAME = '" + city.getName() + "',\n" +
//                "        REGION = '" + city.getRegion() + "',\n" +
//                "        DISTRICT = '" + city.getDistrict() + "',\n" +
//                "        POPULATION = " + city.getPopulation() + ",\n" +
//                "        FOUNDATION = " + city.getFoundation() + "\n" +
//                "        WHERE NAME = '" + name + "';";
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE CITIES SET " +
                     "NAME = ?, REGION = ?, DISTRICT = ?, POPULATION = ?, FOUNDATION = ? WHERE NAME = ?")) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getRegion());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setLong(4, city.getPopulation());
            preparedStatement.setInt(5, city.getFoundation());
            preparedStatement.setString(6, name);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<City> findCity(String name) {
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM CITIES WHERE NAME = '" + name + "'; ")) {
            resultSet.next();
            City city = new City(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getLong(5),
                    resultSet.getInt(6));
            return Optional.of(city);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<City> findAllCitiesSortedByName() {
        List<City> cities = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM CITIES ORDER BY LOWER(NAME) ASC");) {
            while (resultSet.next()) {
                City city = new City(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getLong(5),
                        resultSet.getInt(6));
                cities.add(city);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cities;
    }

    public List<City> findAllCitiesSortedByDistrictAndName() {
        List<City> cities = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM CITIES ORDER BY LOWER(DISTRICT) ASC, LOWER(NAME) ASC")) {
            while (resultSet.next()) {
                City city = new City(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getLong(5),
                        resultSet.getInt(6));
                cities.add(city);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cities;
    }

    public long[] getMaxPopulation() {
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT ID, POPULATION FROM CITIES ORDER BY POPULATION DESC LIMIT 1");) {
            resultSet.next();
            return new long[]{resultSet.getLong(1), resultSet.getLong(2)};
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new long[]{0};
    }

    public Map<String, Integer> getCityCountByRegions() {
        Map<String, Integer> regions = new TreeMap<>();
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT REGION, COUNT (NAME) AS KOLVO FROM CITIES GROUP BY REGION");) {
            while (resultSet.next()) {
                regions.put(resultSet.getString("REGION"), resultSet.getInt("KOLVO"));
            }
            return regions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return regions;
    }

    public boolean addListCitiesInDB(List<City> cities) {
        for (City city : cities) {
            try (Connection connection = DriverManager.getConnection(url);
                 Statement statement = connection.createStatement()) {
                statement.execute(
                        "INSERT INTO CITIES(NAME, REGION, DISTRICT, POPULATION, FOUNDATION)" +
                                "VALUES ('" + city.getName() + "' , '" + city.getRegion() + "' , '" +
                                city.getDistrict() + "' , " + city.getPopulation() + " , " +
                                city.getFoundation() + ");");
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
