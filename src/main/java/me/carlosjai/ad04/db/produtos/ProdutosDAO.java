/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.db.produtos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import me.carlosjai.ad04.db.Conector;
import me.carlosjai.ad04.obxectos.Produto;

/**
 *
 * @author carlos
 */
public class ProdutosDAO {

    protected static List<Produto> obterProdutos() throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        Connection c = Conector.getConexion();
        stmt = c.createStatement();
        List<Produto> lp = new ArrayList<>();
        String sql = "SELECT * FROM Produtos ORDER BY nome";

        ResultSet s = stmt.executeQuery(sql);
        while (s.next()) {
            lp.add(new Produto(s.getString("nome"), s.getString("descripcion"), s.getDouble("prezo")));
        }
        c.close();
        return lp;
    }

    protected static Produto obterProduto(String nomeProduto) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        Connection c = Conector.getConexion();
        stmt = c.createStatement();
        Produto p = null;
        String sql = "SELECT * FROM Produtos WHERE nome='" + nomeProduto + "'";
        ResultSet s = stmt.executeQuery(sql);
        while (s.next()) {
            p = new Produto(s.getString("nome"), s.getString("descripcion"), s.getDouble("prezo"));
            break;
        }
        c.close();
        return p;
    }

    protected static void insertarProduto(Produto p) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        Connection c = Conector.getConexion();
        stmt = c.createStatement();
        String sql = "INSERT INTO Produtos (nome, descripcion, prezo) VALUES ('" + p.getNome() + "' , '" + p.getDescripcion() + "', " + p.getPrezo() + ")";
        stmt.executeUpdate(sql);
        c.close();
    }

    protected static void actualizarProduto(String nomeProduto, Produto novaInfo) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Produtos SET nome = '" + novaInfo.getNome() + "', descripcion = '" + novaInfo.getDescripcion() + "', prezo= " + novaInfo.getPrezo() + " WHERE nome='" + nomeProduto + "'";
        Statement stmt = null;
        Connection con = Conector.getConexion();
        stmt = con.createStatement();
        Conector.engadirPragma(stmt);
        stmt.executeUpdate(sql);
        con.close();
    }

    protected static void eliminarProduto(String nomeProduto) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Produtos WHERE nome='" + nomeProduto + "'";
        Statement stmt = null;
        Connection con = Conector.getConexion();
        stmt = con.createStatement();
        Conector.engadirPragma(stmt);
        stmt.executeUpdate(sql);
        con.close();
    }
}
