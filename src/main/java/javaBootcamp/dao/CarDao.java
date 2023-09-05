package javaBootcamp.dao;

import javaBootcamp.entity.Car;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


@Component
public class CarDao {
    private static String url = "jdbc:postgresql://localhost:5432/car";
    private static String userName = "postgres";
    private static String password = "root";
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from car";
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                Car car = new Car();
                car.setId(res.getInt("id"));
                car.setModel(res.getString("model"));
                cars.add(car);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return cars;
    }

    public Car show(int id) {
        Car car = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from car where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            car = new Car();
            car.setId(resultSet.getInt("id"));
            car.setModel(resultSet.getString("model"));

        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return car;
    }

    // crud
    // c create new car
    // ![](../../../../../../../Снимок экрана 2023-09-04 в 18.05.09.png)r select all cars or one car with id
    //  update car with id and new car
    //  delete car with id

    public void saveCar(Car car) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into car values(1, ?)");
            preparedStatement.setString(1, car.getModel());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void updateCar(int id, Car newCar) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("update car set model=? where id=?");
            preparedStatement.setString(1, newCar.getModel());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("delete from car where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
