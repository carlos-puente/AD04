/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.db.stockProdutoTenda;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author carlos
 */
public class StockProdutosTendasAccessor {

    public static List<StockProdutosTenda> obterPorTenda(String nomeTenda) throws SQLException, ClassNotFoundException {
        return StockProdutosTendasDAO.obterPorTenda(nomeTenda);
    }

    public static void insertarStock(String nomeProduto, String nomeTenda, Double stock) throws SQLException, ClassNotFoundException {
        StockProdutosTendasDAO.insertarStockProdutoTenda(nomeProduto, nomeTenda, stock);
    }

    public static void actualizarStockProdutoTenda(String nomeProduto, String nomeTenda, Double stock) throws SQLException, ClassNotFoundException {
        StockProdutosTendasDAO.actualizarStockProdutoTenda(nomeProduto, nomeTenda, stock);
    }

    public static void eliminarStockProdutoTenda(String nomeProduto, String nomeTenda) throws SQLException, ClassNotFoundException {
        StockProdutosTendasDAO.eliminarStockProdutoTenda(nomeProduto, nomeTenda);
    }
}
