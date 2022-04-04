package database.factory;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://us-cdbr-east-05.cleardb.net/heroku_104c13d7d7e815f";
    private static final String USER = "b0e35dcaf1f745";
    private static final String PASSWORD = "73a934da";

    //Faz a conexão com o DB
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            return connection;
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
