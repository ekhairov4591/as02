package utils;

import java.sql.*;

public class DBUtils {

    private String dbURL = "jdbc:postgresql://localhost:5432/POM_DB";
    private String dbUser = "postgres";
    private String dbPassword = "Khairov9787";
    private String dbDriver = "org.postgresql.Driver";

    private void loadDriver(String dbDriver) {
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Connection connExternal(){
        loadDriver(dbDriver);
        return getConnection();
    }



    public String insert(User user) {
        loadDriver(dbDriver);
        Connection connection = getConnection();
        String sql = "insert into users values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getCountry());
            preparedStatement.setString(5, user.getCity());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.setString(7, user.getName());
            preparedStatement.setString(8, user.getSurname());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean validate(User user) {
        loadDriver(dbDriver);
        Connection connection = getConnection();
        boolean status = false;
        String sql = "select * from users where email = ? and password = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();

            status = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    public User retrieveUser(User user) {
        loadDriver(dbDriver);
        Connection connection = getConnection();
        boolean status = false;
        String sql = "select * from users where email = ? and password = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            status = resultSet.next();

            if(status){
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setAge(resultSet.getInt("age"));
                user.setCountry(resultSet.getString("country"));
                user.setCity(resultSet.getString("city"));
                user.setGender(resultSet.getString("gender"));

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public EnrolledCourses retrieveActiveCourses(User user) {
        loadDriver(dbDriver);
        Connection connection = getConnection();
        boolean status = false;
        EnrolledCourses enrolledCourses = new EnrolledCourses();
        String sql = "select * from active_courses where email = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();

            status = resultSet.next();

            if(status){
                assert false;
                enrolledCourses.setEmail(user.getEmail());
                enrolledCourses.setCourse_id(resultSet.getInt("course_id"));
                enrolledCourses.setCourse_name(resultSet.getString("course_name"));
                return enrolledCourses;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




    public void enrollTo(EnrolledCourses enrolledCourses){

        loadDriver(dbDriver);
        Connection connection = getConnection();
        boolean status = false;
        String sql = "insert into active_courses values(?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, enrolledCourses.getEmail());
            preparedStatement.setInt(2, enrolledCourses.getCourse_id());
            preparedStatement.setString(3, enrolledCourses.getCourse_name());
            preparedStatement.executeUpdate();

            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }



}
