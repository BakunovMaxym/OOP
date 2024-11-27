package com.lb8.rest_api.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.lb8.rest_api.persistence.models.Day;

@Service
public class DayService extends DBaccessService<Day> {

    private final String url = "jdbc:sqlite:lessonsDB.db";

    public DayService() {
        try (Connection connection = DriverManager.getConnection(url)) {
            System.err.println("Connected to DB");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Day day) {
        String query = "INSERT INTO days(name, firstSubj, secondSubj, thirdSubj, fourthSubj) VALUES(?,?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, day.getName());
            statement.setString(2, day.getFirstSubj());
            statement.setString(3, day.getSecondSubj());
            statement.setString(4, day.getThirdSubj());
            statement.setString(5, day.getFourthSubj());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
public List<Day> readAll() {
    List<Day> days = new ArrayList<>();
    String query = "SELECT " +
                   "    days.id AS day_id, " +
                   "    days.name AS day_name, " +
                   "    lessons1.name AS first_subject, " +
                   "    lessons2.name AS second_subject, " +
                   "    lessons3.name AS third_subject, " +
                   "    lessons4.name AS fourth_subject " +
                   "FROM days " +
                   "LEFT JOIN lessons AS lessons1 ON days.firstSubj = lessons1.id " +
                   "LEFT JOIN lessons AS lessons2 ON days.secondSubj = lessons2.id " +
                   "LEFT JOIN lessons AS lessons3 ON days.thirdSubj = lessons3.id " +
                   "LEFT JOIN lessons AS lessons4 ON days.fourthSubj = lessons4.id";

    try (Connection connection = DriverManager.getConnection(url);
         PreparedStatement statement = connection.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {
            Day day = new Day();
            day.setId(resultSet.getInt("day_id"));
            day.setName(resultSet.getString("day_name"));

            day.setFirstSubj(resultSet.getString("first_subject"));
            day.setSecondSubj(resultSet.getString("second_subject"));
            day.setThirdSubj(resultSet.getString("third_subject"));
            day.setFourthSubj(resultSet.getString("fourth_subject"));

            days.add(day);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return days;
}

    @Override
    public Day read(int id) {
        Day day = null;
        String query = "SELECT * FROM days WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(query)) {
    
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
    
            if (resultSet.next()) {
                day = new Day();
                day.setId(resultSet.getInt("id"));
                day.setName(resultSet.getString("name"));
                day.setFirstSubj(resultSet.getString("firstSubj"));
                day.setSecondSubj(resultSet.getString("secondSubj"));
                day.setThirdSubj(resultSet.getString("thirdSubj"));
                day.setFourthSubj(resultSet.getString("fourthSubj"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return day;
    }
    
    @Override
    public boolean update(Day day, int id) {
        String query = "UPDATE days SET name = ?, firstSubj = ?, secondSubj = ?, thirdSubj = ?, fourthSubj = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(query)) {
    
            statement.setString(1, day.getName());
            statement.setString(2, day.getFirstSubj());
            statement.setString(3, day.getSecondSubj());
            statement.setString(4, day.getThirdSubj());
            statement.setString(5, day.getFourthSubj());
            statement.setInt(6, id);
    
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM days WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(query)) {
    
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
