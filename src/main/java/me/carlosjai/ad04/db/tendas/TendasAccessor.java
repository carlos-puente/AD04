/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.db.tendas;

import java.sql.SQLException;
import java.util.List;
import me.carlosjai.ad04.obxectos.Produto;
import me.carlosjai.ad04.obxectos.Tenda;

/**
 *
 * @author carlos
 */
public class TendasAccessor {

    public static List<Tenda> getTendas(List<Produto> lProdutos) throws ClassNotFoundException, SQLException {
        return TendasDAO.getTendas(lProdutos);
    }

    public static Tenda getTenda(String nomeTenda, List<Produto> lProdutos) throws ClassNotFoundException, SQLException {
        return TendasDAO.getTenda(nomeTenda, lProdutos);
    }

    public static void insertarTenda(Tenda t) throws SQLException, ClassNotFoundException {
        TendasDAO.insertarTenda(t);
    }

    public static void actualizarTenda(String nome, Tenda novaInfo) throws SQLException, ClassNotFoundException {
        TendasDAO.actualizarTenda(nome, novaInfo);
    }

    public static void eliminarTenda(String nome) throws SQLException, ClassNotFoundException {
        TendasDAO.eliminarTenda(nome);
    }
}
