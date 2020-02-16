/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.db.tendas;

import java.util.List;
import me.carlosjai.ad04.obxectos.Produto;
import me.carlosjai.ad04.obxectos.Tenda;
import org.hibernate.HibernateException;

/**
 *
 * @author carlos
 */
public class TendasAccessor {

    public static List<Tenda> getTendas() throws HibernateException {
        return TendasDAO.getTendas();
    }

    public static Tenda getTenda(String nomeTenda, List<Produto> lProdutos) throws HibernateException {
        return TendasDAO.getTenda(nomeTenda);
    }

    public static void insertarTenda(Tenda t) throws HibernateException {
        TendasDAO.insertarTenda(t);
    }

    public static void actualizarTenda(String nome, Tenda t) throws HibernateException {
        TendasDAO.actualizarTenda(nome, t);
    }
    
    public static void actualizarTenda(Tenda t) throws HibernateException {
        TendasDAO.actualizarTenda(t);
    }

    public static void eliminarTenda(Tenda t) throws HibernateException {
        TendasDAO.eliminarTenda(t);
    }
}
