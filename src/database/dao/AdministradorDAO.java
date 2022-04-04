package database.dao;

import database.factory.ConnectionFactory;
import database.models.Administrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdministradorDAO {

    //CRUD

    public void create(Administrador administrador) throws SQLException {
        Connection con = null;
        PreparedStatement pstm = null;

        //Query SQL
        String sql = "INSERT INTO tb_administradores(username, password)" +
                "VALUES (?, ?)";
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, administrador.getUsername());
            pstm.setString(2, administrador.getPassword());

            pstm.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally { //Fecha as conexões caso estejam abertas
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
