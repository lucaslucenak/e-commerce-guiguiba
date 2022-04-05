package database.dao;

import database.factory.ConnectionFactory;
import database.models.Administrador;
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
                categoria.setId(rset.getInt("id"));

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

    public void update(Categoria categoria) throws SQLException {
        String sql = "UPDATE tb_categorias SET nome = ?" +
                "WHERE id = ?";

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(sql);

            pstm.setString(1, categoria.getNome());
            pstm.setInt(2, categoria.getId());

            pstm.execute();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM tb_categorias WHERE id = ?";

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, id);
            pstm.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            if (pstm != null) {
                pstm.close();
            }
        }
    }
}
