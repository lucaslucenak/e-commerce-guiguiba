package database.dao;

import database.factory.ConnectionFactory;
import database.models.Categoria;
import database.models.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void create(Produto produto) throws SQLException {
        Connection con = null;
        PreparedStatement pstm = null;

        //Query SQL
        String sql = "INSERT INTO tb_produtos(nome, categoria, preco, quantidade)" +
                "VALUES (?, ?, ?, ?)";
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getCategoria());
            pstm.setDouble(3, produto.getPreco());
            pstm.setInt(4, produto.getQuantidade());

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

    public List<Produto> read() throws SQLException {
        String sql = "SELECT * FROM tb_produtos";

        List<Produto> produtos  = new ArrayList<Produto>();

        Connection con = null;
        PreparedStatement pstm = null;

        //Recupera dados do DB
        ResultSet rset = null;

        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Produto produto = new Produto();

                produto.setNome(rset.getString("nome"));
                produto.setCategoria(rset.getString("categoria"));
                produto.setPreco(rset.getDouble("preco"));
                produto.setQuantidade(rset.getInt("preco"));
                produto.setId(rset.getInt("id"));

                produtos.add(produto);
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
        return produtos;
    }

    public List<Produto> read(String categoria) throws SQLException {
        String sql = "SELECT * FROM tb_produtos WHERE categoria LIKE '%" + categoria + "%'";

        List<Produto> produtos  = new ArrayList<Produto>();

        Connection con = null;
        PreparedStatement pstm = null;

        //Recupera dados do DB
        ResultSet rset = null;

        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Produto produto = new Produto();

                produto.setNome(rset.getString("nome"));
                produto.setCategoria(rset.getString("categoria"));
                produto.setPreco(rset.getDouble("preco"));
                produto.setQuantidade(rset.getInt("preco"));
                produto.setId(rset.getInt("id"));

                produtos.add(produto);
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
        return produtos;
    }

    public String read(Integer id) throws SQLException {
        String sql = "SELECT * FROM tb_produtos WHERE id LIKE " + id;
        Produto produto = new Produto();


        Connection con = null;
        PreparedStatement pstm = null;

        //Recupera dados do DB
        ResultSet rset = null;

        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                produto.setNome(rset.getString("nome"));
                produto.setCategoria(rset.getString("categoria"));
                produto.setPreco(rset.getDouble("preco"));
                produto.setQuantidade(rset.getInt("preco"));
                produto.setId(rset.getInt("id"));

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
        return produto.getCategoria();
    }

    public void update(Produto produto) throws SQLException {
        String sql = "UPDATE tb_produtos SET nome = ?, categoria = ?, preco = ?, quantidade = ? WHERE id = ?";

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(sql);

            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getCategoria());
            pstm.setDouble(3, produto.getPreco());
            pstm.setInt(4, produto.getQuantidade());
            pstm.setInt(5, produto.getId());

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
        String sql = "DELETE FROM tb_produtos WHERE id = ?";

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
