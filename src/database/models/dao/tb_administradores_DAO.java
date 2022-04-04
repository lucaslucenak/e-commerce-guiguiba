package database.models.dao;

import database.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class tb_administradores_DAO {
    public void create() {

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
