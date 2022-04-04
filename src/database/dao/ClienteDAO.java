package database.dao;

import database.factory.ConnectionFactory;
import database.models.Cliente;
import database.models.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    //CRUD

    public void create(Cliente cliente) throws SQLException {
        Connection con = null;
        PreparedStatement pstm = null;

        //Query SQL
        String sql = "INSERT INTO tb_clientes(username, password, contato, endereco1, endereco2, endereco3)" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, cliente.getUsername());
            pstm.setString(2, cliente.getPassword());
            pstm.setString(3, cliente.getContato());
            pstm.setString(4, cliente.getEndereco().getEndereco1());
            pstm.setString(5, cliente.getEndereco().getEndereco2());
            pstm.setString(6, cliente.getEndereco().getEndereco3());

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

    public List<Cliente> read() throws SQLException {
        String sql = "SELECT * FROM tb_vendedores";

        List<Cliente> clientes  = new ArrayList<Cliente>();

        Connection con = null;
        PreparedStatement pstm = null;

        //Recupera dados do DB
        ResultSet rset = null;

        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Cliente cliente = new Cliente();

                cliente.setUsername(rset.getString("username"));
                cliente.setPassword(rset.getString("password"));

                clientes.add(cliente);
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
        return  clientes;
    }
}
