/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.db.produtos;

import java.sql.SQLException;
import java.util.List;
import me.carlosjai.ad04.obxectos.Produto;

/**
 *
 * @author carlos
 */
public class ProdutosAccessor {

    public static List<Produto> obterProdutos() throws SQLException, ClassNotFoundException {
        return ProdutosDAO.obterProdutos();
    }

    public static Produto obterProduto(String nomeProduto) throws SQLException, ClassNotFoundException {
        return ProdutosDAO.obterProduto(nomeProduto);
    }

    public static void insertarProduto(Produto p) throws SQLException, ClassNotFoundException {
        ProdutosDAO.insertarProduto(p);
    }

    public static void actualizarProduto(String nomeProduto, Produto novaInfo) throws SQLException, ClassNotFoundException {
        ProdutosDAO.actualizarProduto(nomeProduto, novaInfo);
    }

    public static void eliminarProduto(String nomeProduto) throws SQLException, ClassNotFoundException {
        ProdutosDAO.eliminarProduto(nomeProduto);
    }
}
