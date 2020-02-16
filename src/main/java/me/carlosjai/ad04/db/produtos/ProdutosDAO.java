/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.db.produtos;

import java.util.List;
import me.carlosjai.ad04.obxectos.Produto;
import me.carlosjai.ad04.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author carlos
 */
public class ProdutosDAO {

    protected static List<Produto> obterProdutos() throws HibernateException {
        return HibernateUtil.createQuery("SELECT p FROM Produto p").list();
    }

    protected static Produto obterProduto(String nomeProduto) throws HibernateException {
        Produto p = null;
        Query query = HibernateUtil.getSession().createQuery("SELECT p FROM Produto p WHERE p.nome=:nome");
        query.setParameter("nome", nomeProduto);
        List<Produto> l = query.list();
        if (l != null && !l.isEmpty()) {
            p = l.get(0);
        }
        return p;
    }

    protected static void insertarProduto(Produto p) throws HibernateException {
        Transaction tran = null;
        Session session = HibernateUtil.getSession();
        tran = session.beginTransaction();
        session.save(p);
        tran.commit();
    }

    protected static void actualizarProduto(String nomeProduto, Produto p) throws HibernateException {
        Transaction tran = null;
        Session session = HibernateUtil.getSession();
        tran = session.beginTransaction();
        Query query = session.createQuery("UPDATE Produto set nome=:nome, descripcion=:descripcion, prezo=:prezo WHERE nome=:velloNome");
        query.setParameter("nome", p.getNome());
        query.setParameter("descripcion", p.getDescripcion());
        query.setParameter("prezo", p.getPrezo());
        query.setParameter("velloNome", nomeProduto);
        tran.commit();
    }

    protected static void actualizarProduto(Produto p) throws HibernateException {
        Transaction tran = null;
        Session session = HibernateUtil.getSession();
        tran = session.beginTransaction();
        session.update(p);
        tran.commit();
    }

    protected static void eliminarProduto(Produto p) throws HibernateException {
        Transaction tran = null;
        Session session = HibernateUtil.getSession();
        tran = session.beginTransaction();
        session.delete(p);
        tran.commit();
    }
}
