package utils;

import org.mindrot.jbcrypt.BCrypt;

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

    public Connection connExternal() {
        loadDriver(dbDriver);
        return getConnection();
    }

    // used to hash password
    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    // used to validate hashed pass with entered plaintext
    private boolean checkPass(String plainPassword, String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword)) {
            System.out.println("The password matches.");
            return true;
        } else {
            System.out.println("The password does not match.");
        }
        return false;
    }


    public String insert(User user) {
        loadDriver(dbDriver);
        Connection connection = getConnection();
        String sql = "insert into users values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getEmail());
            // hashing and passing simultaneously
            preparedStatement.setString(2, hashPassword(user.getPassword()));
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getCountry());
            preparedStatement.setString(5, user.getCity());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.setString(7, user.getName());
            preparedStatement.setString(8, user.getSurname());
            preparedStatement.executeUpdate();
            connection.close();
            System.out.println("Insert successful from DBUtils.insert()...");
        } catch (SQLException e) {
            System.out.println("Failed to insert from DBUtils.insert()...");
            e.printStackTrace();
        }

        return null;
    }

    public boolean update(User user) {
        loadDriver(dbDriver);
        Connection connection = getConnection();
        boolean status = false;
        String removeSQL = "delete from users where email=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(removeSQL);
            preparedStatement.setString(1, user.getEmail());
            // preparedStatement.setString(2, hashPassword(user.getPassword()));
            ResultSet resultSet = preparedStatement.executeQuery();

            status = resultSet.next();

            while(status){
                String hashedPass = resultSet.getString("password");

                if(checkPass(user.getPassword(), hashedPass)){
                    System.out.println("Found User");
                    break;
                } else {
                    System.out.println("No matches...Failed to remove old record");
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to remove old record");
            e.printStackTrace();
        }

        String sql = "insert into users values(?,?,?,?,?,?,?,?)";
        try {
            System.out.println("Inserting updated record...");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getEmail());
            // hashing and passing
            preparedStatement.setString(2, hashPassword(user.getPassword()));
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getCountry());
            preparedStatement.setString(5, user.getCity());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.setString(7, user.getName());
            preparedStatement.setString(8, user.getSurname());
            preparedStatement.executeUpdate();
            System.out.println("Inserted!");
            preparedStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to insert updated record");
            e.printStackTrace();
        }

        return false;
    }


    public boolean validate(User user) {
        loadDriver(dbDriver);
        Connection connection = getConnection();
        boolean status = false;
        String sql = "select * from users where email = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getEmail());
           // preparedStatement.setString(2, hashPassword(user.getPassword()));
            ResultSet resultSet = preparedStatement.executeQuery();

            status = resultSet.next();

            while(status){
                String hashedPass = resultSet.getString("password");

                if(checkPass(user.getPassword(), hashedPass)){
                    System.out.println("Found User");
                    return status;
                } else {
                    System.out.println("No matches");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return status;
    }

    public User retrieveUser(User user) {
        loadDriver(dbDriver);
        Connection connection = getConnection();
        boolean status = false;
        String sql = "select * from users where email = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getEmail());
            //preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            status = resultSet.next();

            while(status){
                String hashedPass = resultSet.getString("password");

                if(checkPass(user.getPassword(), hashedPass)){
                    System.out.println("User Found...Validating");
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setAge(resultSet.getInt("age"));
                    user.setCountry(resultSet.getString("country"));
                    user.setCity(resultSet.getString("city"));
                    user.setGender(resultSet.getString("gender"));

                    return user;

                } else {
                    System.out.println("Failed to Validate");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void enrollTo(EnrolledCourses enrolledCourses) {
        loadDriver(dbDriver);
        Connection connection = getConnection();
        boolean status = false;
        String sql = "insert into active_courses values(?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, enrolledCourses.getEmail());
            preparedStatement.setInt(2, enrolledCourses.getCourse_id());
            preparedStatement.executeUpdate();

            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
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

            if (status) {
                assert false;
                enrolledCourses.setEmail(user.getEmail());
                enrolledCourses.setCourse_id(resultSet.getInt("course_id"));
                //enrolledCourses.setCourse_name(resultSet.getString("course_name"));
                return enrolledCourses;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
