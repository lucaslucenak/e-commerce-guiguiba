package database.dao;

import database.factory.ConnectionFactory;
import database.models.Administrador;
import database.models.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Vendedor> read() throws SQLException {
        String sql = "SELECT * FROM tb_vendedores";

        List<Vendedor> vendedores  = new ArrayList<Vendedor>();

        Connection con = null;
        PreparedStatement pstm = null;

        //Recupera dados do DB
        ResultSet rset = null;

        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Vendedor vendedor = new Vendedor();

                vendedor.setUsername(rset.getString("username"));
                vendedor.setPassword(rset.getString("password"));

                vendedores.add(vendedor);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return  vendedores;
    }
}
