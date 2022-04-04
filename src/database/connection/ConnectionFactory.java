package database.connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql//us-cdbr-east-05.cleardb.net/heroku_104c13d7d7e815f";
    private static String USER = "b0e35dcaf1f745";
    private static String PASSWORD = "73a934da";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw  new RuntimeException("Erro na conexão: ", e);
        }
    }

    public static void closeConnection(Connection connection) {

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void closeConnection(Connection connection, PreparedStatement statement) {
        closeConnection(connection);
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        closeConnection(connection, statement);
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);
        }
    }
//    Connection connection = DriverManager.getConnection();

    //mysql://b0e35dcaf1f745:73a934da@us-cdbr-east-05.cleardb.net/heroku_104c13d7d7e815f?reconnect=true
}
