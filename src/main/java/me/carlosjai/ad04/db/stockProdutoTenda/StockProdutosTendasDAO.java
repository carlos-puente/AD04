/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.db.stockProdutoTenda;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import me.carlosjai.ad04.db.Conector;

/**
 *
 * @author carlos
 */
public class StockProdutosTendasDAO {

    protected static List<StockProdutosTenda> obterPorTenda(String nomeTenda) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        Connection c = Conector.getConexion();
        stmt = c.createStatement();
        List<StockProdutosTenda> lp = new ArrayList<>();
        String sql = "SELECT * FROM Stock_produto_tenda WHERE nome_tenda='" + nomeTenda + "' ORDER BY NOME_TENDA, NOME_PRODUTO";
        ResultSet s = stmt.executeQuery(sql);
        while (s.next()) {
            lp.add(new StockProdutosTenda(s.getString("nome_tenda"), s.getString("nome_produto"), s.getDouble("stock")));
        }

        c.close();
        return lp;
    }

    protected static void insertarStockProdutoTenda(String nomeProduto, String nomeTenda, Double stock) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        Connection c = Conector.getConexion();
        stmt = c.createStatement();
        String sql = "INSERT INTO Stock_produto_tenda (nome_produto, nome_tenda, stock) VALUES ('" + nomeProduto + "' , '" + nomeTenda + "', " + stock + ")";
        stmt.executeUpdate(sql);
        c.close();
    }

    protected static void actualizarStockProdutoTenda(String nomeProduto, String nomeTenda, Double stock) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Stock_produto_tenda SET stock= " + stock
                + " WHERE nome_produto='" + nomeProduto + "' AND nome_tenda='" + nomeTenda + "'";
        Statement stmt = null;
        Connection con = Conector.getConexion();
        stmt = con.createStatement();
        Conector.engadirPragma(stmt);
        stmt.executeUpdate(sql);
        con.close();
    }

    protected static void eliminarStockProdutoTenda(String nomeProduto, String nomeTenda) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Stock_produto_tenda WHERE nome_produto='" + nomeProduto + "' AND nome_tenda='" + nomeTenda + "'";
        Statement stmt = null;
        Connection con = Conector.getConexion();
        stmt = con.createStatement();
        Conector.engadirPragma(stmt);
        stmt.executeUpdate(sql);
        con.close();
    }
}
