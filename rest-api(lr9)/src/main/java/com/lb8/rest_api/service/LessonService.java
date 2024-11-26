package com.lb8.rest_api.service;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lb8.rest_api.persistence.models.Lesson;

@Service
public class LessonService implements DBaccessService<Lesson> {

    private final String url = "jdbc:sqlite:lessonsDB.db";

    public LessonService() {
        try (Connection connection = DriverManager.getConnection(url)) {
            System.err.println("Connected to DB");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
public void create(Lesson lesson) {
    // Не використовуємо PreparedStatement, вставляємо значення безпосередньо в запит
    String query = "INSERT INTO lessons(name, teacher, time) VALUES('" 
                   + lesson.getName() + "', '" 
                   + lesson.getTeacher() + "', '" 
                   + lesson.getTime() + "');";

    try (Connection connection = DriverManager.getConnection(url);
         Statement statement = connection.createStatement()) {

        // Виконуємо SQL-запит
        statement.executeUpdate(query);
        System.err.println("Lesson created successfully");

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    @Override
    public List<Lesson> readAll() {
        List<Lesson> lessons = new ArrayList<>();
        String query = "SELECT * FROM lessons";
        try (Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(resultSet.getInt("id"));
                lesson.setName(resultSet.getString("name"));
                lesson.setTeacher(resultSet.getString("teacher"));
                java.sql.Time time = resultSet.getTime("time");
                if (time != null) {
                    lesson.setTime(time.toLocalTime());
                }

                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    @Override
    public Lesson read(int id) {
        Lesson lesson = null;
        String query = "SELECT * FROM lessons WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                lesson = new Lesson();
                lesson.setId(resultSet.getInt("id"));
                lesson.setName(resultSet.getString("name"));
                lesson.setTeacher(resultSet.getString("teacher"));
                lesson.setTime(resultSet.getTime("time").toLocalTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesson;
    }

    @Override
    public boolean update(Lesson lesson, int id) {
        String query = "UPDATE lessons SET name = ?, teacher = ?, time = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, lesson.getName());
            statement.setString(2, lesson.getTeacher());
            statement.setString(3, lesson.getTime().toString());
            statement.setInt(4, id);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM lessons WHERE id = ?";
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
