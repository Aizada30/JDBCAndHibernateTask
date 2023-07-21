package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String sql = "create table if not exists users(" +
                "id serial primary key, " +
                "first_name varchar," +
                "last_name varchar, " +
                "age smallInt )";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Create new table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void dropUsersTable() {
        String sql = "drop  table users;";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Table successfully droped!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into users (" +
                "first_name,last_name,age)" +
                "values (" +
                "?,?,?);";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User successfully saved");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(Long id) {

        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = Util.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("User with ID " + id + " not found");
            } else {
                System.out.println("User with ID " + id + "successfully found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users;";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }


    public void cleanUsersTable() {
        String sql="delete from users;";
        try(Connection connection = Util.getConnection();
            Statement statement= connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("\nTable successfully deleted!\n");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


    //        String sql = "delete from users where id=?;";
//        try (Connection connection = Util.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            preparedStatement.executeUpdate();
//            preparedStatement.setLong(1, id);
//            System.out.println("User by id: " + id + "is deleted!");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }



}