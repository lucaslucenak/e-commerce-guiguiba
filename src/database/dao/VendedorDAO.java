package database.dao;

import database.factory.ConnectionFactory;
import database.models.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VendedorDAO {

    //CRUD

    public void create(Vendedor vendedor) throws SQLException {
        Connection con = null;
        PreparedStatement pstm = null;

        //Query SQL
        String sql = "INSERT INTO tb_vendedores(username, password, vendasRealizadas)" +
                "VALUES (?, ?, ?)";
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, vendedor.getUsername());
            pstm.setString(2, vendedor.getPassword());
            pstm.setDouble(3, vendedor.getVendasRealizadas());

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
