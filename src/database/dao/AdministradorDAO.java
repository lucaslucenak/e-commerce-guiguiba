package database.dao;

import database.factory.ConnectionFactory;
import database.models.Administrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Administrador> read() throws SQLException {
        String sql = "SELECT * FROM tb_administradores";

        List<Administrador> administradores  = new ArrayList<Administrador>();

        Connection con = null;
        PreparedStatement pstm = null;

        //Recupera dados do DB
        ResultSet rset = null;

        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Administrador administrador = new Administrador();

                administrador.setUsername(rset.getString("username"));
                administrador.setPassword(rset.getString("password"));
                administrador.setId(rset.getInt("id"));

                administradores.add(administrador);
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
        return  administradores;
    }


}
