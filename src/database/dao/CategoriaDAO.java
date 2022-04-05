package database.dao;

import database.factory.ConnectionFactory;
import database.models.Categoria;
import database.models.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    //CRUD

    public void create(Categoria categoria) throws SQLException {
        Connection con = null;
        PreparedStatement pstm = null;

        //Query SQL
        String sql = "INSERT INTO tb_categorias(nome)" +
                "VALUES (?)";
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, categoria.getNome());

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

    public List<Categoria> read() throws SQLException {
        String sql = "SELECT * FROM tb_categorias";

        List<Categoria> categorias  = new ArrayList<Categoria>();

        Connection con = null;
        PreparedStatement pstm = null;

        //Recupera dados do DB
        ResultSet rset = null;

        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Categoria categoria = new Categoria();

                categoria.setNome(rset.getString("nome"));

                categorias.add(categoria);
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
        return  categorias;
    }
}
